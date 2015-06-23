package com.wxservice.web.form.report;
import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 项目名称：七匹狼运动信息化平台 
 * 创建人：Gererator
 */

public class TrpreportconditionForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TrpreportconditionForm(){
		this.setService("trpreportconditionManager");
		this.setModuleTitle("报表条件");
		this.setAnonymous("0");
	}
	
    Integer  trpreportconditionid;
    Integer  trpreportid;
    String  name;
    String  jsname;
    String  wheres;
    String  htmlcontent;
    Integer  rowindex;
    Integer  colindex;
    Integer  width;
    String  processclass;
    Integer  trpreportpluginid;
    String methodflag;
    
	public String getMethodflag() {
		return methodflag;
	}
	public void setMethodflag(String methodflag) {
		this.methodflag = methodflag;
	}
    public Integer getTrpreportconditionid () {
       return trpreportconditionid;
    }
    public void setTrpreportconditionid(Integer trpreportconditionid) {
       this.trpreportconditionid = trpreportconditionid;
    }
   /**
    * @return 返回 报表id
    */
    public Integer getTrpreportid () {
       return trpreportid;
    }
	
   /**
    * @param trpreportid 要设置的 报表id
    */
    public void setTrpreportid(Integer trpreportid) {
      this.trpreportid = trpreportid;
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
    * @return 返回 JS名称
    */
    public String getJsname () {
       return jsname;
    }
	
   /**
    * @param jsname 要设置的 JS名称
    */
    public void setJsname(String jsname) {
      this.jsname = jsname;
    }
   /**
    * @return 返回 where语句
    */
    public String getWheres () {
       return wheres;
    }
	
   /**
    * @param wheres 要设置的 where语句
    */
    public void setWheres(String wheres) {
      this.wheres = wheres;
    }
   /**
    * @return 返回 Html内容
    */
    public String getHtmlcontent () {
       return htmlcontent;
    }
	
   /**
    * @param htmlcontent 要设置的 Html内容
    */
    public void setHtmlcontent(String htmlcontent) {
      this.htmlcontent = htmlcontent;
    }
   /**
    * @return 返回 行
    */
    public Integer getRowindex () {
       return rowindex;
    }
	
   /**
    * @param rowindex 要设置的 行
    */
    public void setRowindex(Integer rowindex) {
      this.rowindex = rowindex;
    }
   /**
    * @return 返回 列
    */
    public Integer getColindex () {
       return colindex;
    }
	
   /**
    * @param colindex 要设置的 列
    */
    public void setColindex(Integer colindex) {
      this.colindex = colindex;
    }
   /**
    * @return 返回 宽度
    */
    public Integer getWidth () {
       return width;
    }
	
   /**
    * @param width 要设置的 宽度
    */
    public void setWidth(Integer width) {
      this.width = width;
    }
   /**
    * @return 返回 处理类
    */
    public String getProcessclass () {
       return processclass;
    }
	
   /**
    * @param processclass 要设置的 处理类
    */
    public void setProcessclass(String processclass) {
      this.processclass = processclass;
    }
   /**
    * @return 返回 插件
    */
    public Integer getTrpreportpluginid () {
       return trpreportpluginid;
    }
	
   /**
    * @param trpreportpluginid 要设置的 插件
    */
    public void setTrpreportpluginid(Integer trpreportpluginid) {
      this.trpreportpluginid = trpreportpluginid;
    }
    List pluginlist;
	public List getPluginlist() {
		return pluginlist;
	}
	public void setPluginlist(List pluginlist) {
		this.pluginlist = pluginlist;
	}
	List processclasslist;
	public List getProcessclasslist() {
		return processclasslist;
	}
	public void setProcessclasslist(List processclasslist) {
		this.processclasslist = processclasslist;
	}
}


