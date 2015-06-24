package com.wxservice.web.form.base;

import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

public class SelectForm extends BaseForm{
	public SelectForm(){
		this.setService("selectManager");
		this.setModuleTitle("");
		this.setAnonymous("0");
	}
	private String condition1;
	private String condition2;
	private String condition3;
	private String condition4;
	private String condition5;
	private String condition6;
	private String condition7;
	private String condition8;
	private String condition9;
	private String condition10;
	private String object; // 对于公司是
	private String objecttype; // 对于公司来说. 0:是公司, 1:是经销商    对于店铺来说 0:取本级店铺，1取下级店铺
	private String cmpid=null;
	private List list1 = null;
	private List list2 = null;
	private List list3 = null;
	private String querystring;
	private String mapping;
	// 用于多选的时候,需要
	private String multiname = "";
	private String multivalue = "";
	private String table="";
	private int limitcmpflag=0;//是否限制公司
	
	public int getLimitcmpflag() {
		return limitcmpflag;
	}
	public void setLimitcmpflag(int limitcmpflag) {
		this.limitcmpflag = limitcmpflag;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getCondition1() {
		return condition1;
	}
	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}
	public String getCondition2() {
		return condition2;
	}
	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}
	public String getCondition3() {
		return condition3;
	}
	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}
	public String getCondition4() {
		return condition4;
	}
	public void setCondition4(String condition4) {
		this.condition4 = condition4;
	}
	public String getCondition5() {
		return condition5;
	}
	public void setCondition5(String condition5) {
		this.condition5 = condition5;
	}
	public String getCondition6() {
		return condition6;
	}
	public void setCondition6(String condition6) {
		this.condition6 = condition6;
	}
	public String getCondition7() {
		return condition7;
	}
	public void setCondition7(String condition7) {
		this.condition7 = condition7;
	}
	public String getCondition8() {
		return condition8;
	}
	public void setCondition8(String condition8) {
		this.condition8 = condition8;
	}
	public String getCondition9() {
		return condition9;
	}
	public void setCondition9(String condition9) {
		this.condition9 = condition9;
	}
	public String getCondition10() {
		return condition10;
	}
	public void setCondition10(String condition10) {
		this.condition10 = condition10;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getObjecttype() {
		return objecttype;
	}
	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
	public String getCmpid() {
		return cmpid;
	}
	public void setCmpid(String cmpid) {
		this.cmpid = cmpid;
	}
	public List getList1() {
		return list1;
	}
	public void setList1(List list1) {
		this.list1 = list1;
	}
	public List getList2() {
		return list2;
	}
	public void setList2(List list2) {
		this.list2 = list2;
	}
	public List getList3() {
		return list3;
	}
	public void setList3(List list3) {
		this.list3 = list3;
	}
	public String getQuerystring() {
		return querystring;
	}
	public void setQuerystring(String querystring) {
		this.querystring = querystring;
	}
	public String getMultiname() {
		return multiname;
	}
	public void setMultiname(String multiname) {
		this.multiname = multiname;
	}
	public String getMultivalue() {
		return multivalue;
	}
	public void setMultivalue(String multivalue) {
		this.multivalue = multivalue;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	
}
