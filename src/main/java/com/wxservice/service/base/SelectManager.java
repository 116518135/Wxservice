package com.wxservice.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ReflectionUtils;

import com.wxservice.database.po.base.Tbsuser;
import com.wxservice.database.po.report.Trphtmlcate;
import com.wxservice.database.po.system.Tsysdatadict;
import com.wxservice.framework.dao.support.Page;
import com.wxservice.framework.engine.BaseEngine;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.web.form.base.SelectForm;

public class SelectManager extends BaseEngine {

	
	public IStrutsForward selectTable(ActionContext context) {
		SelectForm form = (SelectForm) context.getForm();
		doSelectTable(context, form);
		return this.forwardJsp("selectTable");
	}
	

	
	public IStrutsForward selectTableMulti(ActionContext context) {
		SelectForm form = (SelectForm) context.getForm();
		doSelectTable(context, form);
		return this.forwardJsp("selectTableMulti");
	}
	public IStrutsForward selectAppUserTableMulti(ActionContext context) {
		SelectForm form = (SelectForm) context.getForm();
		doSelectAppUserTable(context, form);
		return this.forwardJsp("selectTableMulti");
	}
	
	private void doSelectTable(ActionContext context, SelectForm form) {
		try {
			String classname = SysUtil.getClassname(form.getTable());
			StringBuffer hql = new StringBuffer();
			hql.append(" from ");

			hql.append(classname);

			hql.append(" where 1=1 ");
			if (StringUtil.isNotBlank(form.getCondition1())) {
				hql.append(" and code like '%").append(
						form.getCondition1().trim()).append("%'");
			}
			if (StringUtil.isNotBlank(form.getCondition2())) {
				hql.append(" and name like '%").append(
						form.getCondition2().trim()).append("%'");
			}
			
			if (StringUtil.isNotBlank(form.getQuerystring())) {
				hql.append(" and ");
				hql.append(form.getQuerystring());
			}
			hql.append(" order by code");
			SystemLogger.debug(hql.toString());
			int pageNumber = form.getEc_p();
			int limit = form.getEc_crd();
			Page page = dao.iterate(hql.toString(), pageNumber, limit);
			form.setPage(page);
			List list = (List) page.getResult();
			String tablekey = classname.toLowerCase() + "id";
			List<Map> webList = new ArrayList<Map>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Object entity = list.get(i);
					Map model = new HashMap();
					model.put("code", BeanUtils.getProperty(entity, "code"));
					model.put("name", BeanUtils.getProperty(entity, "name"));
					model.put("tableid", BeanUtils
							.getProperty(entity, tablekey));
					webList.add(model);

				}
			}
			context.getRequest().setAttribute("LISTS", webList);
		} catch (Exception e) {
			SystemLogger.error("选择的时候发生错误(doSelectTable)", e);
		}
	}
	
	private void doSelectAppUserTable(ActionContext context, SelectForm form) {
		try {
			String classname = "Tsysdatadictdtl";
			StringBuffer hql = new StringBuffer();
			hql.append(" from ");

			hql.append(classname);

			hql.append(" where 1=1 ");
			if (StringUtil.isNotBlank(form.getCondition1())) {
				hql.append(" and code like '%").append(
						form.getCondition1().trim()).append("%'");
			}
			if (StringUtil.isNotBlank(form.getCondition2())) {
				hql.append(" and name like '%").append(
						form.getCondition2().trim()).append("%'");
			}
			
			Tsysdatadict po  = nameManager.getDatadict("role");
			hql.append(" and tsysdatadictid=").append(po.getTsysdatadictid());
			
			if (StringUtil.isNotBlank(form.getQuerystring())) {
				hql.append(" and ");
				hql.append(form.getQuerystring());
			}
			
			hql.append(" order by code");
			SystemLogger.debug(hql.toString());
			int pageNumber = form.getEc_p();
			int limit = form.getEc_crd();
			Page page = dao.iterate(hql.toString(), pageNumber, limit);
			form.setPage(page);
			List list = (List) page.getResult();
			String tablekey = classname.toLowerCase() + "id";
			List<Map> webList = new ArrayList<Map>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Object entity = list.get(i);
					Map model = new HashMap();
					model.put("code", BeanUtils.getProperty(entity, "code"));
					model.put("name", BeanUtils.getProperty(entity, "name"));
					model.put("tableid", BeanUtils
							.getProperty(entity, tablekey));
					webList.add(model);

				}
			}
			context.getRequest().setAttribute("LISTS", webList);
		} catch (Exception e) {
			SystemLogger.error("选择的时候发生错误(doSelectTable)", e);
		}
	}
	
	
	
	
	
	public IStrutsForward selectUser(ActionContext context) {
		SelectForm form = (SelectForm) context.getForm();
		doSelectUser(context, form);
		return this.forwardJsp("selectUser");
	}

	public IStrutsForward selectUserMulti(ActionContext context) {
		SelectForm form = (SelectForm) context.getForm();
		doSelectUser(context, form);
		return this.forwardJsp("selectUserMulti");
	}
	

	
	
	private void doSelectUser(ActionContext context, SelectForm form) {
		try {
			StringBuffer hql = new StringBuffer();
			hql
					.append(" from Tbsuser as a  where tbsuserid>0 and ifnull(a.stopflag,0)!=1 ");
			if (StringUtil.isNotBlank(form.getCondition1())) {
				hql.append(" and a.code like '%").append(
						form.getCondition1().trim()).append("%'");
			}
			if (StringUtil.isNotBlank(form.getCondition2())) {
				hql.append(" and a.name like '%").append(
						form.getCondition2().trim()).append("%'");
			}
			if (StringUtil.isNotBlank(form.getQuerystring())) {
				hql.append(" and ");
				hql.append(form.getQuerystring());
			}
			hql.append(" order by a.code");
			int pageNumber = form.getEc_p();
			int limit = form.getEc_crd();
			Page page = dao.iterate(hql.toString(), pageNumber, limit);
			form.setPage(page);
			List list = (List) page.getResult();

			List<Map> webList = new ArrayList<Map>();
			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {
					Map model = new HashMap();
					Tbsuser entity = (Tbsuser) list.get(i);
					model.put("code", entity.getCode());
					model.put("name", entity.getName());

					model.put("tbsuserid", entity.getTbsuserid());
					webList.add(model);
				}
			}
			context.getRequest().setAttribute("LISTS", webList);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
	}
	// 报表设计增加条件
		public IStrutsForward selectTrpreportcondition(ActionContext context) {
			SelectForm form = (SelectForm) context.getForm();
			doSelectTrpreportcondition(context, form);
			return this.forwardJsp("selectTrpreportconditionTable");
		}
		private void doSelectTrpreportcondition(ActionContext context,
				SelectForm form) {
			try {
				StringBuffer hql = new StringBuffer();
				hql.append(" from Trphtmlctrl");
				hql.append(" where 1=1 ");
				if (StringUtil.isNotBlank(form.getCondition2())) {
					hql.append(" and trphtmlcateid=").append(
							form.getCondition2().trim());
				}
				if (StringUtil.isNotBlank(form.getQuerystring())) {
					hql.append(" and ");
					hql.append(form.getQuerystring());
				}
				int pageNumber = form.getEc_p();
				int limit = form.getEc_crd();
				Page page = dao.iterate(hql.toString(), pageNumber, limit);
				form.setPage(page);
				List list = (List) page.getResult();

				List<Map> webList = new ArrayList<Map>();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Object entity = list.get(i);
						Map model = new HashMap();
						model.put("name", BeanUtils.getProperty(entity, "name"));
						model
								.put("jsname", BeanUtils.getProperty(entity,
										"jsname"));
						model.put("tableid", BeanUtils
								.getProperty(entity, "trphtmlctrlid"));
						webList.add(model);

					}
				}
				List<LabelValue> trphtmlcateidlist = nameManager.getOptions(
						Trphtmlcate.class, "name", "trphtmlcateid");
				SelectForm selectForm = form;
				form.setList1(trphtmlcateidlist);
				context.getRequest().setAttribute("LISTS", webList);
			} catch (Exception e) {
				SystemLogger.error("选择的时候发生错误(selectTrpreportcondition)", e);
			}
		}
		//---------------------------只显示名称，不显示编号。begin--------------------------------
		public IStrutsForward selectTable1(ActionContext context) {
			SelectForm form = (SelectForm) context.getForm();
			doSelectTable1(context, form);
			return this.forwardJsp("selectTable1");
		}
		private void doSelectTable1(ActionContext context, SelectForm form) {
			try {
				String classname = SysUtil.getClassname(form.getTable());
				String tableid=classname.toLowerCase()+"id";
				StringBuffer hql = new StringBuffer();
				hql.append(" from ");

				hql.append(classname);

				hql.append(" where 1=1 ");
	
				if (StringUtil.isNotBlank(form.getCondition2())) {
					hql.append(" and name like '%").append(
							form.getCondition2().trim()).append("%'");
				}
				if (StringUtil.isNotBlank(form.getQuerystring())) {
					hql.append(" and ");
					hql.append(form.getQuerystring());
				}
				hql.append(" order by ").append(tableid).append(" desc");
				SystemLogger.debug(hql.toString());
				int pageNumber = form.getEc_p();
				int limit = form.getEc_crd();
				Page page = dao.iterate(hql.toString(), pageNumber, limit);
				form.setPage(page);
				List list = (List) page.getResult();
				String tablekey = classname.toLowerCase() + "id";
				List<Map> webList = new ArrayList<Map>();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Object entity = list.get(i);
						Map model = new HashMap();
						model.put("name", BeanUtils.getProperty(entity, "name"));
						model.put("tableid", BeanUtils
								.getProperty(entity, tablekey));
						webList.add(model);

					}
				}
				context.getRequest().setAttribute("LISTS", webList);
			} catch (Exception e) {
				SystemLogger.error("选择的时候发生错误(doSelectTable1)", e);
			}
		}
		//---------------------------end--------------------------------
}