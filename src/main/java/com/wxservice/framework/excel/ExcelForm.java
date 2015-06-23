package com.wxservice.framework.excel;

import org.apache.struts.upload.FormFile;

import com.wxservice.framework.web.form.BaseForm;

public class ExcelForm  extends BaseForm{
	FormFile formFile=null;
	String billno=null;
	String code=null;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ExcelForm() {
		this.setService("tsysexcelManager");
		this.setAnonymous("0");
	}
	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public FormFile getFormFile() {
		return formFile;
	}

	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
	
}
