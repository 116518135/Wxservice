package com.wxservice.framework.web.action;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ClassUtils;

import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.support.JspForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;

public class VipStrutsAction extends StrutsAction {


	public ClientSession getClientSession(HttpServletRequest request) {
		ClientSession client = WebUtil.getVipClientSession(request);

		return client;

	}

}
