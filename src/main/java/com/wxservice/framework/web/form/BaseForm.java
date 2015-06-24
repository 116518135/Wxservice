package com.wxservice.framework.web.form;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionServlet;

import com.wxservice.framework.dao.support.Page;
import com.wxservice.framework.web.tag.PageTableProperties;

public class BaseForm extends ActionForm {
	protected String method = null; // 方法

	private String action = null;

	protected String moduleTitle = null; // 标题

	protected String findwhere = null; // 查找的where语句

	protected String webPath = null;

	protected int ec_p = 1; // 列表是用用到.

	protected int ec_crd = 0;

	protected String service = null;// 对应的service名称

	protected String forward = null;// 当forward不为null的时候,将不调用Manger中的方法,改为直接dispatch

	String anonymous = null;// 当anonymous为0的时候,登录用户才可以访问,否则,非登录用户也可以访问
	String formname;
	String formpath;
	protected Page page = new Page();

	protected int pageNumber = 1;

	protected int linePageNumber = 1;
	String auth_string;//权限字符串

	/**  
	 * 返回 auth_string 的值   
	 * @return auth_string  
	 */
	
	public String getAuth_string() {
		return auth_string;
	}
	/**  
	 * 设置 auth_string 的值  
	 * @param auth_string
	 */
	
	public void setAuth_string(String auth_string) {
		this.auth_string = auth_string;
	}
	public ActionServlet getServlet() {
		return (this.servlet);
	}
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public String getFindwhere() {
		return findwhere;
	}

	public void setFindwhere(String findwhere) {
		this.findwhere = findwhere;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public int getEc_p() {
		return ec_p;
	}

	public void setEc_p(int ec_p) {
		this.ec_p = ec_p;
	}

	public int getEc_crd() {
		if(ec_crd==0){
			ec_crd=PageTableProperties.getRowdisplayed();
		}
		return ec_crd;
	}

	public void setEc_crd(int ec_crd) {
		this.ec_crd = ec_crd;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getLinePageNumber() {
		return linePageNumber;
	}

	public void setLinePageNumber(int linePageNumber) {
		this.linePageNumber = linePageNumber;
	}
	public String getFormname() {
		return formname;
	}
	public void setFormname(String formname) {
		this.formname = formname;
	}
	public String getFormpath() {
		return formpath;
	}
	public void setFormpath(String formpath) {
		this.formpath = formpath;
	}

}
