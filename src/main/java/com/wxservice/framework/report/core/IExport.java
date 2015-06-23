package com.wxservice.framework.report.core;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.exception.ExportException;
import com.wxservice.framework.web.action.ActionContext;
public interface IExport {
	   public Object export(ReportRequest ctx) throws ExportException;
	   public Object cache(ReportRequest ctx) throws ExportException;
	   public void export(ReportRequest ctx,String fileName) throws ExportException;
	   public void setDao(IDao dao);
	   public void setActionContext(ActionContext context);
}
