package com.wxservice.framework.web.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.BaseFieldTag;
import org.apache.struts.util.ResponseUtils;

import com.wxservice.framework.engine.support.SysConfig;
import com.wxservice.framework.util.HtmlBuilder;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.WebUtil;

public class AccountTag extends BaseFieldTag {
	public static final String account_useflag = "wxservice.account.useflag";
	public static final String account_list = "wxservice.account.list";
	public static final String key_url = "url";
	public static final String key_name = "name";
	public static final String key_defaultflag = "defaultflag";

	public String doMultidatasourceHtml() {
		HtmlBuilder html = new HtmlBuilder();
		html.append("<DIV class='fm-item'><LABEL class='fm-label'>帐套</LABEL>");
		html.newline();
		html
				.append("<input type='text' name='dstype'  onfocus='select()' class='i-text'/>");
		html.append("</div>");
		return html.toString();
	}

	public List<Map> getAccountList() throws Exception {
		List<Map> result = new ArrayList<Map>();
		String set_temp = SysConfig.getStringConfig(account_list);
		String set_str = new String(set_temp.getBytes("ISO-8859-1"), "UTF-8");
		String[] arr = StringUtil.split(set_str, "|");
		for (String str : arr) {
			String[] temp = str.split(",");
			if (temp.length == 3) {
				Map<String, String> map = new HashMap<String, String>();
				String url = temp[0];
				String name = temp[1];
				String defaultflag = temp[2];
				map.put(key_url, url);
				map.put(key_name, name);
				map.put(key_defaultflag, defaultflag);
				result.add(map);
			}
		}
		return result;
	}

	public String doAccountListHtml() throws Exception {
		List<Map> accountlist = getAccountList();
		HtmlBuilder html = new HtmlBuilder();
		html.append("<DIV class='fm-item'><LABEL class='fm-label'>帐套</LABEL>");
		html.newline();
		html.append("<select name='accountselect' class='i-text' ");
		html.onchange("changeAccount()");
		html.append(">");
		for (Map<String, String> map : accountlist) {
			html.newline();
			String url = map.get(key_url);
			String name = map.get(key_name);
			String defaultflag = map.get(key_defaultflag);
			html.option();
			html.value(url);
			if ("true".equalsIgnoreCase(defaultflag)) {
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
		return html.toString();
	}

	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest) this.pageContext
					.getRequest();
			int flag = SysUtil.getDsTypeflag();
			// flag=0:不使用多帐套
			// flag=1:使用url来代替多帐套,一个url表示一个帐套.
			// flag=2:使用多个数据库方式来实现多帐套
			
			if (flag > 0) {
				String html = null;
				if (flag == 1) {
					html=this.doAccountListHtml();
				}
				if (flag == 2) {
                    html=this.doMultidatasourceHtml();
				}
				ResponseUtils.write(pageContext, html.toString());
			}
		} catch (Exception e) {

		}
		return (EVAL_BODY_TAG);
	}

	public void release() {

	}
}
