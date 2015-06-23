package com.wxservice.framework.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.system.Tsysexcel;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.web.session.ClientSession;

public class ExcelContext {
	Tsysexcel master;
	List dtllist;
	ExcelForm form;
	HttpServletRequest request;
	ClientSession client;
	Map vars = new HashMap();
	List results=new ArrayList();
	IExcelProcess excelProcess;
	CRUDOperatorImpl springBean;
	public Tsysexcel getMaster() {
		return master;
	}

	public void setMaster(Tsysexcel master) {
		this.master = master;
	}

	public List getDtllist() {
		return dtllist;
	}

	public void setDtllist(List dtllist) {
		this.dtllist = dtllist;
	}

	public ExcelForm getForm() {
		return form;
	}

	public void setForm(ExcelForm form) {
		this.form = form;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ClientSession getClient() {
		return client;
	}

	public void setClient(ClientSession client) {
		this.client = client;
	}

	public Map getVars() {
		return vars;
	}

	public void setVars(Map vars) {
		this.vars = vars;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	public IExcelProcess getExcelProcess() {
		return excelProcess;
	}

	public void setExcelProcess(IExcelProcess excelProcess) {
		this.excelProcess = excelProcess;
	}

	public CRUDOperatorImpl getSpringBean() {
		return springBean;
	}

	public void setSpringBean(CRUDOperatorImpl springBean) {
		this.springBean = springBean;
	}

	

}
