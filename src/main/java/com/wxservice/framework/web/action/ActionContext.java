package com.wxservice.framework.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;

import com.wxservice.framework.web.form.BaseForm;

public class ActionContext {
	HttpServletRequest request;
	HttpServletResponse response;
	BaseForm form;
	ActionMapping mapping;
	AbstractStrutsAction action;
	/**  
	 * 返回 action 的值   
	 * @return action  
	 */
	
	public AbstractStrutsAction getAction() {
		return action;
	}
	/**  
	 * 设置 action 的值  
	 * @param action
	 */
	/**  
	 * 返回 request 的值   
	 * @return request  
	 */
	
	public HttpServletRequest getRequest() {
		return request;
	}
	/**  
	 * 设置 request 的值  
	 * @param request
	 */
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	/**  
	 * 返回 response 的值   
	 * @return response  
	 */
	
	public HttpServletResponse getResponse() {
		return response;
	}
	/**  
	 * 设置 response 的值  
	 * @param response
	 */
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	/**  
	 * 返回 form 的值   
	 * @return form  
	 */
	
	public BaseForm getForm() {
		return form;
	}
	/**  
	 * 设置 form 的值  
	 * @param form
	 */
	
	public void setForm(BaseForm form) {
		this.form = form;
	}
	/**  
	 * 返回 mapping 的值   
	 * @return mapping  
	 */
	
	public ActionMapping getMapping() {
		return mapping;
	}
	/**  
	 * 设置 mapping 的值  
	 * @param mapping
	 */
	
	public void setMapping(ActionMapping mapping) {
		this.mapping = mapping;
	}
	/**  
	 * 设置 action 的值  
	 * @param action
	 */
	
	public void setAction(AbstractStrutsAction action) {
		this.action = action;
	}
	
	
}
