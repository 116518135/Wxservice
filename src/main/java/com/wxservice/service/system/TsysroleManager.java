package com.wxservice.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.system.Tsysmenu;
import com.wxservice.database.po.system.Tsysrole;
import com.wxservice.database.po.system.Tsysroledtl;
import com.wxservice.framework.components.License.LicenseService;
import com.wxservice.framework.components.right.BaseRightService;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.web.form.system.TsysroleForm;

/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class TsysroleManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;
	public static final String checkbox_prex = "auth_";

	public static final String pos_right = "0000000";

	public TsysroleManager() {

	}

	public Class getEntityClass() {
		return Tsysrole.class;
	}

	public String getTableKeyField() {
		return "tsysroleid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tsysrole po = (Tsysrole) object;
		TsysroleForm form = (TsysroleForm) baseform;
		form.setTsysroleid(po.getTsysroleid());
		form.setCode(po.getCode());
		form.setName(po.getName());
		form.setTbscmpid(po.getTbscmpid());
		form.setCreatetbscmpid(po.getCreatetbscmpid());

		form.setMemo(po.getMemo());
		form.setPublicflag(po.getPublicflag());

	}

	public void bindEntity(BaseForm baseform, Object object) {

		Tsysrole po = (Tsysrole) object;
		TsysroleForm form = (TsysroleForm) baseform;
		po.setTsysroleid(form.getTsysroleid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		po.setTbscmpid(form.getTbscmpid());
		po.setMemo(form.getMemo());
		po.setPublicflag(form.getPublicflag());

	}

	public boolean isUniques(Object entity) throws Exception {
		Tsysrole po = (Tsysrole) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysrole as a where a.code='");
		hql.append(po.getCode());
		hql.append("'");
		Object o = dao.get(hql.toString());
		if (o == null) {
			return true;
		} else {
			return false;
		}

	}

	public void updateSaveEx(ActionContext context, Object entity, Operators os) {
		Tsysrole po = (Tsysrole) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysrole as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.tsysroleid!=");
		hql.append(po.getTsysroleid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}

	@Override
	public void listEx(ActionContext context, Map model, Object entity)
			throws Exception {
		SysUtil.copyProperties(model, entity);
		Tsysrole po = (Tsysrole) entity;


	}

	public void addSaveEx(ActionContext context, Object entity, Operators os) {
		Tsysrole po = (Tsysrole) entity;
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		po.setCreatetbscmpid(client.getCmpid());

	}

	@Override
	public void createListHqlWhere(StringBuffer hql, ActionContext context) {

	}

	@Override
	public void addEx(ActionContext context) {
		TsysroleForm form = (TsysroleForm) context.getForm();
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		form.setTbscmpid(client.getCmpid());
		form.setCmpname(client.getCmpname());
	}

	@Override
	public void initFormName(ActionContext context, Object entity) {
		TsysroleForm form = (TsysroleForm) context.getForm();
		Tsysrole po = (Tsysrole) entity;


	}

	@Override
	public void viewEx(ActionContext context, Object entity) {
		initFormCombo(context);
		initFormName(context, entity);
	}

	private String getCmpIndexChar(Tsysmenu menu, int kind) {
		String cmprightvalue = StringUtil.notNull(menu.getCmprightvalue());
		int jmdkind = BaseRightService.cmpprop_JMD;
		int zydkind = BaseRightService.cmpprop_ZYD;
		if (kind == BaseRightService.cmpprop_GYS) {
			String indexChar1 = substringCmp(menu, menu.getCmprightvalue(),
					kind);
			return indexChar1;
		} else {
			String indexChar1 = substringCmp(menu, menu.getCmprightvalue(),
					kind);
			String indexChar2 = substringCmp(menu, menu.getCmprightvalue(),
					jmdkind);
			String indexChar3 = substringCmp(menu, menu.getCmprightvalue(),
					zydkind);
			StringBuffer buf = new StringBuffer();
			buf.append(indexChar1);
			buf.append(indexChar2);
			buf.append(indexChar3);
			if (buf.indexOf(BaseRightService.right_canedit) >= 0) {
				return BaseRightService.right_canedit;
			}

			if (buf.indexOf(BaseRightService.right_onlybrowse) >= 0) {
				return BaseRightService.right_onlybrowse;
			}

			return BaseRightService.right_onlysystemedit;
		}
	}

	public String substringCmp(Tsysmenu menu, String cmprightvalue, int kind) {
		if (cmprightvalue.length() < kind + 1) {
			SystemLogger.warn(menu.getName() + "菜单组织权限定义有错误:" + cmprightvalue);
			return "0";
		}
		return cmprightvalue.substring(kind, kind + 1);
	}

	private String getMenuIndexChar(Tsysmenu menu, int index) {
		String rightvalue = StringUtil.notNull(menu.getRightvalue());
		if (rightvalue.length() < index) {
			SystemLogger.warn(menu.getName() + "菜单权限定义有错误:" + rightvalue);
			return "0";
		}
		return rightvalue.substring(index - 1, index);
	}

	public IStrutsForward updateAuth(ActionContext context) {
		BusinessException be = null;
		try {
			List checkboxList = new ArrayList();
			token.saveToken(context.getRequest());// 生成令牌
			context.getForm().setMethod("updataAuthSave");
			ClientSession client = WebUtil.getClientSession(context
					.getRequest());
			TsysroleForm form = (TsysroleForm) context.getForm();
			Tsysrole role = (Tsysrole) dao.get(Tsysrole.class, form
					.getTsysroleid());
			
			int kind = 0;
			StringBuffer html = new StringBuffer();
			html
					.append("<table width='100%'  width='100%' style='table-layout:fixed;' border='0' cellspacing='1' cellpadding='1'>");
			html.append("\n");
			List list = dao
					.iterate("from Tsysmenu as a where a.parentid=0 order by a.indexno asc ");// 菜单
			for (int i = 0; i < list.size(); i++) {
				Tsysmenu menu = (Tsysmenu) list.get(i);
				
				// 判断当前菜单有没有权
				String indexChar = this.getCmpIndexChar(menu, kind);

				if (BaseRightService.right_onlysystemedit.equals(indexChar)) {// 只有超级系统管理员才有编辑权限
					continue;// 也不需要设置
				} else if (BaseRightService.right_onlybrowse.equals(indexChar)) {// 只能读

				} else if (BaseRightService.right_canedit.equals(indexChar)) {// 具有编辑权限

				} else {
					continue;
				}
				String chkname = this.checkbox_prex
						+ menu.getTsysmenuid().toString();
				html.append("<tr class='label'>");
				html.append("<td width='10%'>");
				html.append("----");
				html.append(menu.getName());
				html.append("</td><td align='left' width='90%'>");
				html
						.append("<input type='checkbox' value='1' name='allsel' onclick='allSelect2(this,\"");
				html.append(chkname);
				html.append("\")'>");
				html.append("全选");
				html.append("</td>");
				html.append("</tr>\n");
				this.doMenus(context.getRequest(), form, menu, html,
						checkboxList, kind, chkname);

			}
			html.append("</table>");
			form.setHtml(html.toString());
			form.setCheckboxList(checkboxList);

		} catch (BusinessException e) {
			processFailure(context, e, false, "进入设置权限");
			return this.forwardMethod("view");
		}
		processSuccess(context, false, "进入设置权限");
		return this.forwardJsp("auth");
	}

	public void doMenus(HttpServletRequest request, TsysroleForm form,
			Tsysmenu pmenu, StringBuffer html, List checkboxList, Integer kind,
			String chkname) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Tsysmenu as a where a.parentid=");
		hql.append(pmenu.getTsysmenuid());
		hql.append(" order by a.indexno asc");
		List subList = dao.iterate(hql.toString());
		for (Object o1 : subList) {
			Tsysmenu submenu = (Tsysmenu) o1;
			
			if (StringUtil.isBlank(submenu.getAccessurl())) {
				// String tr=this.processMenu(form, submenu, "label", kind,
				// chkname, checkboxList);
				// html.append(tr);
				chkname = chkname + submenu.getTsysmenuid().toString();
				html.append("<tr class='label'>");
				html.append("<td width='10%'>");
				html.append("----");
				html.append(submenu.getName());
				html.append("</td><td align='left' width='90%'>");
				html.append("</td>");
				html.append("</tr>\n");
				this.doMenus(request, form, submenu, html, checkboxList, kind,
						chkname);
			} else {

				String tr = this.processMenu(form, submenu, "input", kind,
						chkname, checkboxList);
				html.append(tr);
			}

		}
	}

	public String processMenu(TsysroleForm form, Tsysmenu submenu,
			String styleclass, Integer kind, String chkname, List checkboxList) {
		String idxChar = this.getCmpIndexChar(submenu, kind);
		if (BaseRightService.right_onlysystemedit.equals(idxChar)) {// 只有超级系统管理员才有编辑权限
			return "";// 也不需要设置
		} else if (BaseRightService.right_onlybrowse.equals(idxChar)) {// 只能读

		} else if (BaseRightService.right_canedit.equals(idxChar)) {// 具有编辑权限

		} else {
			return "";
		}
		String newcheckname = chkname + "_"
				+ submenu.getTsysmenuid().toString();
		checkboxList.add(new LabelValue(newcheckname, submenu.getTsysmenuid()
				.intValue()));
		StringBuffer tr = new StringBuffer();
		tr.append("<tr class='");
		tr.append(styleclass);
		tr.append("'>");
		tr.append("<td width='10%' align='right'>");
		tr
				.append("<input type='checkbox' value='1' name='allsel' onclick='allSelect(this,\"");
		tr.append(newcheckname);
		tr.append("\")'>");

		tr.append(submenu.getName());
		tr.append("</td>");
		tr.append("<td width='90%'  align='left'>");
		List operlist = nameManager.getFuncList("func");
		for (int loop = 0; loop < operlist.size(); loop++) {
			LabelValue operinfo = (LabelValue) operlist.get(loop);

			if (BaseRightService.right_onlybrowse.equals(idxChar)) {// 只具有只读权限
				if (loop == 0) {// 默认第一个是浏览权限
					tr.append("<input type='checkbox' value='1' name='");
					tr.append(newcheckname);
					tr.append("' ");
					tr.append(this.genIsSelected(form.getTsysroleid(), submenu
							.getTsysmenuid(), 1));
					tr.append(" />");
					tr.append(operinfo.getValue());
				} else {
					tr.append("<input type='hidden' value='2' name='");
					tr.append(newcheckname);
					tr.append("'/>");
				}
			} else {
				int indexno = MathUtil.parsetInt(operinfo.getLabel(), 0);
				String indexnochar = this.getMenuIndexChar(submenu, indexno);
				if ("2".equals(indexnochar)) {// 2 不需要这个权限
					tr.append("<input type='hidden' value='2' name='");
					tr.append(newcheckname);
					tr.append("'/>");
				} else {
					// 判断是不是需要打上勾:
					tr.append("<input type='checkbox' value='1' name='");
					tr.append(newcheckname);
					tr.append("' ");
					tr.append(this.genIsSelected(form.getTsysroleid(), submenu
							.getTsysmenuid(), indexno));
					tr.append(" />");
					tr.append(operinfo.getValue());
				}

			}

		}

		tr.append("</td>");
		tr.append("</tr>\n");
		return tr.toString();
	}

	public String genIsSelected(Integer roleid, Integer menuid, int indexno) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysroledtl as a where a.tsysroleid=");
		hql.append(roleid);
		hql.append(" and a.tsysmenuid=");
		hql.append(menuid);
		Tsysroledtl rr = (Tsysroledtl) dao.get(hql.toString());
		if (rr == null)
			return "";
		String ch = "";
		try {
			ch = rr.getRightvalue().substring(indexno - 1, indexno);
		} catch (Exception e) {

		}
		if ("1".equals(ch)) {
			return "checked";
		} else {
			return "";
		}
	}

	public IStrutsForward updateAuthSave(ActionContext context) {
		BusinessException be = null;
		String pk = null;
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				TsysroleForm form = (TsysroleForm) context.getForm();
				Operators os = new Operators();
				String deletesql = "delete from Tsysroledtl where tsysroleid="
						+ form.getTsysroleid().toString();
				os.addScriptObject(deletesql);
				List authlist = StringUtil.string2List(form.getAuthbuf(), ";");
				for (Object o : authlist) {
					String authstr = (String) o;
					List auths = StringUtil.string2List(authstr, ",");
					int menuid = MathUtil
							.parsetInt(auths.get(0).toString(), -1);
					String rightvalue = auths.get(1).toString();
					Tsysroledtl rr = new Tsysroledtl();
					rr.setTsysmenuid(menuid);
					rr.setTsysroleid(form.getTsysroleid());
					rr.setRightvalue(rightvalue);
					os.addAddObject(rr);
				}
				dao.execute(os);
			}

		} catch (Exception e) {
			processFailure(context, e, true, "设置权限");
			return this.forwardMethod("updateAuth");
		}
		processSuccess(context, true, "设置权限");
		return this.forwardMethod("updateAuth");
	}

	public IStrutsForward copySave(ActionContext context) {
		BusinessException be = null;
		String pk = null;
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				TsysroleForm form = (TsysroleForm) context.getForm();
				Operators os = new Operators();
				Tsysrole role = (Tsysrole) dao.get(Tsysrole.class, form
						.getTsysroleid());
				role.setCode(role.getCode() + "_copy");
				role.setName(role.getName() + "_copy");
				String sql = " from Tsysroledtl as a  where a.tsysroleid="
						+ form.getTsysroleid().toString();
				Integer id = (Integer) dao.addSave(role);
				List list = dao.iterate(sql);
				for (Object o : list) {
					Tsysroledtl rr = (Tsysroledtl) o;
					rr.setTsysroleid(id);
					os.addAddObject(rr);
				}
				dao.execute(os);
				form.setTsysroleid(id);
			}

		} catch (Exception e) {
			processFailure(context, e, true, "复制权限");
			return this.forwardMethod(this.VIEW_METHOD);
		}
		processSuccess(context, true, "复制权限");
		return this.forwardMethod(this.VIEW_METHOD);
	}

}
