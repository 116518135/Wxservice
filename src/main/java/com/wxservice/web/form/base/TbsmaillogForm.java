package com.wxservice.web.form.base;
import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 创建人：Gererator
 */

public class TbsmaillogForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TbsmaillogForm(){
		this.setService("tbsmaillogManager");
		this.setModuleTitle("邮箱激活日志");
		this.setAnonymous("1");
	}
	
    Integer  tbsmaillog;
    String  mail;
    Integer  status;
    String  validatecode;
    String code;
    java.util.Date  logdate;

    public Integer getTbsmaillog () {
       return tbsmaillog;
    }
    public void setTbsmaillog(Integer tbsmaillog) {
       this.tbsmaillog = tbsmaillog;
    }
    
   public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
/**
    * @return 返回 邮箱
    */
    public String getMail () {
       return mail;
    }
	
   /**
    * @param mail 要设置的 邮箱
    */
    public void setMail(String mail) {
      this.mail = mail;
    }
   /**
    * @return 返回 绑定标志
    */
    public Integer getStatus () {
       return status;
    }
	
   /**
    * @param status 要设置的 绑定标志
    */
    public void setStatus(Integer status) {
      this.status = status;
    }
   /**
    * @return 返回 激活码
    */
    public String getValidatecode () {
       return validatecode;
    }
	
   /**
    * @param validatecode 要设置的 激活码
    */
    public void setValidatecode(String validatecode) {
      this.validatecode = validatecode;
    }
   /**
    * @return 返回 激活时间时间
    */
    public java.util.Date getLogdate () {
       return logdate;
    }
	
   /**
    * @param logdate 要设置的 激活时间时间
    */
    public void setLogdate(java.util.Date logdate) {
      this.logdate = logdate;
    }
}


