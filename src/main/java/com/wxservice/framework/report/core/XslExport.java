package com.wxservice.framework.report.core;

import java.io.File;
import java.io.IOException;

import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



import org.springframework.util.ReflectionUtils;

import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.base.ReportPrint;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.exception.ExportException;
import com.wxservice.framework.report.exception.FillException;
import com.wxservice.framework.util.SysFinal;

public class XslExport extends XslProcessor {
	public Object export(ReportRequest ctx) throws ExportException {

		try {
			ReportConfig rc = ctx.getRc();
			FillService fs = new FillService(ctx);
			ReportPrint rp = fs.fillReport();
			// this.saveRc(rp, rc, ctx.getContext().getForm(),
			// ctx.getContext().getRequest());
			WritableWorkbook workbook = this.getWorkbook(ctx);
			WritableSheet s = workbook.createSheet("报表数据", 0);
			ctx.setWorkbook(workbook);
			printHeader(s, rp);
			printPageHeader(rp, ctx);
			printDetail(rp, ctx);
			int idx = printFooter(rp, ctx);
			ctx.getWorkbook().write();
			ctx.getWorkbook().close();
			rp = null;
			byte[] buffer = zip(ctx);
			return buffer;

		} catch (FillException e) {
			ReflectionUtils.handleReflectionException(e);
			throw new ExportException(e.getError());
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
			throw new ExportException(e.getMessage());
		}
	}

	public Object cache(ReportRequest ctx) throws ExportException {
		try {
			System.out.println(34235423);
			ReportConfig rc = ctx.getRc();
			ReportPrint rp = this.loadRc(rc, ctx.getContext().getForm(), ctx
					.getContext().getRequest());
			WritableWorkbook workbook = this.getWorkbook(ctx);
			WritableSheet s = workbook.createSheet("报表数据", 0);
			ctx.setWorkbook(workbook);
			printHeader(s, rp);
			printPageHeader(rp, ctx);
			printDetail(rp, ctx);
			int idx = printFooter(rp, ctx);
			ctx.getWorkbook().write();
			ctx.getWorkbook().close();
			rp = null;
			byte[] buffer = zip(ctx);

			return buffer;

		} catch (FillException e) {
			ReflectionUtils.handleReflectionException(e);
			throw new ExportException(e.getError());
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
			throw new ExportException(e.getMessage());
		}
	}

	
	

}
