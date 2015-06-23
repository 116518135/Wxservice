package com.wxservice.framework.excel.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;

import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.excel.ExcelContext;
import com.wxservice.framework.excel.ExcelUtil;
import com.wxservice.framework.excel.IExcelField;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.service.NameManager;

public abstract class SimpleExcelField implements IExcelField {
	IDao dao = null;
	NameManager nameManager = null;

	@Override
	public List exportTitle(ExcelContext ctx,Tsysexceldtl detail,int row) throws Exception {
		List result=new ArrayList();
		ExcelBean bean=new ExcelBean();
		bean.setRow(row);
		bean.setCol(detail.getColindex());
		bean.setWidth(detail.getColwidth());
		bean.setMergeheights(0);
		bean.setMergewidths(0);
		bean.setText(detail.getFieldtitle());
		bean.setDatatype(detail.getFieldtype());
		result.add(bean);
		return result;
	}

	@Override
	public List exportValue(ExcelContext ctx, Tsysexceldtl detail, int row) throws Exception {
		List result=new ArrayList();
		ExcelBean bean=new ExcelBean();
		bean.setRow(row);
		bean.setCol(detail.getColindex());
		bean.setWidth(detail.getColwidth());
		bean.setMergeheights(0);
		bean.setMergewidths(0);
		bean.setText("");
		bean.setDatatype(detail.getFieldtype());
		result.add(bean);
		return result;
	}

	@Override
	public Map importValue(ExcelContext ctx, Tsysexceldtl detail, Sheet sheet,
			int row, Map valueMap,int plugCol) throws Exception {
		Map errorMap=null;
		String value=this.catchValue(ctx, detail, sheet,row,plugCol);
		errorMap=this.check(detail, value, row);
		valueMap.put(detail.getFieldname().toLowerCase(),value);
		return errorMap;
	}
	public abstract String catchValue(ExcelContext ctx, Tsysexceldtl detail, Sheet sheet,int row,int plugCol);

	public Map check(Tsysexceldtl detail, String value, int row) {
		Map errorMap = new HashMap();
		if (ExcelUtil.datatype_int == detail.getFieldtype()) {
			if (StringUtil.isBlank(value)) {
				value = "0";
			}
			try {
				Integer nv = Integer.valueOf(value);
			} catch (Exception e) {
				errorMap.put("type", "数据类型错误");
				errorMap.put("rows",row);
				errorMap.put("keyvalue", value);
				errorMap.put("message", "当前单元格应该写入整数:"+detail.getFieldtitle());
			}
		} else if (ExcelUtil.datatype_real == detail.getFieldtype()) {
			if (StringUtil.isBlank(value)) {
				value = "0";
			}
			try {
				Double nv = Double.valueOf(value);
			} catch (Exception e) {
				errorMap.put("type", "数据类型错误");
				errorMap.put("rows", row);
				errorMap.put("keyvalue", value);
				errorMap.put("message", "当前单元格应该写入实数:"+detail.getFieldtitle());
			}
		} else if (ExcelUtil.datatype_date == detail.getFieldtype()) {
			if (StringUtil.isBlank(value)) {
				value= "";
			}
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date nv = df.parse(value);
			} catch (Exception e) {

				errorMap.put("type", "数据类型错误");
				errorMap.put("rows", row);
				errorMap.put("keyvalue", value);
				errorMap.put("message", "当前单元格应该写入日期,格式为：yyyy-MM-dd:"+detail.getFieldtitle());

			}
		} else {

		}
		return errorMap;

	}

	@Override
	public int getCol(ExcelContext ctx, Tsysexceldtl detail) throws Exception {
		return 1;
	}

	@Override
	public int getRow(ExcelContext ctx, Tsysexceldtl detail) throws Exception {
		return 1;
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
