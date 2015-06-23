package com.wxservice.framework.excel.base;

import jxl.Sheet;

import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.excel.ExcelContext;

public class VarExcelField extends  SimpleExcelField {
	
	public String catchValue(ExcelContext ctx, Tsysexceldtl detail, Sheet sheet,int row,int plugCol){
		Object o=ctx.getVars().get(detail.getFieldvalue());
		if(o==null){
			return "";
		}else{
			return o.toString();
		}
	}

}
