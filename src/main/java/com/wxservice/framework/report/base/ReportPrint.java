package com.wxservice.framework.report.base;

import java.io.Serializable;

public class ReportPrint implements Serializable{
	Band header =new Band();
    Band pageHeader =new Band();
    Band detail =new Band();
    Band footer =new Band();
	public Band getDetail() {
		return detail;
	}
	public void setDetail(Band detail) {
		this.detail = detail;
	}
	public Band getFooter() {
		return footer;
	}
	public void setFooter(Band footer) {
		this.footer = footer;
	}
	public Band getHeader() {
		return header;
	}
	public void setHeader(Band header) {
		this.header = header;
	}
	public Band getPageHeader() {
		return pageHeader;
	}
	public void setPageHeader(Band pageHeader) {
		this.pageHeader = pageHeader;
	}
    
}
