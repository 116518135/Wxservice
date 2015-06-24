package com.wxservice.framework.report.datasource;

import java.util.Map;

import com.wxservice.database.po.report.Trpreportdatasource;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.WhereProcessor;

public interface IDatasource {
	public Object query(ReportRequest context, Trpreportdatasource ds,
			WhereProcessor whereProcessor, Map rsMap) throws Exception;

}
