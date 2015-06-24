package com.wxservice.framework.web.action;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.MultipartRequestWrapper;

import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.support.BigDecimalConverter;
import com.wxservice.framework.web.action.support.DateConverter;
import com.wxservice.framework.web.action.support.JspForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.service.ServiceConfig;

public abstract class AbstractStrutsAction extends Action {
	static {
		registConverter();
	}

	/**
	 * 注册自动转换函数
	 */
	public static void registConverter() {
		ConvertUtils.register(new StringConverter(), String.class);
		ConvertUtils.register(new IntegerConverter(new Integer(0)),
				Integer.class);
		ConvertUtils.register(new LongConverter(new Long(0)), Long.class);
		ConvertUtils.register(new FloatConverter(new Float(0)), Float.class);
		ConvertUtils.register(new DoubleConverter(new Double(0)), Double.class);
		ConvertUtils.register(new DateConverter("yyyy/MM/dd HH:mm:ss"), Date.class);
		ConvertUtils.register(new BigDecimalConverter(new BigDecimal(0)),
				BigDecimal.class);
	}

	public void setServlet(ActionServlet actionServlet) {
		super.setServlet(actionServlet);
	}

	/**
	 * 取到Request
	 * 
	 * @param request
	 * @return
	 */
	public HttpServletRequest getRequest(HttpServletRequest request) {
		HttpServletRequest req = null;
		assert (request == null);
		if (request != null && request instanceof MultipartRequestWrapper) {
			MultipartRequestWrapper obj = (MultipartRequestWrapper) request;
			req = obj.getRequest();
		} else {
			req = request;
		}
		return req;
	}

	/**
	 * 执行
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (form != null) {
			if (form instanceof BaseForm) {
				IStrutsForward forward = null;
				BaseForm curform = (BaseForm) form;

				request.setAttribute(SysFinal.STRUTS_FORM, form);
				// create context
				ActionContext context = createContext(mapping, request,
						response, curform);
				curform.setFormname(mapping.getName());
				curform.setFormpath(mapping.getPath());
				// process session
				forward = processSession(mapping, request, curform);
				if (forward != null) {
					return forward.findForward(context);
				}
				// process forward JSP
				forward = processForwardJsp(curform);
				if (forward != null) {
					return forward.findForward(context);
				}
				// process method
				forward = processMethod(curform);
				if (forward != null) {
					return forward.findForward(context);
				}
				// process forward
				forward = dispatch(context);
				if (forward == null)
					return null;
				return forward.findForward(context);
			} else {
				SystemLogger.error("Struts Form  必须继承于BaseForm!!!");
			}
		}else{
			SystemLogger.error("Struts Form 没有找到!!!");
		}
		return mapping.findForward(SysFinal.STRUTS_SYSTEM_ERROR); // 系统产生异常
	}

	/**
	 * 判断Session
	 * 
	 * @param mapping
	 * @param request
	 * @param curform
	 * @return
	 */
	private IStrutsForward processSession(ActionMapping mapping,
			HttpServletRequest request, BaseForm curform) {
		IStrutsForward forward = null;
		String anonymous = StringUtil.notNull(curform.getAnonymous());
			if (!existSession(request, curform)) {
				if ("".equals(anonymous) || "0".equals(anonymous)) { // default是需要判断是否登录
					forward = new JspForward(SysFinal.STRUTS_SESSION_ERROR);
					return forward;// session丢失的页面
				}
			}
				
		return null;
	}

	/**
	 * 不经过Manager，直接返回一个Jsp
	 * 
	 * @param curform
	 * @return
	 */
	private IStrutsForward processForwardJsp(BaseForm curform) {
		IStrutsForward forward = null;
		String forwardjsp = StringUtil.notNull(curform.getForward());
		if (!"".equals(forwardjsp)) { // default是需要判断是否登录
			forward = new JspForward(forwardjsp);
			return forward;// session丢失的页面
		}
		return null;
	}

	/**
	 * 调用Mangager中的Method方法
	 * 
	 * @param curform
	 * @return
	 */
	private IStrutsForward processMethod(BaseForm curform) {
		IStrutsForward forward = null;
		String method = StringUtil.notNull(curform.getMethod());
		if ("".equals(method)) { // default是需要判断是否登录

			forward = new JspForward(SysFinal.STRUTS_SYSTEM_ERROR);
			SystemLogger.error("Method参数未传,无法找到对应的service method!!!!!!");
			return forward;// session丢失的页面

		}
		return null;
	}

	/**
	 * 创建一个上下文 ActionContext
	 * 
	 * @param mapping
	 * @param request
	 * @param response
	 * @param curform
	 * @return
	 */
	private ActionContext createContext(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response,
			BaseForm curform) {
		ActionContext context = new ActionContext();
		curform.setWebPath(mapping.getPath());
		context.setForm(curform);
		HttpServletRequest req = this.getRequest(request);
		context.setRequest(req);
		context.setResponse(response);
		context.setAction(this);
		context.setMapping(mapping);
		return context;
	}

	/**
	 * 得到Session
	 * 
	 * @param request
	 * @return
	 */
	public ClientSession getClientSession(HttpServletRequest request) {
		ClientSession client = WebUtil.getClientSession(request);

		return client;

	}

	/**
	 * 是否存在Session
	 * 
	 * @param request
	 * @param form
	 * @return
	 */
	protected boolean existSession(HttpServletRequest request, BaseForm form) {
		ClientSession client = this.getClientSession(request);

		if (client == null) {
			return false;
		} else {
			String key = WebUtil.getPath(form, request);
			String authStr = client.getAuthMap().get(key);// 权限字符串
			String moduleTitle = client.getTitleMap().get(key);
			form.setModuleTitle(moduleTitle);
			if(StringUtil.isNotBlank(authStr)){
			  form.setAuth_string(authStr);
			}
			request.setAttribute(SysFinal.WEB_PATH, key);
			return true;

		}
	}

	/**
	 * 抛转，子类实现
	 * 
	 * @param context
	 * @return
	 */
	public abstract IStrutsForward dispatch(ActionContext context);

	/**
	 * 得到web路径
	 * 
	 * @param form
	 * @param request
	 * @return
	 */
	

}
