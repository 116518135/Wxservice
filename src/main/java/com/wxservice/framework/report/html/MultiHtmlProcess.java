package com.wxservice.framework.report.html;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.util.StringUtil;
public class MultiHtmlProcess extends CommonHtmlProcess {
	@Override
	public Map<String, String> processValue(Trpreportcondition condition, 
			HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		String[] str=condition.getJsname().split(",");
		if(str.length==1) {
			return super.processValue(condition, request);
		}
		String idfield=str[0];
		String namefield=str[1];
		String idvalue=this.catchValue(idfield, request);
		String namevalue=this.catchValue(namefield, request);
		if(StringUtil.isNotBlank(idvalue) || StringUtil.isBlank(namevalue)){
			doValue(result,condition.getWheres(),idfield, idvalue,request);
		}else{
			String v=code2Value(namevalue,request);
			doValue(result,condition.getWheres(),idfield, v,request);
		}
		return result;
	}
	
	public String code2Value(String value,HttpServletRequest request){
		return "";
	}
}
