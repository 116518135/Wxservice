package com.wxservice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.wxservice.database.po.base.Tbsuser;
import com.wxservice.database.po.system.Tsysmenu;
import com.wxservice.database.po.system.Tsysroledtl;
import com.wxservice.framework.components.right.BaseRightService;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.RequestUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.web.form.report.ReportForm;

public class RightService extends BaseRightService {

	public boolean getSystemRight(HttpServletRequest request, int func) {
		if (func == 0) {
			return true;
		}
		boolean hasRight = false; // 初始化没有权限
		String rightBuffer = readRightString(request);
		String right = getRightChar(rightBuffer, func);
		if ("1".equals(right)) {
			hasRight = true;
		} else {
			hasRight = false;
		}
		return hasRight;
	}

	public String readRightString(HttpServletRequest request) {
		BaseForm form = (BaseForm) request.getAttribute(SysFinal.STRUTS_FORM);
		if (form == null) {
			return "";
		} else {
			if (StringUtil.isNotBlank(form.getAuth_string())) {
				return form.getAuth_string();
			} else {
				// ClientSession client = WebUtil.getClientSession(request);
				// if(client==null){
				// return "";
				// }
				// String key = WebUtil.getPath(form, request);
				// String authStr = client.getAuthMap().get(key);// 权限字符串
				// if(StringUtil.isNotBlank(authStr)){
				// form.setAuth_string(authStr);
				// return authStr;
				// }
				return "";
			}
		}
	}

	public String subString(String str, int start, int end) {
		String right = "0";
		try {
			right = str.substring(start, end);

		} catch (Exception e) {
			right = "0";
		}
		return right;
	}

	public String getRightChar(String authString, int func) {

		int start = func - 1;
		int end = func;

		String right = subString(authString, start, end);
		return right;
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param action
	 *            String
	 * @param request
	 *            HttpServletRequest
	 * @return boolean
	 */
	public boolean hasRight(HttpServletRequest request, BaseForm form, int func) {
		if (func == 0) {
			return true;
		}
		boolean hasRight = false;
		String rightBuffer = readRightString(request);
		if ("".equals(rightBuffer)) {
			return false;
		}
		String right = getRightChar(rightBuffer, func);
		if ("0".equals(right)) {
			hasRight = false;
		} else if ("1".equals(right)) {
			hasRight = true;
		}

		return hasRight;
	}

	public boolean hasRight(HttpServletRequest request, String path, int func) {
		if (func == 0) {
			return true;
		}
		boolean hasRight = false;
		ClientSession client = WebUtil.getClientSession(request);
		String rightBuffer = client.getAuthMap().get(path);
		if ("".equals(rightBuffer)) {
			return false;
		}
		String right = getRightChar(rightBuffer, func);
		if ("0".equals(right)) {
			hasRight = false;
		} else if ("1".equals(right)) {
			hasRight = true;
		}

		return hasRight;
	}

	public boolean hasRightOnlySystem(HttpServletRequest request,
			BaseForm form, int func) {
		if (func == 0) {
			return true;
		}
		boolean hasRight = false;
		String rightBuffer = StringUtil.notNull(form.getAuth_string());
		if ("".equals(rightBuffer)) {
			return false;
		}
		String right = getRightChar(rightBuffer, func);
		if ("0".equals(right)) {
			hasRight = false;
		} else if ("1".equals(right)) {
			hasRight = true;
		}
		return hasRight;
	}

	public Tsysmenu getTsysmenu(String path) {
		Tsysmenu po = null;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysmenu as a where a.accessurl like '/")
				.append(path + ".do").append("%'");
		po = (Tsysmenu) dao.get(hql.toString());
		return po;
	}

	/**
	 * 返回当前菜单项是否具有权限 LoginManager调用
	 * 
	 * @param dao
	 *            BaseDao
	 * @param client
	 *            ClientSession
	 * @param po
	 *            BS_AuthorityGroupD
	 * @return boolean
	 */
	public boolean getLeafRight(ClientSession client, Tsysmenu po) {
		boolean result = false;
		String key = SysUtil.getUrlKey(po.getAccessurl());
		String auth_string = client.getAuthMap().get(key);
		if (StringUtils.isBlank(auth_string)) {
			result = false;
		} else {
			String right = getRightChar(auth_string, FBROWSE);
			if ("1".equals(right)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

	public boolean getRight(HttpServletRequest request, Tsysmenu po) {
		boolean result = false;
		// 判断版本权限

		ClientSession client = WebUtil.getClientSession(request);
		if (!client.isAdmin()) {
			// 第一步判断自身的权限
			if (po != null && StringUtils.isNotBlank(po.getAccessurl())) {
				result = this.getLeafRight(client, po);
			} else {
				boolean subRight = getRootMenuRight(client, po.getTsysmenuid());
				if (subRight) {
					result = true;
				} else {
					result = false;
				}
			}
		} else {
			result = true;
		}

		if (result) {
			result = fliter(client, po);
		}

		return result;
	}

	public boolean getRootMenuRight(ClientSession client, Integer parentid)
			throws DataAccessException {
		boolean result = false;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tsysmenu as a  where  a.parentid=");
		hsql.append(parentid);
		List list = dao.iterate(hsql.toString());
		for (Object o : list) {
			Tsysmenu po = (Tsysmenu) o;
			if (po != null && StringUtils.isNotBlank(po.getAccessurl())) {
				result = this.getLeafRight(client, po);
				if (result) {
					return true;
				}
			} else {
				StringBuffer hsql1 = new StringBuffer();
				hsql1.append(" from Tsysmenu as a  where  a.parentid=");
				hsql1.append(po.getTsysmenuid());
				List list1 = dao.iterate(hsql1.toString());
				for (Object o1 : list1) {
					Tsysmenu po1 = (Tsysmenu) o1;
					if (po1 != null
							&& StringUtils.isNotBlank(po1.getAccessurl())) {
						result = this.getLeafRight(client, po1);
						if (result) {
							return true;
						}
					}
				}
			}
		}
		return result;
	}

	public boolean fliter(ClientSession client, Tsysmenu po) {
		boolean result = true;

		return result;
	}

	public boolean getRight(Tsysmenu po, HttpServletRequest request) {
		boolean result = false;
		if (po != null) {
			String right = getRightChar(po.getRightvalue(), 0);
			if ("1".equals(right)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

	public boolean isPriceEdit(HttpServletRequest request, BaseForm form)
			throws Exception {
		int func = FEDIT;
		boolean hasRight = false;
		String rightBuffer = readRightString(request);
		if ("".equals(rightBuffer)) {
			return false;
		}
		String right = getRightChar(rightBuffer, func);
		if ("0".equals(right)) {
			hasRight = false;
		} else if ("1".equals(right)) {
			hasRight = true;
		}
		return hasRight;
	}

	public boolean isProdEdit(HttpServletRequest request, BaseForm form)
			throws Exception {
		int func = FEDIT;
		boolean hasRight = false;
		String rightBuffer = readRightString(request);
		if ("".equals(rightBuffer)) {
			return false;
		}
		String right = getRightChar(rightBuffer, func);
		if ("0".equals(right)) {
			hasRight = false;
		} else if ("1".equals(right)) {
			hasRight = true;
		}
		ClientSession client = WebUtil.getClientSession(request);
		boolean zgsflag = false;
		if (client.getCmpid() == SysFinal.SYSTEM_ZGSID) {
			zgsflag = true;
		}
		return hasRight && zgsflag;
	}

	public boolean isEdit(HttpServletRequest request, BaseForm form)
			throws Exception {
		return hasRight(request, form, FEDIT);
	}

	public boolean isSysEdit(HttpServletRequest request, BaseForm form)
			throws Exception {
		boolean flag = hasRight(request, form, FEDIT);
		ClientSession client = WebUtil.getClientSession();

		boolean systemflag = MathUtil.equal(client.getCmpid(),
				SysFinal.SYSTEM_ZGSID);
		return flag && systemflag;
	}

	public boolean isCmpEdit(HttpServletRequest request, BaseForm form)
			throws Exception {
		boolean systemflag = hasRight(request, form, FEDIT);
		boolean cmpflag = false;
		ClientSession client = WebUtil.getClientSession();
		if (client != null) {
			cmpflag = MathUtil.equal(client.getCmpid(), client.getParentid());
		}
		return systemflag && cmpflag;
	}

	public boolean isBrowse(HttpServletRequest request, BaseForm form)
			throws Exception {
		Boolean bl = hasRight(request, form, FBROWSE);
		return true;
	}

	public boolean isBrowseAmt(HttpServletRequest request, BaseForm form)
			throws Exception {
		int func = FBROWSEAMT;
		if (func == 0) {
			return true;
		}
		boolean hasRight = false;
		String rightBuffer = readRightString(request);
		if ("".equals(rightBuffer)) {
			return false;
		}
		String right = getRightChar(rightBuffer, func);
		if ("0".equals(right)) {
			hasRight = false;
		} else {
			hasRight = true;
		}
		return hasRight;

	}

	public boolean isReportBrowseAmt(HttpServletRequest request, BaseForm form)
			throws Exception {
		int func = FBROWSEAMT;
		if (func == 0) {
			return true;
		}
		boolean hasRight = false;
		String rightBuffer = readRightString(request);
		if ("".equals(rightBuffer)) {// 如果没有定义菜单就可能看的见。
			return true;
		}
		String right = getRightChar(rightBuffer, func);
		if ("0".equals(right)) {
			hasRight = false;
		} else {
			hasRight = true;
		}
		return hasRight;

	}

	public boolean isAudit(HttpServletRequest request, BaseForm form)
			throws Exception {
		Integer auditflag = getRightFlag(request, "auditflag");

		boolean audit = MathUtil.notEqual(auditflag, 1); // 未被审核
		boolean system = hasRightOnlySystem(request, form, FAUDIT);// 有审核权限
		boolean result = audit && system;
		return result;
	}

	public boolean isPrint(HttpServletRequest request, BaseForm form)
			throws Exception {
		return hasRightOnlySystem(request, form, FPRINT);
	}

	public Object getRightObject(HttpServletRequest request) {
		Object rightObject = request.getAttribute(RIGHT_OBJECT);
		return rightObject;
	}

	public Integer getRightFlag(HttpServletRequest request, String prop) {
		Integer result = 0;
		try {
			Object rightObject = getRightObject(request);
			if (rightObject != null) {
				String temp = BeanUtils.getProperty(rightObject, prop);
				if (temp != null) {
					result = new Integer(temp);
				}
			}
		} catch (Exception e) {
			StringBuffer error = new StringBuffer();
			error.append("取权限属性发生错误:");
			error.append(prop);
			SystemLogger.error(error.toString(), e);
		}
		return result;
	}

	

	// 下面是报表导出
	public boolean isExport(HttpServletRequest request, BaseForm form) {

		String reportid = RequestUtil.getParameter(request, "reportid");
		return isDownloadReport(request, reportid);

	}
	public boolean isDownloadReport(HttpServletRequest request, String reportid) {
		ReportForm form = (ReportForm) WebUtil.getForm(request);
		Object rightflag = request.getAttribute(REPORT_EXPORT_RIGHT_KEY);
		boolean result = false;
		if (rightflag != null) {
			result = "1".equals(rightflag.toString());
		} else {
			ClientSession client = WebUtil.getClientSession(request);
			IDao dao = WebUtil.getDao(request);
			Tsysmenu menu = getReportMenu(reportid);
			if (menu == null) {
				return true;
			}
			String right = null;
			if (client == null || client.isAdmin()) {
				form.setAuth_string(menu.getRightvalue());
				right = getRightChar(menu.getRightvalue(), FPRINT);
				boolean hasRight = false;
				if ("1".equals(right)) {
					hasRight = true;
				} else {
					hasRight = false;
				}
				result = hasRight;
			} else {
				Tbsuser user = (Tbsuser) dao.get(Tbsuser.class, client
						.getUserid());
				StringBuffer hsql = new StringBuffer();
				hsql
						.append(" select a from Tsysroledtl  a  where a.tsysroleid in(");
				hsql.append(user.getTsysroleids());
				hsql.append(") and a.tsysmenuid=");
				hsql.append(menu.getTsysmenuid());
				List list = dao.iterate(hsql.toString());
				for (Object o : list) {
					Tsysroledtl t = (Tsysroledtl) o;
					String auth2 = form.getAuth_string();
					String authStr = SysUtil
							.authMerge(t.getRightvalue(), auth2);
					form.setAuth_string(authStr);
				}
				right = getRightChar(form.getAuth_string(), FPRINT);
				boolean hasRight = false;
				if ("1".equals(right)) {
					hasRight = true;
				} else {
					hasRight = false;
				}
				result = hasRight;
			}
			if (result) {
				request.setAttribute(REPORT_EXPORT_RIGHT_KEY, 1);
			} else {
				request.setAttribute(REPORT_EXPORT_RIGHT_KEY, 0);
			}
		}
		return result;
	}
	public Tsysmenu getReportMenu(String code) {
		Tsysmenu po = null;
		StringBuffer hql = new StringBuffer();
		String url = ReportUtil.getMenuUrl(code);
		hql.append(" from Tsysmenu as a where a.accessurl ='").append(url)
				.append("'");
		po = (Tsysmenu) dao.get(hql.toString());
		return po;
	}
}
