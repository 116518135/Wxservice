package com.wxservice.web.form.report;
import org.apache.struts.upload.FormFile;

import com.wxservice.framework.web.form.BaseForm;
public class ReportToolForm extends BaseForm{
	public ReportToolForm(){
		this.setService("reporttoolManager");
		this.setModuleTitle("");
		this.setAnonymous("1");
	}
	 private FormFile report;
	 private String overDeploy="0";
	 private String overBuild="0";
	 private String overLoad="0";
	 String  reportServerUrl;
	 String table;
	 String tablename;
	 String deployServer;//
	 String deployReportcode;
	 
	 String reportids;
	 String oldjsname;
	 String newjsname;
	 
	 
	public String getOldjsname() {
		return oldjsname;
	}
	public void setOldjsname(String oldjsname) {
		this.oldjsname = oldjsname;
	}
	public String getNewjsname() {
		return newjsname;
	}
	public void setNewjsname(String newjsname) {
		this.newjsname = newjsname;
	}
	public String getReportids() {
		return reportids;
	}
	public void setReportids(String reportids) {
		this.reportids = reportids;
	}

	public String getDeployServer() {
		return deployServer;
	}
	public void setDeployServer(String deployServer) {
		this.deployServer = deployServer;
	}
	public String getDeployReportcode() {
		return deployReportcode;
	}
	public void setDeployReportcode(String deployReportcode) {
		this.deployReportcode = deployReportcode;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getReportServerUrl() {
			return reportServerUrl;
		}
		public void setReportServerUrl(String reportServerUrl) {
			this.reportServerUrl = reportServerUrl;
		}
	public String getOverBuild() {
		return overBuild;
	}
	public void setOverBuild(String overBuild) {
		this.overBuild = overBuild;
	}
	public String getOverDeploy() {
		return overDeploy;
	}
	public void setOverDeploy(String overDeploy) {
		this.overDeploy = overDeploy;
	}
	public String getOverLoad() {
		return overLoad;
	}
	public void setOverLoad(String overLoad) {
		this.overLoad = overLoad;
	}
	public FormFile getReport() {
		return report;
	}
	public void setReport(FormFile report) {
		this.report = report;
	}
}
