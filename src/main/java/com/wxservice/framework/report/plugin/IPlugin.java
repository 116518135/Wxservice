package com.wxservice.framework.report.plugin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;


public interface  IPlugin extends Serializable {
	public static String filed_prifx="field";
    public List getTitleList(int beginCol, int beginRow, ReportRequest rr, Trpreportdtl el) throws Exception ;
    public List getValueList(int beginCol, int beginRow, Map model, ReportRequest rr, Trpreportdtl el) throws Exception ;
    public int getCol( ReportRequest rr,
    		Trpreportdtl el);
    public int getRow( ReportRequest rr,
    		Trpreportdtl el);
    public String toHeaderHtml(Map ds,DataElement e,ReportRequest rr, Trpreportdtl el);
    public String toValueHtml(Map ds,DataElement e,ReportRequest rr, Trpreportdtl el);
    public boolean isTable();
    public String getWidthPer();
    public abstract Map<String, String> processValue(Trpreportcondition condition,ReportRequest rr);
}
