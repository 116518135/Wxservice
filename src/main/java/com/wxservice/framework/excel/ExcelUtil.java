package com.wxservice.framework.excel;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.wxservice.database.po.system.Tsysexcel;
import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.engine.BaseEngine;
import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.session.ClientSession;

public class ExcelUtil {
	public static int datatype_int = 0;
	public static int datatype_string = 1;
	public static int datatype_real = 2;
	public static int datatype_date = 3;
	final public static int BEGINROW = 3;

	public static List<LabelValue> getDatatypeOptions() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		options.add(new LabelValue("整数", datatype_int));
		options.add(new LabelValue("字符串", datatype_string));
		options.add(new LabelValue("实数", datatype_real));
		options.add(new LabelValue("日期", datatype_date));
		return options;
	}

	public static String getDatatypeName(int value) {
		for (LabelValue v : getDatatypeOptions()) {
			if (value == v.getIvalue()) {
				return v.getLabel();
			}
		}
		return "";
	}
	
	public static List<LabelValue> getFieldProcessOptions() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		options.add(new LabelValue("单元格处理", "cellExcelField"));
		options.add(new LabelValue("变量处理", "varExcelField"));
		options.add(new LabelValue("常量处理", "finalExcelField"));
		return options;
	}

	public static String getFieldProcessName(String value) {
		for (LabelValue v : getFieldProcessOptions()) {
			if (value.equals(v.getValue())) {
				return v.getLabel();
			}
		}
		return "";
	}
	
	public static List<LabelValue> getExcelProcessOptions() {
		List<LabelValue> options = new ArrayList<LabelValue>();	
		options.add(new LabelValue("Hibernate处理类", "springExcelProcess"));
		options.add(new LabelValue("Sql处理类", "sqlExcelProcess"));
		options.add(new LabelValue("店铺处理类", "storeExcelProcess"));
		options.add(new LabelValue("操作员处理类", "userExcelProcess"));
		return options;
	}
	
	public static String getExcelProcessName(String value) {
		for (LabelValue v : getExcelProcessOptions()) {
			if (value.equals(v.getValue())) {
				return v.getLabel();
			}
		}
		return "";
	}
	

	public static InputStream read(ExcelForm form) throws Exception {
		return form.getFormFile().getInputStream();
	}

	public static String getContent(Sheet sheet, int row, int col) {
		if (col < 0)
			return "";
		String result = "";
		if (sheet != null) {
			Cell[] rowcell = sheet.getRow(row);
			Object[] obj = (Object[]) rowcell;
			if (obj == null)
				return "";
			if (obj.length < col + 1)
				return "";
			Object xxx = obj[col];
			if (xxx == null)
				return "";
			String temp = ((Cell) xxx).getContents();
			result = StringUtil.notNull(temp);
		}
		return result;
	}
	
	public static  ExcelContext createExcelContext(ActionContext context){
		ExcelContext ctx=new ExcelContext();
		if(context.getForm() instanceof ExcelForm ){
			ctx.setForm((ExcelForm)context.getForm());
		}else{
			ExcelForm form=new ExcelForm();
			ctx.setForm(form);
		}
		ClientSession client=WebUtil.getClientSession(context.getRequest());
		ctx.setClient(client);
		ctx.setRequest(context.getRequest());
		ctx.getVars().put("$userid", client.getUserid());
		ctx.getVars().put("$cmpid", client.getCmpid());
		ctx.getVars().put("$username",client.getUsername());
		ctx.getVars().put("$today", DateUtil.formatCurrentDayStart());
		return ctx;
	}

	
}
