package com.wxservice.web.form.report;
import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 项目名称：七匹狼运动信息化平台 
 * 创建人：Gererator
 */

public class TrpreportdatasourceForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TrpreportdatasourceForm(){
		this.setService("trpreportdatasourceManager");
		this.setModuleTitle("报表数据源");
		this.setAnonymous("0");
	}
	
    Integer  trpreportdatasourceid;
    Integer  trpreportid;
    String  name;
    String  content;
    Integer  datasourcetype;
    String methodflag;
    
	public String getMethodflag() {
		return methodflag;
	}
	public void setMethodflag(String methodflag) {
		this.methodflag = methodflag;
	}
	public Integer getTrpreportdatasourceid () {
       return trpreportdatasourceid;
    }
    public void setTrpreportdatasourceid(Integer trpreportdatasourceid) {
       this.trpreportdatasourceid = trpreportdatasourceid;
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
    * @return 返回 类型
    */
    public Integer getDatasourcetype () {
       return datasourcetype;
    }
	
   /**
    * @param datasourcetype 要设置的 类型
    */
    public void setDatasourcetype(Integer datasourcetype) {
      this.datasourcetype = datasourcetype;
    }
    List datasourcetypelist;
	public List getDatasourcetypelist() {
		return datasourcetypelist;
	}
	public void setDatasourcetypelist(List datasourcetypelist) {
		this.datasourcetypelist = datasourcetypelist;
	}
}


