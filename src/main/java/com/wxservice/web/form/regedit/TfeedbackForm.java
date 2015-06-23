package com.wxservice.web.form.regedit;
import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 创建人：Gererator
 */

public class TfeedbackForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TfeedbackForm(){
		//this.setService("tfeedbackManager");
		this.setModuleTitle("反馈信息");
		this.setAnonymous("1");
	}
	
    Integer  tfeedbackid;
    String  usercode;
    String  content;
    java.util.Date time;
	
    public Integer getTfeedbackid() {
		return tfeedbackid;
	}
	public void setTfeedbackid(Integer tfeedbackid) {
		this.tfeedbackid = tfeedbackid;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
    
	
	
	
  

}


