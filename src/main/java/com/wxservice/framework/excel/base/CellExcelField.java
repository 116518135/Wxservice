package com.wxservice.framework.excel.base;

import jxl.Sheet;

import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.excel.ExcelContext;
import com.wxservice.framework.excel.ExcelUtil;

public class CellExcelField extends  SimpleExcelField {
	
	public String catchValue(ExcelContext ctx, Tsysexceldtl detail, Sheet sheet,int row,int plugCol){
		Object o=ExcelUtil.getContent(sheet, row, detail.getColindex()+plugCol-1);
		if(o==null){
			return "";
		}else{
			return o.toString().trim();
		}
	}

}
