package com.wxservice.framework.report.base;

import java.util.HashMap;
import java.util.Map;

import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.core.FillService;
import com.wxservice.framework.web.action.ActionContext;

import jxl.write.WritableWorkbook;

public class ReportRequest {
	ReportConfig rc = null;
	ActionContext context = null;
	IDao dao = null;
	FillService fillService = null;
	Map vars = new HashMap();
	int index = 0;
	int rarFlag = 0;
	WritableWorkbook workbook = null;
	Map errormap = new HashMap();

	public Map getErrormap() {
		return errormap;
	}

	public void setErrormap(Map errormap) {
		this.errormap = errormap;
	}

	public WritableWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ActionContext getContext() {
		return context;
	}

	public void setContext(ActionContext context) {
		this.context = context;
	}

	public ReportConfig getRc() {
		return rc;
	}

	public void setRc(ReportConfig rc) {
		this.rc = rc;
	}

	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public FillService getFillService() {
		return fillService;
	}

	public void setFillService(FillService fillService) {
		this.fillService = fillService;
	}

	public void release() {
		this.fillService = null;
		this.rc = null;
	}

	public Map getVars() {
		return vars;
	}

	public void setVars(Map vars) {
		this.vars = vars;
	}

	public void setAttribute(String key, Object o) {
		vars.put(key, o);
	}

	public Object gettAttribute(String key) {
		return vars.get(key);
	}

	public int getRarFlag() {
		return rarFlag;
	}

	public void setRarFlag(int rarFlag) {
		this.rarFlag = rarFlag;
	}

}
