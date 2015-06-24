package com.wxservice.framework.report.core;

import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.base.ReportPrint;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.table.Assembler;
import com.wxservice.framework.report.exception.ExportException;
import com.wxservice.framework.report.exception.FillException;
import com.wxservice.framework.util.SystemLogger;

public class HtmlExport extends  SimpleExport{
    Assembler assembler=null;
	public Object export(ReportRequest ctx) throws ExportException {
		try {
			long begin=System.currentTimeMillis();
			ReportConfig rc = ctx.getRc();
			FillService fs = new FillService(ctx);
			ReportPrint rp = fs.fillReport();
			this.saveRc(rp, rc, ctx.getContext().getForm(), ctx.getContext().getRequest());
			String content=assembler.build(ctx, rp);
			rp=null;
			fs.release();
			
			fs=null;
			long end=System.currentTimeMillis();
			StringBuffer msg=new StringBuffer();
			msg.append("报表生成时间:");
			msg.append(end-begin);
			SystemLogger.info(msg.toString());
			return content;

		} catch (FillException e) {
			SystemLogger.error("填充报表的时候发生错误",e);
			
			throw new ExportException(e.getError());
		} catch (Exception e) {
			SystemLogger.error("填充报表的时候发生未知错误",e);
			throw new ExportException(e.getMessage());
		}
	}
	public Object cache(ReportRequest ctx) throws ExportException {
		try {
			ReportConfig rc = ctx.getRc();
			ReportPrint rp = this.loadRc(rc, ctx.getContext().getForm(), ctx.getContext().getRequest());
			String content=assembler.build(ctx, rp);
			rp=null;
			return content;

		} catch (FillException e) {
			SystemLogger.error("填充报表的时候发生错误",e);
			throw new ExportException(e.getError());
		} catch (Exception e) {
			SystemLogger.error("填充报表的时候发生未知错误",e);
			throw new ExportException(e.getMessage());
		}
	}
	public void export(ReportRequest ctx, String fileName) throws ExportException {
		// TODO Auto-generated method stub
		
	}

	public Assembler getAssembler() {
		return assembler;
	}

	public void setAssembler(Assembler assembler) {
		this.assembler = assembler;
	}

}
