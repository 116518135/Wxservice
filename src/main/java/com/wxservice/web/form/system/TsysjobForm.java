package com.wxservice.web.form.system;
import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 创建人：Gererator
 */

public class TsysjobForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TsysjobForm(){
		this.setService("tsysjobManager");
		this.setModuleTitle("调度对象表");
		this.setAnonymous("0");
	}
	
    Integer  tsysjobid;
    String  code;
    String  name;
    String  springname;
    String  memo;

    public Integer getTsysjobid () {
       return tsysjobid;
    }
    public void setTsysjobid(Integer tsysjobid) {
       this.tsysjobid = tsysjobid;
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
    * @return 返回 业务对象名
    */
    public String getSpringname () {
       return springname;
    }
	
   /**
    * @param springname 要设置的 业务对象名
    */
    public void setSpringname(String springname) {
      this.springname = springname;
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
}


