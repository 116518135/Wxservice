package com.wxservice.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.wxservice.database.po.system.Tsyscmpparam;
import com.wxservice.database.po.system.Tsysdatadict;
import com.wxservice.database.po.system.Tsysdatadictdtl;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;

public class NameManager {
	public IDao dao = null;

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public String getName(Class entityClass, Serializable id, String namefield) {
		String result = null;
		if (id == null)
			return "";

		Object entity = dao.get(entityClass, id);
		if (entity == null)
			return "";
		try {
			result = BeanUtils.getProperty(entity, namefield);
		} catch (Exception e) {
			SystemLogger.error("BeanUtil取属性发生错误", e);
		}
		return result;
	}

	public List<LabelValue> getOptions(Class entityClass, String labelfield,
			String valuefield) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from ").append(entityClass.getSimpleName()).append("  a");
		List<LabelValue> options = getOptions(hql.toString(), labelfield,
				valuefield);
		return options;
	}

	public List<LabelValue> getOptions(Class entityClass, String labelfield,
			String valuefield, Integer cmpid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from ").append(entityClass.getSimpleName()).append("  a");
		hql.append(" where a.createtbscmpid=");
		hql.append(cmpid);
		hql.append("");
		List<LabelValue> options = getOptions(hql.toString(), labelfield,
				valuefield);
		return options;
	}

	public List<LabelValue> getOptions(String hql, String labelfield,
			String valuefield) {
		List<LabelValue> options = new ArrayList<LabelValue>();
		try {
			List list = dao.iterate(hql);
			for (Object o : list) {
				String label = BeanUtils.getProperty(o, labelfield);
				String value = BeanUtils.getProperty(o, valuefield);
				options.add(new LabelValue(label, value));
			}
		} catch (Exception e) {
			SystemLogger.error("BeanUtil取属性发生错误", e);
		}
		return options;
	}

	public Tsysdatadict getDatadict(String code) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("  from Tsysdatadict a where  a.code='");
			hql.append(code);
			hql.append("'");
			Tsysdatadict po = (Tsysdatadict) dao.get(hql.toString());
			return po;
		} catch (Exception e) {
			SystemLogger.error("取字典组信息发生错误", e);
		}
		return null;
	}

	public String getOptionName(List options, int prop) {
		for (Object o : options) {
			LabelValue lv = (LabelValue) o;
			if (lv.getIvalue() == prop) {
				return lv.getLabel();
			}
		}
		return "";
	}

	public String getOptionName(List options, String prop) {
		for (Object o : options) {
			LabelValue lv = (LabelValue) o;
			if (lv.getValue().equals(prop)) {
				return lv.getLabel();
			}
		}
		return "";
	}

	// 得到字典信息
	public List<LabelValue> getDatadictList(String code) {
		List<LabelValue> options = new ArrayList<LabelValue>();
		try {
			Tsysdatadict po = this.getDatadict(code);
			if (po != null) {
				StringBuffer hql = new StringBuffer();
				hql.append("  from Tsysdatadictdtl a where a.tsysdatadictid=");
				hql.append(po.getTsysdatadictid());
				List list = dao.iterate(hql.toString());
				for (Object o : list) {
					Tsysdatadictdtl dtl = (Tsysdatadictdtl) o;
					String value = dtl.getCode().toLowerCase();
					String label = dtl.getName().toLowerCase();
					options.add(new LabelValue(label, value));
				}
			}
		} catch (Exception e) {
			SystemLogger.error("取字典信息发生错误", e);
		}
		return options;
	}

	// 取字典名称
	public String getDatadictdtlname(String code, String dtlcode) {
		List<LabelValue> options = this.getDatadictList(code);
		for (LabelValue o : options) {
			if (o.getValue().equals(dtlcode)) {
				return o.getLabel();
			}

		}
		return "";
	}

	public List<LabelValue> getFuncList(String code) {
		List<LabelValue> options = new ArrayList<LabelValue>();
		try {
			Tsysdatadict po = this.getDatadict(code);
			if (po != null) {
				StringBuffer hql = new StringBuffer();
				hql.append("  from Tsysdatadictdtl a where a.tsysdatadictid=");
				hql.append(po.getTsysdatadictid());
				hql.append(" order by cast(a.code as int) asc ");
				List list = dao.iterate(hql.toString());
				for (Object o : list) {
					Tsysdatadictdtl dtl = (Tsysdatadictdtl) o;
					String label = dtl.getCode().toLowerCase();
					String value = dtl.getName().toLowerCase();
					options.add(new LabelValue(label, value));
				}
			}
		} catch (Exception e) {
			SystemLogger.error("取字典信息发生错误", e);
		}
		return options;
	}

	public String getStrCmpparam(String param, String dft) {
		try {
			String result = "";
			Tsyscmpparam po = this.getTsyscmpparam(param);
			if (po != null) {
				result = po.getParamvalue();
				result = StringUtil.notNull(result);
			}
			if (StringUtil.isNotBlank(result)) {
				return result;
			}
		} catch (Exception e) {
			SystemLogger.error("取参数时发生错误:" + param, e);
		}
		return dft;
	}
	
	public Tsyscmpparam getTsyscmpparam(String param) {
		Tsyscmpparam po = null;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsyscmpparam as a where a.paramname='");
		hql.append(param);
		hql.append("' ");
		po = (Tsyscmpparam) dao.get(hql.toString());
		return po;
	}

	public double getDoubleCmpparam(String param, double dft) {
		double result = 0;
		try {
			Tsyscmpparam po = this.getTsyscmpparam(param);
			if (po != null) {
				String value = po.getParamvalue();
				result = MathUtil.parseDouble(value, 0);
			}
			if (result != 0) {
				return result;
			}

		} catch (Exception e) {
			SystemLogger.error("取参数时发生错误:" + param, e);
		}
		return dft;
	}

	public int getIntCmpparam(String param, int dft) {

		try {
			int result = 0;
			Tsyscmpparam po = this.getTsyscmpparam(param);
			if (po != null) {

				String value = po.getParamvalue();
				result = MathUtil.parsetInt(value, 0);
			}
			if (result != 0) {
				return result;
			}
		} catch (Exception e) {
			SystemLogger.error("取参数时发生错误:" + param, e);
		}
		return dft;
	}
	
}
