package com.wxservice.framework.excel.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.excel.ExcelContext;
import com.wxservice.framework.excel.ExcelUtil;
import com.wxservice.framework.util.SystemLogger;
public class SqlExcelProcess extends   SimpleExcelProcess {
	public String generateSql(ExcelContext context, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert ");
		sql.append(context.getMaster().getTablename());
		StringBuffer fields = new StringBuffer();
		StringBuffer values = new StringBuffer();
		for (int i = 0; i < context.getDtllist().size(); i++) {
			Tsysexceldtl detail = (Tsysexceldtl) context.getDtllist().get(i);
			if (i > 0) {
				fields.append(",");
				values.append(",");
			}
			fields.append(detail.getFieldname());
			String value = (String) map
					.get(detail.getFieldname().toLowerCase());
			if ("true".equals(value)) {
				value = "1";
			}
			if ("false".equals(value)) {
				value = "0";
			}
			if (ExcelUtil.datatype_int == detail.getFieldtype()) {
				values.append(value);
			} else if (ExcelUtil.datatype_real == detail.getFieldtype()) {
				values.append(value);
			} else if (ExcelUtil.datatype_date == detail.getFieldtype()) {
				values.append("'");
				values.append(value);
				values.append("'");
			} else {
				values.append("'");
				values.append(value);
				values.append("'");
			}
		}
		sql.append(" (");
		sql.append(fields);
		sql.append(") values (");
		sql.append(values);
		sql.append(")");
		return sql.toString();
	}
	public List execute(ExcelContext context) throws Exception {
		List errorList=new ArrayList();
		try {
			for (int i = 0; i < context.getResults().size(); i++) {
				Map map = (Map) context.getResults().get(i);
				String sql=this.generateSql(context, map);
				try{
				   SystemLogger.info(sql);	
				   dao.script(sql);
				}catch(Exception e){
				 
				}
			}
		}catch(Exception e){
			Map errorMap = new HashMap();
			errorMap.put("type", "数据错误");
			errorMap.put("rows", "");
			errorMap.put("keyvalue","");
			errorMap.put("message", "执行sql语句的时候发生错误");
			errorList.add(errorMap);
			SystemLogger.error(e.getMessage(),e);
		}
		return errorList;
	}
}
