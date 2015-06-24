package com.wxservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.system.Tsysbillnorule;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.service.system.TsysbillnoruleManager;

public abstract class AbstractBillnoGenerator {
	public IDao dao = null;
	public TsysbillnoruleManager tsysbillnoruleManager;

	private int getMaxNo(int len) {
		int m = 0;
		for (int i = 0; i < len; i++) {
			m = m * 10 + 9;
		}
		return m;
	}

	public static String formatYymm(Date date) {
		if (date == null)
			return "";
		String myDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
		try {
			myDate = sdf.format(date);
		} catch (Exception e) {
		}
		return myDate;
	}

	public static String formatYymmdd(Date date) {
		if (date == null)
			return "";
		String myDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		try {
			myDate = sdf.format(date);
		} catch (Exception e) {
		}
		return myDate;
	}

	public static String formatYy(Date date) {
		if (date == null)
			return "";
		String myDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		try {
			myDate = sdf.format(date);
		} catch (Exception e) {
		}
		return myDate;
	}

	public String formatDate(Date date, Tsysbillnorule po) {
		String dateformat = null;
		if (tsysbillnoruleManager.notype0 == po.getBilltype().intValue()) {
			dateformat = formatYymmdd(date);
		} else if (tsysbillnoruleManager.notype1 == po.getBilltype().intValue()) {
			dateformat = formatYymm(date);
		} else if (tsysbillnoruleManager.notype2 == po.getBilltype().intValue()) {
			dateformat = formatYy(date);
		} else if (tsysbillnoruleManager.notype3 == po.getBilltype().intValue()) {
			dateformat = "";
		} else {// 默认是按日
			dateformat = formatYymmdd(date);
		}
		return dateformat;
	}

	public int getNumcount(int count, Date date, Tsysbillnorule po) {
		if (TsysbillnoruleManager.recounttype0 == po.getCounttype().intValue()) {// 每天都从1开始计数
			String s1 = formatYymmdd(date);
			String s2 = formatYymmdd(po.getEditdate());
			if (s1.equalsIgnoreCase(s2)) {
				count = count + 1;
			} else {
				count = 1;
			}
		} else if (TsysbillnoruleManager.recounttype1 == po.getCounttype()
				.intValue()) {// 每月从1天始计数
			String s1 = formatYymm(date);
			String s2 = formatYymm(po.getEditdate());
			if (s1.equalsIgnoreCase(s2)) {
				count = count + 1;
			} else {
				count = 1;
			}
		} else if (TsysbillnoruleManager.recounttype2 == po.getCounttype()
				.intValue()) {// // 每年从1天始计数
			String s1 = formatYy(date);
			String s2 = formatYy(po.getEditdate());
			if (s1.equalsIgnoreCase(s2)) {
				count = count + 1;
			} else {
				count = 1;
			}
		} else if (TsysbillnoruleManager.recounttype3 == po.getCounttype()
				.intValue()) {// 到达最大号后从1开始计数
			int max = this.getMaxNo(po.getNumlength());
			if (count == max) {
				count = 1;
			} else {
				count = count + 1;
			}
		} else { // 默认是每日的
			String s1 = formatYymmdd(date);
			String s2 = formatYymmdd(po.getEditdate());
			if (s1.equalsIgnoreCase(s2)) {
				count = count + 1;
			} else {
				count = 1;
			}
		}

		return count;
	}

	// 取一个表的最大号
	public int getMaxCount(String table, String prefx, String field) {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" select max(");
		sql.append(field);
		sql.append(") as billno from ");
		sql.append(table);
		sql.append(" where ");
		sql.append(field);
		sql.append(" like '");
		sql.append(prefx);
		sql.append("%'");
		List list = dao.getJdbcManager().queryForList(sql.toString());
		if (list != null && list.size() > 0) {
			Map map = (Map) list.get(0);
			String billno = (String) map.get("billno");
			if (StringUtils.isBlank(billno) || "null".equalsIgnoreCase(billno)) {
				count = 1;
			} else {
				String str = billno.substring(prefx.length());
				count = MathUtil.parsetInt(str, 0);
				count = count + 1;
			}
		} else {
			count = 1;
		}
		return count;

	}

	public Tsysbillnorule getBillnorule(Integer cmpid, String billcode,
			boolean iscopy) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysbillnorule as a where a.code='");
		hql.append(billcode).append("'");
		Tsysbillnorule po = null;
		po = (Tsysbillnorule) dao.get(hql.toString());

		if (po == null && iscopy) {
			Tsysbillnorule src = this.getBillnorule(SysFinal.SYSTEM_ZGSID,
					billcode, false);
			if (src == null) {
				throw new BusinessException("单据编号规则未定义");
			}
			try {
				po = new Tsysbillnorule();
				po.setCode(src.getCode());
				po.setName(src.getName());
				po.setMark(src.getMark());
				po.setBilltype(src.getBilltype());
				po.setNumlength(src.getNumlength());
				po.setCounttype(src.getCounttype());
				po.setCreatetbscmpid(cmpid);
				po.setCurcount(0);
				po.setEditdate(new Date());
				dao.addSave(po);
			} catch (Exception e) {
				SystemLogger.error("复制单据编码规则产生错误", e);
			}
		}

		return po;
	}

	protected String generatorBillno(String mark, boolean isUpdateCount,
			Date date, Tsysbillnorule rule, int count) {
		String dateformat = formatDate(date, rule);
		int newcount = this.getNumcount(count, date, rule);
		String lsbh = SysUtil.getCode(newcount, rule.getNumlength());
		StringBuffer billno = new StringBuffer();
		//billno.append(mark);
		billno.append(rule.getMark());
		billno.append(dateformat);
		billno.append(lsbh);
		// 更新日期和流水编号
		if (isUpdateCount) {
			rule.setEditdate(date);
			rule.setCurcount(newcount);
			dao.updateSave(rule);
		}
		return billno.toString();

	}

	
	
	protected String getPrefix(String mark, Date date, Tsysbillnorule rule) {
		String dateformat = formatDate(date, rule);
		StringBuffer prefx = new StringBuffer();
		prefx.append(mark);
		prefx.append(rule.getMark());
		prefx.append(dateformat);
		return prefx.toString();
	}

	protected String generatorPosBillno(String prefx, boolean isUpdateCount,
			Date date, Tsysbillnorule rule, int count) {
		int newcount = this.getNumcount(count, date, rule);
		String lsbh = SysUtil.getCode(count, rule.getNumlength());
		StringBuffer billno = new StringBuffer();
		billno.append(prefx);
		billno.append(lsbh);
		// 更新日期和流水编号
		if (isUpdateCount) {
			rule.setEditdate(date);
			rule.setCurcount(newcount);
			dao.updateSave(rule);
		}
		return billno.toString();

	}

	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public TsysbillnoruleManager getTsysbillnoruleManager() {
		return tsysbillnoruleManager;
	}

	public void setTsysbillnoruleManager(
			TsysbillnoruleManager tsysbillnoruleManager) {
		this.tsysbillnoruleManager = tsysbillnoruleManager;
	}

}