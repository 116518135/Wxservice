package com.wxservice.framework.report.core.convert;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import bsh.Interpreter;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;
import com.wxservice.framework.report.core.FillService;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.FormatUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.service.NameManager;

public class SimpleConvert implements IConvert {
	IDao dao = null;
	NameManager nameManager = null;

	@Override
	public void convert(Map ds, ReportRequest rr, Trpreportdtl el,
			DataElement p, int row) throws Exception {
	}

	public void baseConvert(Trpreportdtl rp, DataElement p, int row) {
		if (p == null) {
			p = new DataElement();
		}
		p.setRow(row);
		p.setCol(rp.getColindex());
		p.setHeight(1);
		p.setAlign(rp.getAlign());
		p.setWidth(rp.getWidth());
		p.setField(rp.getField());
		p.setTitle(rp.getTitle());// 需要多语言
		if (rp.getDimvalueflag() == null) {
			p.setDimvalueflag(0);
		} else {
			p.setDimvalueflag(rp.getDimvalueflag());
		}

	}

	protected Object getFieldValue(Object ds, String field) throws Exception {
		Object result = null;
		if (ds instanceof java.util.Map) {
			Map map = (Map) ds;
			result = map.get(field.toLowerCase());
		}
		return result;
	}

	protected Object getDataSource(String key, Map dsMap) throws Exception {
		Object ds = null;
		if ("".equals(key)) {
			ds = dsMap.get(ReportUtil.DATASOURCE_DEFAULT);
		} else {
			ds = dsMap.get(key);
		}
		return ds;
	}

	protected void setFieldValue(DataElement p, Object o, Trpreportdtl el)
			throws Exception {
		if (o == null) {
			p.setText("");
			return;
		}
		if (o instanceof Number) {
				Number t = (Number) o;
				String property = el.getField();
				String value = FormatUtil.format(property, (Number) t);
				p.setText(value);
			
			// 都格式化为两位
		} else if (o instanceof Integer) {
			p.setText(o.toString());
			p.setDatatype(2);
		} else if (o instanceof Date) {
			//p.setText(DateUtil.formatSimplyDate((Date) o));
			//我ypr写死了，不好，只是为了显示好看，如果有需要还是之前的好
			p.setText(o.toString().substring(0, o.toString().length()-2));
		} else if (o instanceof Clob) {
			Clob temp = (Clob) o;
			p.setText(readClob(temp));
		} else {
			p.setText(o.toString());
		}

	}

	protected String readClob(Clob clob) throws Exception {
		StringBuffer text = new StringBuffer();

		Reader is = clob.getCharacterStream();

		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		while (s != null) {
			text.append(s);
			s = br.readLine();

		}

		return text.toString();

	}

	protected String getFieldScriptValue(Object ds, String field,
			Trpreportdtl rf, ReportRequest rr) throws Exception {
		Object result = null;
		try {
			if (ds instanceof java.util.Map) {
				Map map = (Map) ds;
				Object tempfield = map.get(field);
				Interpreter inter = new Interpreter();
				inter.set("record", map);
				inter.set("dao", this.dao);
				inter.set("rr", rr);
				inter.set("nameManager", this.nameManager);
				inter.eval(rf.getJavacontent());
				result = inter.get("result");
				if (result == null) {
					return "";
				} else {
					return result.toString();
				}
			}
		} catch (Exception e) {
			SystemLogger.error("执行Java脚本时发生错误!", e);
		}
		return "";
	}

	protected String getVariableValue(ReportRequest rr, String variable) {
		String result = null;
		Object o = rr.gettAttribute(variable);
		if (o == null) {
			o = rr.getContext().getRequest().getParameter(variable);
		}
		result = String.valueOf(o);
		if (result == null) {
			return "";
		} else {
			return result;
		}
	}

	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public NameManager getNameManager() {
		return nameManager;
	}

	public void setNameManager(NameManager nameManager) {
		this.nameManager = nameManager;
	}

}
