package com.wxservice.web.form.system;
import java.util.List;

import org.apache.struts.upload.FormFile;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 创建人：Gererator
 */

public class AboutForm extends BaseForm
{
	private static final long serialVersionUID = -1L;
	public AboutForm(){
		this.setService("aboutManager");
		this.setModuleTitle("");
		this.setAnonymous("0");
	}
	long totalMemory;
	long freeMemory;
	long maxMemory;
	long processcount;
	String version;
	String versiontype;
	String versionname;
	String begindate;
	String enddate;
	String host;
	FormFile formFile=null;
	String software_version;
	public long getTotalMemory() {
		return totalMemory;
	}
	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}
	public long getFreeMemory() {
		return freeMemory;
	}
	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}
	public long getMaxMemory() {
		return maxMemory;
	}
	public void setMaxMemory(long maxMemory) {
		this.maxMemory = maxMemory;
	}
	public long getProcesscount() {
		return processcount;
	}
	public void setProcesscount(long processcount) {
		this.processcount = processcount;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVersiontype() {
		return versiontype;
	}
	public void setVersiontype(String versiontype) {
		this.versiontype = versiontype;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public FormFile getFormFile() {
		return formFile;
	}
	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
	public String getVersionname() {
		return versionname;
	}
	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getSoftware_version() {
		return software_version;
	}
	public void setSoftware_version(String software_version) {
		this.software_version = software_version;
	}
}


