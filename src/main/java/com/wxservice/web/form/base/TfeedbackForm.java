package com.wxservice.web.form.base;
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
		this.setService("tfeedbackManager");
		this.setModuleTitle("用户反馈表");
		this.setAnonymous("0");
	}
	
    Integer  tfeedbackid;
    String  usercode;
    String  content;
    java.util.Date  time;

    public Integer getTfeedbackid () {
       return tfeedbackid;
    }
    public void setTfeedbackid(Integer tfeedbackid) {
       this.tfeedbackid = tfeedbackid;
    }
   /**
    * @return 返回 用户
    */
    public String getUsercode () {
       return usercode;
    }
	
   /**
    * @param usercode 要设置的 用户
    */
    public void setUsercode(String usercode) {
      this.usercode = usercode;
    }
   /**
    * @return 返回 内容
    */
    public String getContent () {
       return content;
    }
	
   /**
    * @param content 要设置的 内容
    */
    public void setContent(String content) {
      this.content = content;
    }
   /**
    * @return 返回 时间
    */
    public java.util.Date getTime () {
       return time;
    }
	
   /**
    * @param time 要设置的 时间
    */
    public void setTime(java.util.Date time) {
      this.time = time;
    }
}


