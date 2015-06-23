package com.wxservice.web.form.system;
import java.util.ArrayList;
import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

public class TsysmenuForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TsysmenuForm(){
		this.setService("tsysmenuManager");
		this.setModuleTitle("菜单");
		this.setAnonymous("0");
	}
	String id;
    Integer  tsysmenuid;
    Integer  parentid=0;
    String  name;
    String  accessurl;
    Integer indexno;
    String  rightvalue="11222222222221";
    String  cmprightvalue="22000";
    String  memo;
    String  webserver;
    String menuorder=null;
    List menuOptions=new ArrayList();
    List adjOptions=new ArrayList();
    String item;
    public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public List getAdjOptions() {
		return adjOptions;
	}
	public void setAdjOptions(List adjOptions) {
		this.adjOptions = adjOptions;
	}
	public Integer getTsysmenuid () {
       return tsysmenuid;
    }
    public void setTsysmenuid(Integer tsysmenuid) {
       this.tsysmenuid = tsysmenuid;
    }
   /**
    * @return 返回 父菜单ID
    */
    public Integer getParentid () {
       return parentid;
    }
	
   /**
    * @param parentid 要设置的 父菜单ID
    */
    public void setParentid(Integer parentid) {
      this.parentid = parentid;
    }
   /**
    * @return 返回 菜单名称
    */
    public String getName () {
       return name;
    }
	
   /**
    * @param name 要设置的 菜单名称
    */
    public void setName(String name) {
      this.name = name;
    }
   /**
    * @return 返回 资源URL
    */
    public String getAccessurl () {
       return accessurl;
    }
	
   /**
    * @param accessurl 要设置的 资源URL
    */
    public void setAccessurl(String accessurl) {
      this.accessurl = accessurl;
    }
   /**
    * @return 返回 显示顺序
    */
    public Integer getIndexno () {
       return indexno;
    }
	
   /**
    * @param indexno 要设置的 显示顺序
    */
    public void setIndexno(Integer indexno) {
      this.indexno = indexno;
    }
   /**
    * @return 返回 权限字符串
    */
    public String getRightvalue () {
       return rightvalue;
    }
	
   /**
    * @param rightvalue 要设置的 权限字符串
    */
    public void setRightvalue(String rightvalue) {
      this.rightvalue = rightvalue;
    }
   /**
    * @return 返回 组织权限字符串
    */
    public String getCmprightvalue () {
       return cmprightvalue;
    }
	
   /**
    * @param cmprightvalue 要设置的 组织权限字符串
    */
    public void setCmprightvalue(String cmprightvalue) {
      this.cmprightvalue = cmprightvalue;
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
   /**
    * @return 返回 WEB服务器
    */
    public String getWebserver () {
       return webserver;
    }
	
   /**
    * @param webserver 要设置的 WEB服务器
    */
    public void setWebserver(String webserver) {
      this.webserver = webserver;
    }
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getMenuorder() {
	return menuorder;
}
public void setMenuorder(String menuorder) {
	this.menuorder = menuorder;
}
public List getMenuOptions() {
	return menuOptions;
}
public void setMenuOptions(List menuOptions) {
	this.menuOptions = menuOptions;
}
}


