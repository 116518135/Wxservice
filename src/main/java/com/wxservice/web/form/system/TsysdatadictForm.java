package com.wxservice.web.form.system;
import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 创建人：Gererator
 */

public class TsysdatadictForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TsysdatadictForm(){
		this.setService("tsysdatadictManager");
		this.setModuleTitle("数据词典");
		this.setAnonymous("0");
	}
	
    Integer  tsysdatadictid;
    String  code;
    String  name;
    String  memo;
  

    Integer  tsysdatadictdtlid;

    public Integer getTsysdatadictdtlid () {
       return tsysdatadictdtlid;
    }
    public void setTsysdatadictdtlid(Integer tsysdatadictdtlid) {
       this.tsysdatadictdtlid = tsysdatadictdtlid;
    }
	public Integer getTsysdatadictid() {
		return tsysdatadictid;
	}
	public void setTsysdatadictid(Integer tsysdatadictid) {
		this.tsysdatadictid = tsysdatadictid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

 
}


