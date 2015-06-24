package com.wxservice.framework.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



import com.wxservice.framework.components.License.LicenseService;
import com.wxservice.framework.components.online.UserInfoTool;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.start.SysSecurityContextHolder;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;

public class WebUtil {
	private static WebApplicationContext webApplicationContext;

	private WebUtil() {
	}

	/**
	 * 取到存入在session中的某个值.
	 * 
	 * @param <T>
	 * @param request
	 * @param name
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getOrCreateSessionAttribute(HttpServletRequest request,
			String name, Class<T> clazz) {
		return (T) org.springframework.web.util.WebUtils
				.getOrCreateSessionAttribute(request.getSession(), name, clazz);
	}

	public static Object getBean(String beanName, BaseForm form) {
		webApplicationContext = getWebApplicationContext(form.getServlet()
				.getServletContext());
		Object bean = webApplicationContext.getBean(beanName);
		return bean;
	}

	public static IDao getDao(HttpServletRequest request) {
		IDao dao = (IDao) getBean("dao", request);
		return dao;
	}

	public static IDao getDao() {
		IDao dao = (IDao) getBean("dao");
		return dao;
	}

	public static Object getBean(String beanName, HttpServletRequest request) {
		ServletContext servletContext = request.getSession()
				.getServletContext();
		WebApplicationContext wac = getWebApplicationContext(servletContext);

		Object bean = wac.getBean(beanName);
		return bean;
	}

	public static Object getBean(String beanName, PageContext pageContext) {
		ServletContext servletContext = pageContext.getServletContext();
		WebApplicationContext wac = getWebApplicationContext(servletContext);
		Object obj = wac.getBean(beanName);
		return obj;
	}

	public static Object getBean(String beanName) {
		Object obj = webApplicationContext.getBean(beanName);
		return obj;
	}

	public static Object getBean(String beanName, ServletContext servletContext) {
		WebApplicationContext wac = getWebApplicationContext(servletContext);
		Object obj = wac.getBean(beanName);
		return obj;
	}

	public static BaseForm getForm(HttpServletRequest request) {
		BaseForm form = (BaseForm) request.getAttribute(SysFinal.STRUTS_FORM);
		return form;
	}

	public static ClientSession getClientSession(HttpServletRequest request) {
		ClientSession clientSession = (ClientSession) request.getSession()
				.getAttribute(SysFinal.CLIENT_SESSION);
		if (clientSession != null) {
			boolean exist = UserInfoTool.IsUserOnlineBySessionid(clientSession);
			if (exist) {
				return clientSession;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}
	
	public static ClientSession getVipClientSession(HttpServletRequest request) {
		ClientSession clientSession = (ClientSession) request.getSession()
				.getAttribute(SysFinal.CLIENT_SESSION);
		if (clientSession != null) {
			
				return clientSession;
			
		} else {
			return null;
		}

	}

	public static ClientSession getClientSession() {
		HttpServletRequest request = SysSecurityContextHolder.getContext()
				.getRequest();
		if (request != null) {
			ClientSession client = WebUtil.getClientSession(request);
			return client;
		}
		return null;
	}
	public static ClientSession getVipClientSession() {
		HttpServletRequest request = SysSecurityContextHolder.getContext()
				.getRequest();
		if (request != null) {
			ClientSession client = WebUtil.getVipClientSession(request);
			return client;
		}
		return null;
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = SysSecurityContextHolder.getContext()
				.getRequest();
		return request;
	}

	public static void main(String[] args) {
		HttpServletRequest request = null;
		String name = null;
	}

	public static String getPath(HttpServletRequest request) {
		String uri = request.getRequestURL().toString();
		String str = uri.substring(uri.lastIndexOf("/"));
		String key = str.substring(1);
		return key;
	}

	public static void setWebApplicationContext(
			WebApplicationContext webApplicationContext) {
		WebUtil.webApplicationContext = webApplicationContext;
	}

	public static WebApplicationContext getWebApplicationContext(
			ServletContext servletContext) {
		if (webApplicationContext == null) {
			webApplicationContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(servletContext);
		}
		return webApplicationContext;
	}

	public static String getPath(BaseForm form, HttpServletRequest request) {
		StringBuffer path = new StringBuffer();
		String key = form.getWebPath().substring(1, form.getWebPath().length());
		if (StringUtils.isBlank(key)) {
			key = WebUtil.getPath(request);
		}
		path.append(key);
		path.append(".do");
		return path.toString();
	}

	public static Integer getDSCount() {
		
		int	count = 1;
		
		return count;
	}

	public static String getDstype() {
		ClientSession client=getClientSession();
		if(client!=null){
			return client.getDstype();
		}
		return "";
	}
}
