package com.wxservice.web.form.base;

import com.wxservice.framework.web.form.BaseForm;

public class LoginForm extends BaseForm {
	String cmpcode;
	String usercode;
	String deptcode;
	String passwd;
	String randomcode;
	String licenseno;
	String errormessage;
	String dstype;
	

	public String getDstype() {
		return dstype;
	}

	public void setDstype(String dstype) {
		this.dstype = dstype;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public LoginForm() {
		setAnonymous("1");
		this.setService("loginManager");
		this.setModuleTitle("登录");
	}

	public String getCmpcode() {
		return cmpcode;
	}

	public void setCmpcode(String cmpcode) {
		this.cmpcode = cmpcode;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRandomcode() {
		return randomcode;
	}

	public void setRandomcode(String randomcode) {
		this.randomcode = randomcode;
	}

}
