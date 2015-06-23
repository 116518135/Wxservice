package com.wxservice.service.base;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.wxservice.database.po.base.Tbsuser;
import com.wxservice.database.po.system.Tsysmenu;
import com.wxservice.database.po.system.Tsysroledtl;
import com.wxservice.framework.components.image.ImageCode;
import com.wxservice.framework.components.online.UserInfoTool;
import com.wxservice.framework.engine.BaseEngine;
import com.wxservice.framework.engine.support.SysConfig;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.MapUtil;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.Md5;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.action.support.DownloadForward;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.service.RightService;
import com.wxservice.web.form.base.LoginForm;

public class LoginManager extends BaseEngine {
	RightService rightService = null;

	
	public IStrutsForward image(ActionContext context) {
		HttpServletRequest request = context.getRequest();
		HttpServletResponse response = context.getResponse();
		response.setContentType("image/jpeg");
		ImageCode image = new ImageCode();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			// 输出图象到页面
			BufferedImage img = image.creatImage();
			request.getSession().setAttribute(SysFinal.RANDOM_CODE,
					image.getSRand());
			ImageIO.write(img, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			this.processFailure(context, e, false, "获到验证图片");
		}
		return null;
	}

	public void limitClient(LoginForm form, Tbsuser user) {
		

		//限制同名用户只能登录一次
		if (serviceConfig.getIntConfig("wxservice.login.limituserflag") == 1){
			if(UserInfoTool.IsUserOnlineByUserid(user.getTbsuserid())){
				throw new BusinessException("操作员("+user.getCode()+")已经登录系统!,请先退出再登录");
			}
		}
				
	}

	public IStrutsForward exit(ActionContext context) {
		HttpServletRequest request = context.getRequest();
		HttpSession session = request.getSession();
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		if (client != null) {
			UserInfoTool.removeOnlineUser(client.getSessionid());
			SystemLogger.info(client.getUsername() + "用户,已经退出系统.");
		}
		session.removeAttribute(SysFinal.CLIENT_SESSION);
		return this.forwardJsp("index");
	}

	public IStrutsForward workspace(ActionContext context) {
		// 数据分析
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		
		return this.forwardJsp("workspace");
	}
	
	public void validateRandomCode(String inputcode,
			HttpServletRequest httpServletRequest) throws BusinessException {
		int checkflag = MapUtil.getInteger(serviceConfig.getProps(),
				"wxservice.login.randomflag", false);
		if (SysConfig.getBooleanConfig("wxservice.login.randomflag", true)) {// 需要进行校验码判断
			String randCode = (String) httpServletRequest.getSession()
					.getAttribute(SysFinal.RANDOM_CODE);
			if (!inputcode.equalsIgnoreCase(randCode)) {
				throw new BusinessException("校验码输入不对,请重新输入!");
			}
		}
	}



	public Tbsuser validateUser(LoginForm form,
			HttpServletRequest httpServletRequest) throws BusinessException {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tbsuser as a  where ifnull(a.stopflag,0)=0 and  lower(a.code)='");
		hql.append(form.getUsercode().toLowerCase());
		hql.append("'");
		Tbsuser user = (Tbsuser) dao.get(hql.toString());
		if (user == null) {
			throw new BusinessException("用户编号不存在!");
		}
		return user;
	}



	public void validateCanLogin(LoginForm form, Tbsuser user)
			throws BusinessException {
		if (user.getTbsuserid().intValue() == SysFinal.SYSTEM_USERID) {
			return;
		}
		if (SysConfig.getBooleanConfig("esk2.login.systemuseflag", false)) {
			SystemLogger.error("系统限制为用户不能登录!");
			throw new BusinessException("系统限制为用户不能登录!");
		}
	}

//	public void validateLicense(LoginForm form) throws BusinessException {
//		License license = LicenseService.getLicense();
//		if (!UserInfoTool.checkLicense(license)) {
//			throw new BusinessException("系统License不正确,请联系软件供应商！");
//		}
//		if (StringUtil.isNotBlank(license.getEnddate())) {
//			Date enddate = DateUtil.parse(license.getEnddate());
//			Date today = DateUtil.getToday();
//			if (today.compareTo(enddate) > 0) {
//				throw new BusinessException("License有效日期已失效:"
//						+ license.getEnddate() + ",请联系软件供应商进行重新注册！");
//			}
//		}
//
//	}
	public void validatePassword(LoginForm form, Tbsuser user,
			HttpServletRequest httpServletRequest) throws BusinessException {
		if (StringUtil.isBlank(user.getPasswd())
				&& StringUtil.isBlank(form.getPasswd())) {

		} else {
			String passwd = Md5.md5(form.getPasswd());
			if (!passwd.equals(user.getPasswd())) {
				throw new BusinessException("输入的密码不正确!");
			}
		}
	}



	private void setClientProperty(ClientSession client,
			Tbsuser user) {
		client.setUserid(user.getTbsuserid());
		client.setUsercode(user.getCode());
		client.setUsername(user.getName());
	

		
	}

	public void setRight(Tbsuser user, ClientSession client,
			HttpServletRequest request) throws DataAccessException {

		// 判断是否具有系统管理员权限
		StringBuffer hql = new StringBuffer();
		if (user.getTbsuserid() == SysFinal.SYSTEM_USERID) {
			client.setIsAdmin(true);
		}
		StringBuffer hsql = new StringBuffer();
		if (client.isAdmin()) {
			hsql.append(" from Tsysmenu  a ");
		} else {
			hsql
					.append(" select a from Tsysroledtl  a  where a.tsysroleid in(");
			hsql.append(user.getTsysroleids());
			hsql.append(")");
		}
		List list = dao.iterate(hsql.toString());
		for (Object o : list) {
			String authStr = null;
			if (o instanceof Tsysmenu) {
				Tsysmenu po = (Tsysmenu) o;
				authStr = po.getRightvalue();
				String url = po.getAccessurl();
				if (StringUtils.isBlank(url)) {
					continue;
				}
				String key = SysUtil.getUrlKey(url);
				Map<String, String> authMap = client.getAuthMap();
				Map<String, String> titleMap = client.getTitleMap();
				titleMap.put(key, po.getName());
				authMap.put(key, authStr);
			} else {
				Tsysroledtl t = (Tsysroledtl) o;
				String auth = t.getRightvalue();
				Tsysmenu po = (Tsysmenu) dao.get(Tsysmenu.class, t
						.getTsysmenuid());
				if (po == null) {
					continue;
				}
				String url = po.getAccessurl();
				if (StringUtils.isBlank(url)) {
					continue;
				}

				String isRight = rightService.getRightChar(auth,
						rightService.FBROWSE);
				if (!"1".equals(isRight)) {
					continue;
				}
				// 判断有没有权限
				String key = SysUtil.getUrlKey(url);
				Map<String, String> authMap = client.getAuthMap();
				Map<String, String> titleMap = client.getTitleMap();
				titleMap.put(key, po.getName());
				String auth2 = authMap.get(key);// 原来存在的权限，下面进行合并，，，
				authStr = SysUtil.authMerge(auth, auth2);
				authMap.put(key, authStr);
			}
		}

	}

	public void validateRight(ClientSession client) throws BusinessException {
		boolean right = false;
		Map authMap = client.getAuthMap();
		Iterator iter = authMap.values().iterator();
		while (iter.hasNext()) {
			String auth = (String) iter.next();
			if (auth.indexOf("1") != -1) {
				right = true;
				break;
			}
		}
		if (!right) {
			throw new BusinessException("没有权限访问系统");
		}
	}

	public ClientSession createClientSession(LoginForm form, Tbsuser user,
			 HttpSession session) throws BusinessException {
		ClientSession client = new ClientSession();
		client.setSessionid(session.getId());
		setClientProperty(client, user);
		
		return client;
	}

	/*
	 * public IStrutsForward login(ActionContext context) {
	 * context.getRequest().setAttribute(SysFinal.WEB_MESSAGE,
	 * "系统无法访问，请联系系统管理员"); return this.forwardJsp("index"); }
	 */

	public IStrutsForward login(ActionContext context) {
		HttpServletRequest request = context.getRequest();
		LoginForm form = (LoginForm) context.getForm();
		HttpSession session = createSession(request);
		try {
			ClientSession client = doLogin(context, request, form, session,
					true);
			tree(context, client);
			SystemLogger.info(client.getUsername() + "用户,已经登录系统.");
		} catch (Exception e) {
			form.setCmpcode("");
			form.setUsercode("");
			form.setPasswd("");
			form.setRandomcode("");
			this.processFailure(context, e, true, e.getMessage());
			return this.forwardJsp("index");
		}
		return this.forwardJsp("main");
	}

	protected void processFailure(ActionContext context, Exception e,
			boolean isWriteRequest, String messageEx) {
		String actionName = this
				.getMethodChinese(context.getForm().getMethod());
		StringBuffer msg = new StringBuffer("");
		msg.append(actionName);
		if (StringUtil.isNotBlank(context.getForm().getModuleTitle())) {
			msg.append(context.getForm().getModuleTitle());
		}
		if (e != null) {// 操作是成功的,没有出现异常
			msg.append("失败.");
			if (!"".equals(e.getMessage()) && null != e.getMessage()) {
				msg.append("[");
				msg.append(e.getMessage());
				msg.append("]");
			}
		}
		if (isWriteRequest) {
			context.getRequest().setAttribute(SysFinal.WEB_MESSAGE,
					msg.toString());
		}
		SystemLogger.error(msg.toString(), e);
	}

	public IStrutsForward main_tree(ActionContext context) {
		LoginForm form = (LoginForm) context.getForm();
		try {
			ClientSession client = WebUtil.getClientSession();
			tree(context, client);
		} catch (Exception e) {
			form.setCmpcode("");
			form.setUsercode("");
			form.setPasswd("");
			form.setRandomcode("");
			this.processFailure(context, e, true, e.getMessage());
			return this.forwardJsp("index");
		}
		return this.forwardJsp("main_tree");
	}

	public IStrutsForward main_tab(ActionContext context) {
		LoginForm form = (LoginForm) context.getForm();
		try {
			ClientSession client = WebUtil.getClientSession();
			tree(context, client);
		} catch (Exception e) {
			form.setCmpcode("");
			form.setUsercode("");
			form.setPasswd("");
			form.setRandomcode("");
			this.processFailure(context, e, true, e.getMessage());
			return this.forwardJsp("index");
		}
		return this.forwardJsp("main");
	}

	public ClientSession doLogin(ActionContext context,
			HttpServletRequest request, LoginForm form, HttpSession session,
			boolean validateFlag) {
		if (validateFlag) {
			String randomCode = form.getRandomcode();
			this.validateRandomCode(randomCode, request);
		}
		//validateLicense(form);
		Tbsuser user = validateUser(form, request);
		limitClient(form, user);
		validateCanLogin(form, user);
		validatePassword(form, user, context.getRequest());
		ClientSession client = this.createClientSession(form, user, 
				session);
		this.setRight(user, client, request);
		this.validateRight(client);
		UserInfoTool.addOnlineUser(client);
		request.setAttribute("username", client.getUsername());
		session.setAttribute(SysFinal.CLIENT_SESSION, client);
		return client;
	}

	protected HttpSession createSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session;
	}

	public RightService getRightService() {
		return rightService;
	}

	public void setRightService(RightService rightService) {
		this.rightService = rightService;
	}

	// 下面是菜单

	public String createLink(String pre, String url, String menuname,
			HttpServletRequest request) {
		StringBuffer buf = new StringBuffer();
		buf.append(pre);
		buf.append("<a href='");
		buf.append(request.getContextPath());
		buf.append(url);
		buf.append("' title='");
		buf.append(menuname);
		buf.append("'>");
		buf.append(menuname);
		buf.append("</a> \n");
		return buf.toString();
	}

	public String createLink2(ActionContext context, Tsysmenu menu) {
		StringBuffer buf = new StringBuffer();
		if (StringUtil.isNotBlank(menu.getWebserver())) {
			if (menu.getWebserver().indexOf("http:") < 0) {
				buf.append("http://");
			}
			buf.append(menu.getWebserver());
			buf.append(context.getRequest().getContextPath());
			List list = StringUtil.string2List(menu.getAccessurl(), "?");
			buf.append(list.get(0));
			buf.append(";jsessionid=");
			buf.append(context.getRequest().getSession().getId());
			if (list.size() > 1) {
				buf.append("?");
				buf.append(list.get(1));
			}
		} else {
			buf.append(context.getRequest().getContextPath());
			buf.append(menu.getAccessurl());
		}
		return buf.toString();
	}

	public boolean isEnd(Integer parentid) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tsysmenu  a where a.parentid=").append(parentid);
		Object obj = dao.get(hsql.toString());
		return obj == null;
	}

	public void tree(ActionContext context, ClientSession client) {
		HttpServletRequest request = context.getRequest();
		request.setAttribute("username", client.getUsername());
		request.setAttribute("cmpname", client.getCmpname());
		StringBuffer json = new StringBuffer("");

		json.append("[");
		String str = this.doMenus(context, 0, request, client);
		json.append(str);
		json.append("]");
		request.setAttribute("menu_trees", json.toString());
	}

	public String doMenus(ActionContext context, Integer parentid,
			HttpServletRequest request, ClientSession client) {
		StringBuffer json = new StringBuffer();
		String hsql = getSql(parentid, "");
		List list = dao.iterate(hsql);
		if (list != null && list.size() > 0) {
			int loopflag = 0;
			for (int loop = 0; loop < list.size(); loop++) {
				Object o = list.get(loop);
				Tsysmenu po = (Tsysmenu) o;
				boolean hasRight = rightService.getRight(request, po);
				if (!hasRight)
					continue;

				if (isEnd(po.getTsysmenuid())) {
					if (loopflag > 0) {
						json.append(",");
					}
					loopflag++;
					doLeaf(context, request, json, po);

				} else {
					if (loopflag > 0) {
						json.append(",");
					}
					loopflag++;
					json.append("{");
					this.doBooleanJson("singleClickExpand", "true", json);
					json.append(",");
					this.doStringJson("id", this.getTreeId(po), json);
					json.append(",");
					this.doStringJson("iconCls", "icon-pkg", json);
					json.append(",");
					this.doStringJson("cls", "package", json);
					json.append(",");
					this.doStringJson("text", po.getName(), json);
					json.append(",'children':[");
					String sql2 = this.getSql(po.getTsysmenuid());
					List list2 = dao.iterate(sql2);
					int jflag = 0;
					for (int j = 0; j < list2.size(); j++) {
						Tsysmenu po2 = (Tsysmenu) list2.get(j);
						boolean hasRight2 = rightService.getRight(request, po2);
						if (!hasRight2)
							continue;
						if (isEnd(po2.getTsysmenuid())) {
							if (jflag > 0) {
								json.append(",");
							}
							jflag++;
							doLeaf(context, request, json, po2);
						} else {
							if (jflag > 0) {
								json.append(",");
							}
							jflag++;
							json.append("{");
							this.doBooleanJson("singleClickExpand", "true",
									json);
							json.append(",");
							this.doStringJson("id", this.getTreeId(po2), json);
							json.append(",");
							this.doStringJson("iconCls", "icon-pkg", json);

							json.append(",");
							this.doStringJson("cls", "package", json);
							json.append(",");
							this.doStringJson("text", po2.getName(), json);
							json.append(",'children':[");
							String sql3 = this.getSql(po2.getTsysmenuid());
							List list3 = dao.iterate(sql3.toString());
							int mflag = 0;
							for (int m = 0; m < list3.size(); m++) {
								Tsysmenu po3 = (Tsysmenu) list3.get(m);
								boolean hasRight3 = rightService.getRight(
										request, po3);
								if (!hasRight3)
									continue;
								if (isEnd(po3.getTsysmenuid())) {
									if (mflag > 0) {
										json.append(",");
									}
									mflag++;
									doLeaf(context, request, json, po3);
								} else {
									if (mflag > 0) {
										json.append(",");
									}
									mflag++;
									json.append("{");
									this.doBooleanJson("singleClickExpand",
											"true", json);
									json.append(",");
									this.doStringJson("id",
											this.getTreeId(po3), json);
									json.append(",");
									this.doStringJson("iconCls", "icon-pkg",
											json);

									json.append(",");
									this.doStringJson("cls", "package", json);
									json.append(",");
									this.doStringJson("text", po3.getName(),
											json);
									json.append(",'children':[");
									String sql4 = this.getSql(po3
											.getTsysmenuid());
									List list4 = dao.iterate(sql4.toString());
									int flag4 = 0;
									for (int n = 0; n < list4.size(); n++) {
										Tsysmenu po4 = (Tsysmenu) list4.get(n);
										boolean hasRight4 = rightService
												.getRight(request, po4);
										if (!hasRight4)
											continue;
										if (isEnd(po4.getTsysmenuid())) {
											if (flag4 > 0) {
												json.append(",");
											}
											flag4++;
											doLeaf(context, request, json, po4);
										}
									}
									json.append("]");
									json.append("}");

								}
							}
							json.append("]");
							json.append("}");
						}
					}
					json.append("]");
					json.append("}");
				}
			}
		}
		return json.toString();

	}

	private String getSql(Integer parentid, String where) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("from Tsysmenu  a where a.parentid=");
		hsql.append(parentid);
		if (StringUtil.isNotBlank(where)) {
			hsql.append(" and ");
			hsql.append(where);
		}
		hsql.append(" order by a.indexno asc");
		return hsql.toString();
	}

	private String getSql(Integer parentid) {
		return this.getSql(parentid, "");
	}

	private void doLeaf(ActionContext context, HttpServletRequest request,
			StringBuffer json, Tsysmenu po) {
		json.append("{");
		this.doStringJson("href", createLink2(context, po), json);
		json.append(",");
		this.doStringJson("text", po.getName(), json);
		json.append(",");
		this.doStringJson("id", this.getTreeId(po), json);
		json.append(",");
		this.doBooleanJson("leaf", "true", json);
		json.append(",");
		this.doStringJson("cls", "cls", json);

		json.append(",");
		this.doStringJson("iconCls", "icon-cls", json);
		json.append("}");
	}

	public void doStringJson(String name, String text, StringBuffer json) {
		json.append("'");
		json.append(name);
		json.append("':");
		json.append("'");
		json.append(text);
		json.append("'");
	}

	public void doBooleanJson(String name, String text, StringBuffer json) {
		json.append("'");
		json.append(name);
		json.append("':");
		json.append(text);
	}

	public String getTreeId(Tsysmenu po) {
		StringBuffer id = new StringBuffer();
		id.append("tree");
		id.append(po.getTsysmenuid());
		return id.toString();
	}

	



	public IStrutsForward cancel(ActionContext context) {
		return this.forwardJsp("user_cancel");
	}

	public IStrutsForward cancelSave(ActionContext context) {
		HttpServletRequest request = context.getRequest();
		LoginForm form = (LoginForm) context.getForm();
		try {
			String randomCode = form.getRandomcode();
			this.validateRandomCode(randomCode, request);
			Tbsuser user = validateUser(form, request);
			validatePassword(form, user, context.getRequest());
			UserInfoTool.removeOnlineUser(user.getTbsuserid());
		} catch (Exception e) {
			form.setCmpcode("");
			form.setUsercode("");
			form.setPasswd("");
			form.setRandomcode("");
			this.processFailure(context, e, true, e.getMessage());
			return this.forwardJsp("user_cancel");
		}
		return this.forwardJsp("index");
	}

	public IStrutsForward download(ActionContext context) {
		HttpServletRequest request = context.getRequest();
		LoginForm form = (LoginForm) context.getForm();
		try {
			StringBuffer path = new StringBuffer("");
			path.append(SysUtil.getSystemDownloadDir());
			path.append(form.getCmpcode());
			File file = new File(path.toString());
			byte[] bytes = FileUtils.readFileToByteArray(file);
			IStrutsForward forward = new DownloadForward(bytes);
			((DownloadForward) forward).setDownloadFile(form.getCmpcode(),
					context);
			return forward;
		} catch (Exception e) {
			SystemLogger.error("下载发生错误", e);
		}
		return this.forwardJsp("download");
	}

}
