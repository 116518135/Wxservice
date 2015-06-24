package com.wxservice.framework.report.html;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.report.html.CommonHtmlProcess;
import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.SysUtil;

public class BeginDateHtmlProcess extends CommonHtmlProcess {
	public String doVar(String html,HttpServletRequest request){
		Map<String, String> map=new HashMap<String, String>();
		map.put(DEFAULT_WEBPATH,request.getContextPath());
		 Calendar calendar = Calendar.getInstance();
         calendar.add(Calendar.MONTH, -1);
       String startDate=DateUtil.formatSimplyDate(calendar.getTime());		
		map.put(DEFAULT_VALUE,startDate);
		String result=SysUtil.doVarible(html, map);
		return result;
	}
}
