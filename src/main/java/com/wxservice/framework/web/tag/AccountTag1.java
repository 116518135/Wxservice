package com.wxservice.framework.web.tag;
/*
#是否使用多帐套功能
esk.account.useflag=0
#帐套列表：注意webUrl要以Http打头
#格式为：服务器地址,服务器名称，当前是否默认webUrl,webName,false|webUrl,webName,true 
esk.account.list=http://127.0.0.1:8888/esk,帐套1,true|http://127.0.0.1:8888/esk/,帐套2,false
*/	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.BaseFieldTag;
import org.apache.struts.util.ResponseUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wxservice.framework.engine.support.SysConfig;
import com.wxservice.framework.util.HtmlBuilder;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.service.ServiceConfig;
public class AccountTag1 extends BaseFieldTag {
	
	public static final String account_useflag="wxservice.account.useflag";
	public static final String account_list="wxservice.account.list";
	public static final String key_url="url";
	public static final String key_name="name";
	public static final String key_defaultflag="defaultflag";
	    public List<Map> getAccountList() throws Exception{
	    	List<Map> result=new ArrayList<Map>();
	    	String set_temp=SysConfig.getStringConfig(account_list);
	    	String set_str=new String(set_temp.getBytes("ISO-8859-1"),"UTF-8"); 
	    	String[] arr=StringUtil.split(set_str,"|");
	    	for(String str:arr){
	    		String[] temp=str.split(",");
	    		if(temp.length==3){
	    			Map<String,String> map=new HashMap<String,String>();
	    			String url=temp[0];
	    			String name=temp[1];
	    			String defaultflag=temp[2];
	    			map.put(key_url, url);
	    			map.put(key_name, name);
	    			map.put(key_defaultflag, defaultflag);
	    			result.add(map);
	    		}
	    	}
	    	return result;
	    }
		public int doStartTag() throws JspException {
			Object value = null;
			try {
				int flag=SysConfig.getIntConfig(account_useflag);
				if(flag==1){
					List<Map> accountlist=this.getAccountList();
					if(accountlist.size()>0){
						HtmlBuilder html=new HtmlBuilder();
						html.append("<DIV class='fm-item'><LABEL class='fm-label'>帐套</LABEL>");
						html.newline();
						html.append("<select name='accountselect' class='i-text' ");
						html.onchange("changeAccount()");
						html.append(">");
						for(Map<String,String> map:accountlist){
							html.newline();
							String url=map.get(key_url);
							String name=map.get(key_name);
							String defaultflag=map.get(key_defaultflag);
							html.option();
							html.value(url);
							if("true".equalsIgnoreCase(defaultflag)){
								html.selected();
							}
							html.append(">");
							html.append(name);
							html.optionEnd();
						}
						html.newline();
						html.append("</select>");
						html.newline();
						html.append("</div>");
						ResponseUtils.write(pageContext, html.toString());
					}
				}
			} catch (Exception e) {

			}
			return (EVAL_BODY_TAG);
		}

		public void release() {

		}
}
