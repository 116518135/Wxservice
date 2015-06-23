package com.wxservice.web.form.base;
import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 创建人：Gererator
 */

public class TbsuserForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TbsuserForm(){
		this.setService("tbsuserManager");
		this.setModuleTitle("操作员表");
		this.setAnonymous("1");
	}
	
    Integer  tbsuserid;
    String  code;
    String  name;
    String  passwd;
    String  tsysroleids;
    String  rolenames;
    Integer  stopflag=0;
   

    Integer  passflag=0;

    String oldpasswd;
    String passwd1;
    public String getOldpasswd() {
		return oldpasswd;
	}
	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}
	public String getPasswd1() {
		return passwd1;
	}
	public void setPasswd1(String passwd1) {
		this.passwd1 = passwd1;
	}
	public Integer getTbsuserid () {
       return tbsuserid;
    }
    public void setTbsuserid(Integer tbsuserid) {
       this.tbsuserid = tbsuserid;
    }
   /**
    * @return 返回 编号
    */
    public String getCode () {
       return code;
    }
	
   /**
    * @param code 要设置的 编号
    */
    public void setCode(String code) {
      this.code = code;
    }
   /**
    * @return 返回 名称
    */
    public String getName () {
       return name;
    }
	
   /**
    * @param name 要设置的 名称
    */
    public void setName(String name) {
      this.name = name;
    }
   /**
    * @return 返回 登录密码
    */
    public String getPasswd () {
       return passwd;
    }
	
   /**
    * @param passwd 要设置的 登录密码
    */
    public void setPasswd(String passwd) {
      this.passwd = passwd;
    }
   /**
    * @return 返回 权限组ID
    */
    public String getTsysroleids () {
       return tsysroleids;
    }
	
   /**
    * @param tsysroleids 要设置的 权限组ID
    */
    public void setTsysroleids(String tsysroleids) {
      this.tsysroleids = tsysroleids;
    }
   /**
    * @return 返回 权限组
    */
    public String getRolenames () {
       return rolenames;
    }
	
   /**
    * @param rolenames 要设置的 权限组
    */
    public void setRolenames(String rolenames) {
      this.rolenames = rolenames;
    }
   /**
    * @return 返回 停止使用
    */
    public Integer getStopflag () {
       return stopflag;
    }
	
   /**
    * @param stopflag 要设置的 停止使用
    */
    public void setStopflag(Integer stopflag) {
      this.stopflag = stopflag;
    }
  
public Integer getPassflag() {
	return passflag;
}
public void setPassflag(Integer passflag) {
	this.passflag = passflag;
}
}


