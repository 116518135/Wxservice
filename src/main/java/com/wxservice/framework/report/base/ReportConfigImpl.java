package com.wxservice.framework.report.base;

import java.util.ArrayList;
import java.util.List;

import com.wxservice.database.po.report.Trpreport;




public class ReportConfigImpl implements ReportConfig {

	private static final long serialVersionUID = 6943080030944065260L;

	List condition = new ArrayList();
	List plugin=new ArrayList();

	List datasource = new ArrayList();

	List header = new ArrayList();

	List detail = new ArrayList();

	List footer = new ArrayList();
	Trpreport report = null;
	public List getCondition() {
		return condition;
	}
	public void setCondition(List condition) {
		this.condition = condition;
	}
	public List getDatasource() {
		return datasource;
	}
	public void setDatasource(List datasource) {
		this.datasource = datasource;
	}
	public List getHeader() {
		return header;
	}
	public void setHeader(List header) {
		this.header = header;
	}
	public List getDetail() {
		return detail;
	}
	public void setDetail(List detail) {
		this.detail = detail;
	}
	public List getFooter() {
		return footer;
	}
	public void setFooter(List footer) {
		this.footer = footer;
	}
	public Trpreport getReport() {
		return report;
	}
	public void setReport(Trpreport report) {
		this.report = report;
	}
	public List getPlugin() {
		return plugin;
	}
	public void setPlugin(List plugin) {
		this.plugin = plugin;
	}




}
