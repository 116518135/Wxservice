package com.wxservice.framework.report.listen;

import java.util.Map;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportdatasource;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.WhereProcessor;
import com.wxservice.framework.report.html.HtmlProcess;

public class SimpleListen implements IReportListen{

	@Override
	public void afterDatasource(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterHeader(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPageDetail(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPageFooter(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPageHeader(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object afterQuery(ReportRequest context, Trpreportdatasource ds,
			Object rs) throws Exception {
		// TODO Auto-generated method stub
		return rs;
	}

	@Override
	public void afterReportCtrl(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRow(ReportRequest context, WhereProcessor whereProcessor,
			Map ds) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterWhere(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeDatasource(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeHeader(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePageDetail(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePageFooter(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePageHeader(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean beforeQuery(ReportRequest context, Trpreportdatasource ds,
			WhereProcessor whereProcessor, Object rs) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean beforeReportCtrl(ReportRequest context,
			Trpreportcondition condition, HtmlProcess hp,
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void beforeRow(ReportRequest context, WhereProcessor whereProcessor,
			Map ds) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeWhere(ReportRequest context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
