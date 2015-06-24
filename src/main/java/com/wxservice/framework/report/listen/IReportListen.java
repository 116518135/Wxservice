package com.wxservice.framework.report.listen;

import java.util.Map;



import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportdatasource;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.WhereProcessor;
import com.wxservice.framework.report.html.HtmlProcess;


public interface IReportListen {
	//执行每个sql查询之前调用
	boolean beforeQuery(ReportRequest context,Trpreportdatasource ds,WhereProcessor whereProcessor,Object rs)throws Exception;
	//执行每个sql查询之后调用
	Object afterQuery(ReportRequest context,Trpreportdatasource ds,Object rs)throws Exception;
	//数据源
	void beforeDatasource(ReportRequest context)throws Exception;
	void afterDatasource(ReportRequest context)throws Exception;
	//报表头
	void beforeHeader(ReportRequest context)throws Exception;
	void afterHeader(ReportRequest context)throws Exception;	
	//报表列头
	void beforePageHeader(ReportRequest context)throws Exception;
	void afterPageHeader(ReportRequest context)throws Exception;
	//报表明细
	void beforePageDetail(ReportRequest context)throws Exception;
	void afterPageDetail(ReportRequest context)throws Exception;
	
	//报表尾
	void beforePageFooter(ReportRequest context)throws Exception;
	void afterPageFooter(ReportRequest context)throws Exception;
	
	//where条件
	void beforeWhere(ReportRequest context)throws Exception;
	void afterWhere(ReportRequest context)throws Exception;
	
	//报表Html控件
	boolean beforeReportCtrl(ReportRequest context,Trpreportcondition condition,HtmlProcess hp,Map<String, String> map)throws Exception;
	void afterReportCtrl(ReportRequest context)throws Exception;
	
	//sql的每行
	void beforeRow(ReportRequest context,WhereProcessor whereProcessor,Map ds)throws Exception;
	void afterRow(ReportRequest context,WhereProcessor whereProcessor,Map ds)throws Exception;
}
