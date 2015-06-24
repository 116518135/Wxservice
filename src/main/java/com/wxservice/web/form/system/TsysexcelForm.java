package com.wxservice.web.form.system;
import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述�?  Struts Form
 * 创建人：Gererator
 */

public class TsysexcelForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TsysexcelForm(){
		this.setService("tsysexcelManager");
		this.setModuleTitle("EXCEL导入配置");
		this.setAnonymous("0");
	}
	
    Integer  tsysexcelid;
    String  code;
    String  name;
    String  processclass;
    String  tablename;
    String  tablekey;
    Integer  checkflag=0;
    String  springname;

    Integer  tsysexceldtlid;
    String  fieldname;
    Integer  fieldtype;
    String  fieldtitle;
    String  fieldprocess;
    Integer  colindex;
    Integer  colwidth;
    String  fieldvalue;
    /**
     *下拉�?
     */
    List processclasslist;
    List fieldtypelist;
    List fieldprocesslist;
    public List getFieldtypelist() {
		return fieldtypelist;
	}
	public void setFieldtypelist(List fieldtypelist) {
		this.fieldtypelist = fieldtypelist;
	}
	public List getFieldprocesslist() {
		return fieldprocesslist;
	}
	public void setFieldprocesslist(List fieldprocesslist) {
		this.fieldprocesslist = fieldprocesslist;
	}
	public List getProcessclasslist() {
		return processclasslist;
	}
	public void setProcessclasslist(List processclasslist) {
		this.processclasslist = processclasslist;
	}
	public Integer getTsysexceldtlid () {
       return tsysexceldtlid;
    }
    public void setTsysexceldtlid(Integer tsysexceldtlid) {
       this.tsysexceldtlid = tsysexceldtlid;
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
    * @return 返回 处理�?
    */
    public String getProcessclass () {
       return processclass;
    }
	
   /**
    * @param processclass 要设置的 处理�?
    */
    public void setProcessclass(String processclass) {
      this.processclass = processclass;
    }
   /**
    * @return 返回 表名
    */
    public String getTablename () {
       return tablename;
    }
	
   /**
    * @param tablename 要设置的 表名
    */
    public void setTablename(String tablename) {
      this.tablename = tablename;
    }
   /**
    * @return 返回 主键
    */
    public String getTablekey () {
       return tablekey;
    }
	
   /**
    * @param tablekey 要设置的 主键
    */
    public void setTablekey(String tablekey) {
      this.tablekey = tablekey;
    }
   /**
    * @return 返回 �?��标志
    */
    public Integer getCheckflag () {
       return checkflag;
    }
	
   /**
    * @param checkflag 要设置的 �?��标志
    */
    public void setCheckflag(Integer checkflag) {
      this.checkflag = checkflag;
    }
   /**
    * @return 返回 业务�?
    */
    public String getSpringname () {
       return springname;
    }
	
   /**
    * @param springname 要设置的 业务�?
    */
    public void setSpringname(String springname) {
      this.springname = springname;
    }

   /**
    * @return 返回 主表ID
    */
    public Integer getTsysexcelid () {
       return tsysexcelid;
    }
	
   /**
    * @param tsysexcelid 要设置的 主表ID
    */
    public void setTsysexcelid(Integer tsysexcelid) {
      this.tsysexcelid = tsysexcelid;
    }
   /**
    * @return 返回 字段名称
    */
    public String getFieldname () {
       return fieldname;
    }
	
   /**
    * @param fieldname 要设置的 字段名称
    */
    public void setFieldname(String fieldname) {
      this.fieldname = fieldname;
    }
   /**
    * @return 返回 字段日型
    */
    public Integer getFieldtype () {
       return fieldtype;
    }
	
   /**
    * @param fieldtype 要设置的 字段日型
    */
    public void setFieldtype(Integer fieldtype) {
      this.fieldtype = fieldtype;
    }
   /**
    * @return 返回 字段标题
    */
    public String getFieldtitle () {
       return fieldtitle;
    }
	
   /**
    * @param fieldtitle 要设置的 字段标题
    */
    public void setFieldtitle(String fieldtitle) {
      this.fieldtitle = fieldtitle;
    }
   /**
    * @return 返回 字段处理�?
    */
    public String getFieldprocess () {
       return fieldprocess;
    }
	
   /**
    * @param fieldprocess 要设置的 字段处理�?
    */
    public void setFieldprocess(String fieldprocess) {
      this.fieldprocess = fieldprocess;
    }
   /**
    * @return 返回 列数
    */
    public Integer getColindex () {
       return colindex;
    }
	
   /**
    * @param colindex 要设置的 列数
    */
    public void setColindex(Integer colindex) {
      this.colindex = colindex;
    }
   /**
    * @return 返回 列宽
    */
    public Integer getColwidth () {
       return colwidth;
    }
	
   /**
    * @param colwidth 要设置的 列宽
    */
    public void setColwidth(Integer colwidth) {
      this.colwidth = colwidth;
    }
   /**
    * @return 返回 变量/常量�?
    */
    public String getFieldvalue () {
       return fieldvalue;
    }
	
   /**
    * @param fieldvalue 要设置的 变量/常量�?
    */
    public void setFieldvalue(String fieldvalue) {
      this.fieldvalue = fieldvalue;
    }
}


