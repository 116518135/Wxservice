package com.wxservice.framework.report.html;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.RequestUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.service.NameManager;
public class SimpleHtmlProcess implements HtmlProcess {
	IDao dao = null;
	NameManager nameManager = null;
	@Override
	public String processHtml(Trpreportcondition condition,
			HttpServletRequest request) {
		return "";
	}
	@Override
	public Map<String, String> processValue(Trpreportcondition condition,
			 HttpServletRequest request) {
		return null;
	}

	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}


	public String doVar(String html, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(DEFAULT_WEBPATH, request.getContextPath());
		String result = SysUtil.doVarible(html, map);
		return result;
	}

	public NameManager getNameManager() {
		return nameManager;
	}

	public void setNameManager(NameManager nameManager) {
		this.nameManager = nameManager;
	}
	protected String catchValue(String name, HttpServletRequest request) {
		String value = RequestUtil.getParameter(request, name);
		return value;
	}
	public String getValueKey(String jsname){
		return ReportUtil.getValueKey(jsname);
	}
	
	public String getSqlKey(String jsname){
		return ReportUtil.getSqlKey(jsname);
	}
}
