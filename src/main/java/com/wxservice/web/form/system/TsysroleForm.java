package com.wxservice.web.form.system;
import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 创建人：Gererator
 */

public class TsysroleForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TsysroleForm(){
		this.setService("tsysroleManager");
		this.setModuleTitle("权限组");
		this.setAnonymous("0");
	}
	
    Integer  tsysroleid;
    String  code;
    String  name;
    Integer  tbscmpid;
    String cmpname;
   
    Integer  createtbscmpid;
   
    String  memo;
    String authbuf;
    String html;
    List checkboxList;
    protected  Integer publicflag=0;
    public Integer getPublicflag() {
		return publicflag;
	}
	public void setPublicflag(Integer publicflag) {
		this.publicflag = publicflag;
	}
	public String getAuthbuf() {
		return authbuf;
	}
	public void setAuthbuf(String authbuf) {
		this.authbuf = authbuf;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Integer getTsysroleid () {
       return tsysroleid;
    }
    public void setTsysroleid(Integer tsysroleid) {
       this.tsysroleid = tsysroleid;
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
    * @return 返回 所属组织
    */
    public Integer getTbscmpid () {
       return tbscmpid;
    }
	
   /**
    * @param tbscmpid 要设置的 所属组织
    */
    public void setTbscmpid(Integer tbscmpid) {
      this.tbscmpid = tbscmpid;
    }
   /**
    * @return 返回 创建人
    */
   
   /**
    * @return 返回 创建人所属组织
    */
    public Integer getCreatetbscmpid () {
       return createtbscmpid;
    }
	
   /**
    * @param createtbscmpid 要设置的 创建人所属组织
    */
    public void setCreatetbscmpid(Integer createtbscmpid) {
      this.createtbscmpid = createtbscmpid;
    }
 
   /**
    * @return 返回 备注
    */
    public String getMemo () {
       return memo;
    }
	
   /**
    * @param memo 要设置的 备注
    */
    public void setMemo(String memo) {
      this.memo = memo;
    }
public String getCmpname() {
	return cmpname;
}
public void setCmpname(String cmpname) {
	this.cmpname = cmpname;
}
public List getCheckboxList() {
	return checkboxList;
}
public void setCheckboxList(List checkboxList) {
	this.checkboxList = checkboxList;
}
}


