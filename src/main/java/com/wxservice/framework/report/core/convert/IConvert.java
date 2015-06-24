package com.wxservice.framework.report.core.convert;

import java.util.Map;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;

public interface IConvert {
	public void convert(Map ds,ReportRequest rr,Trpreportdtl el, DataElement p, int row)
			throws Exception;
	public void setDao(IDao dao);
}
