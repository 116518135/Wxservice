package com.wxservice.framework.excel;

import java.util.List;
import java.util.Map;

import jxl.Sheet;

import com.wxservice.database.po.system.Tsysexceldtl;

public interface IExcelField {
	//导出标题
    public List exportTitle(ExcelContext ctx, Tsysexceldtl detail,int row) throws Exception;
    //导出空的单元格
    public List exportValue(ExcelContext ctx, Tsysexceldtl detail,int row) throws Exception;
    //导入值
    public Map importValue(ExcelContext ctx,Tsysexceldtl detail,Sheet sheet,int row,Map valueMap,int plugCol) throws Exception;
    
    public int getRow(ExcelContext ctx, Tsysexceldtl detail) throws Exception;
    public int getCol(ExcelContext ctx, Tsysexceldtl detail) throws Exception;
}
