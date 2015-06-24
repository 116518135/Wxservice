package com.wxservice.framework.report.html;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.util.HtmlBuilder;
import com.wxservice.framework.util.SysUtil;
public class CommonHtmlProcess extends SimpleHtmlProcess {
	@Override
	public String processHtml(Trpreportcondition condition, HttpServletRequest request) {
		String str = condition.getHtmlcontent();
		String value = this.doVar(str, request);
		String title=condition.getName();
		int colspan=1;
		if(condition.getWidth()!=null && condition.getWidth()>1){
			colspan=condition.getWidth()*2-1;
		}
		HtmlBuilder html = new HtmlBuilder();
		html.append("<td  class='label'>");
		html.append(title);
		html.append("</td>");
		html.newline();
		html.append("<td  class='input' colspan='");
		html.append(String.valueOf(colspan));
		html.append("'>");
		html.append(value);
		html.append("</td>");
		html.newline();
		return html.toString();
	}
	@Override
	public Map<String, String> processValue(Trpreportcondition condition, 
			HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		String value = this.catchValue(condition.getJsname(), request);
		doValue(result,condition.getWheres(),condition.getJsname(), value,request);
		return result;
	}
	protected void doValue(Map<String, String> result,String wheres,String jsname, String value,HttpServletRequest request) {
		String valuekey=this.getValueKey(jsname);
		result.put(valuekey.toString(),value);
		request.setAttribute(valuekey.toString(),value);
		String sqlkey=this.getSqlKey(jsname);
		if (StringUtils.isNotBlank(value)) {
			if (StringUtils.isNotBlank(wheres)) {
				Map<String, String> params = new HashMap<String, String>();
				params.put(VAR_KEY_VALUE, value);
				String sql = SysUtil.doVarible(wheres, params);
				result.put(sqlkey.toString(), sql);
			} else {
				result.put(sqlkey.toString(), value);
			}
		}else{
			result.put(sqlkey.toString(),"1=1");
		}
	}
	
	
}
