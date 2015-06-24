package com.wxservice.service.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wxservice.framework.engine.BaseEngine;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.web.form.base.AjaxForm;

public class AjaxManager extends BaseEngine {
	public static String message_key = "message";
	public static String codeerror = "输入编号，回车发生错误!";
	public static String codenotexist = "输入编号，回车发生错误!";
	
	public IStrutsForward checkSession(ClientSession client, Map map) {
		if (client == null) {
			map.put("result", "0");
			map.put(message_key, "会话丢失，请重新登录！");
			return this.forwardJson(map);
		}
		return null;
	}

	public IStrutsForward tableEnter(ActionContext context) {
		Map map = new HashMap();
		try {
			AjaxForm form = (AjaxForm) context.getForm();
			map.put("result", "0");
			map.put("mapping", form.getMapping());
			map.put("jsname", form.getJsname());
			map.put("nextjsname", form.getNextjsname());
			String classname = SysUtil.getClassname(form.getTable());
			StringBuffer hql = new StringBuffer();
			hql.append(" from ");
			hql.append(classname);
			hql.append(" where 1=1 ");
			hql.append(" and code='");
			hql.append(form.getCode());
			hql.append("'");
			if (StringUtils.isNotBlank(form.getQuerystring())) {
				hql.append(" and ( ");
				hql.append(form.getQuerystring());
				hql.append(")");
			}
			if (MathUtil.equal(form.getLimitcmpflag(), 1)) {
				ClientSession client = WebUtil.getClientSession(context
						.getRequest());
				hql.append(" and createtbscmpid=");
				hql.append(client.getCmpid());
			}
			Object entity = dao.get(hql.toString());
			if (entity != null) {
				Map m = SysUtil.doMapping(form.getMapping(), entity);
				map.putAll(m);
			} else {
				map.put(message_key, codeerror);
			}

		} catch (Exception e) {
			SystemLogger.error(codenotexist, e);
		}
		return this.forwardJson(map);
	}

	
}



