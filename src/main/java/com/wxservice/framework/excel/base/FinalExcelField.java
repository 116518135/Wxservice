package com.wxservice.framework.excel.base;

import jxl.Sheet;

import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.excel.ExcelContext;

public class FinalExcelField extends  SimpleExcelField {
	
	public String catchValue(ExcelContext ctx, Tsysexceldtl detail, Sheet sheet,int row,int plugCol){
		return detail.getFieldvalue();
	}

}
