package com.wxservice.service.report;

import com.wxservice.database.po.report.Trpreport;
import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportdatasource;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.web.form.report.TrpreportForm;
import com.wxservice.web.form.report.TrpreportdatasourceForm;

import java.io.Reader;
import java.sql.Clob;
import java.util.List;
import java.util.Map;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Hibernate;


/**
 * 
 * 描述： Services 项目名称：七匹狼运动信息化平台 创建人：Gererator  
 */
public class TrpreportdatasourceManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TrpreportdatasourceManager() {

	}

	public Class getEntityClass() {
		return Trpreportdatasource.class;
	}

	public String getTableKeyField() {
		return "trpreportdatasourceid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Trpreportdatasource po = (Trpreportdatasource) object;
		TrpreportdatasourceForm form = (TrpreportdatasourceForm) baseform;
		form.setTrpreportdatasourceid(po.getTrpreportdatasourceid());
		form.setTrpreportid(po.getTrpreportid());
		form.setName(po.getName());
		form.setContent(po.getContent());
		form.setDatasourcetype(po.getDatasourcetype());

	}

	public void bindEntity(BaseForm baseform, Object object) {

		Trpreportdatasource po = (Trpreportdatasource) object;
		TrpreportdatasourceForm form = (TrpreportdatasourceForm) baseform;
		po.setTrpreportdatasourceid(form.getTrpreportdatasourceid());
		po.setTrpreportid(form.getTrpreportid());
		po.setName(form.getName());
		po.setContent(form.getContent());
		po.setDatasourcetype(form.getDatasourcetype());

	}

	public boolean isUniques(Object entity) throws Exception {
		Trpreportdatasource po = (Trpreportdatasource) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreportdatasource as a where a.name='");
		hql.append(po.getName());
		hql.append("'");
		hql.append(" and a.trpreportid=");
		hql.append(po.getTrpreportid());
		Object o = dao.get(hql.toString());
		if (o == null) {
			return true;
		} else {
			return false;
		}

	}

	public void updateSaveEx(ActionContext context, Object entity, Operators os) {
		Trpreportdatasource po = (Trpreportdatasource) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreportdatasource as a where a.name='");
		hql.append(po.getName());
		hql.append("' and a.trpreportid=");
		hql.append(po.getTrpreportid());
		hql.append(" and a.trpreportdatasourceid!=");
		hql.append(po.getTrpreportdatasourceid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}

	}

	@Override
	public void initFormCombo(ActionContext context) {
		// TODO Auto-generated method stub
		TrpreportdatasourceForm form = (TrpreportdatasourceForm) context
				.getForm();
		List datasourcetypelist = ReportUtil.getDatasourcetypeOption();
		form.setDatasourcetypelist(datasourcetypelist);

	}

	@Override
	public void createListHqlWhere(StringBuffer hql, ActionContext context) {
		// TODO Auto-generated method stub
		TrpreportdatasourceForm form = (TrpreportdatasourceForm) context
				.getForm();
		hql.append("and trpreportid=");
		hql.append(form.getTrpreportid());
	}

	public IStrutsForward addSaveagain(ActionContext context) {
		TrpreportdatasourceForm form = (TrpreportdatasourceForm) context
				.getForm();
		String pk = null;
		form.setContent(null);
		this.addSave(context);
		form.setName(null);
		form.setContent(null);
		form.setDatasourcetype(null);
		form.setTrpreportdatasourceid(null);
		// this.add(context);
		processSuccess(context, true, pk);
		return forwardMethod(ADD_METHOD);
	}

	@Override
	public void listEx(ActionContext context, Map model, Object entity)
			throws Exception {
		// TODO Auto-generated method stub
		SysUtil.copyProperties(model, entity);
		Trpreportdatasource trpreportdatasource = (Trpreportdatasource) entity;
		List<LabelValue> labelValue = ReportUtil.getDatasourcetypeOption();
		model.put("datasourcetype", labelValue.get(
				trpreportdatasource.getDatasourcetype()).getLabel());

	}

	@Override
	public void viewEx(ActionContext context, Object entity){
		// TODO Auto-generated method stub
		this.initFormCombo(context);

	}

	@Override
	public void addEx(ActionContext context) {
		// TODO Auto-generated method stub
		context.getRequest().setAttribute("methodflag", "addSave");
	}

	@Override
	public void updateEx(ActionContext context, Object entity) {
		// TODO Auto-generated method stub
		context.getRequest().setAttribute("methodflag", "update");
	}

	@Override
	public IStrutsForward delete(ActionContext context) {
		// TODO Auto-generated method stub
		String pk = null;
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				// 得到实体
				Object entity = getEntity(context);
				if (entity == null) {
					BusinessException exception = new BusinessException(
							NOEXISTRECORD);
					throw exception;
				} else {
					pk = getIdValue(entity);
					if (isCanDelete(context, entity, pk)) {
						Operators os = new Operators();
						os.addDeleteObject(entity);
						deleteEx(context, entity, os);
						dao.execute(os);
					} else {
						BusinessException exception = new BusinessException(
								"当前数据正在使用,不能删除.");
						throw exception;
					}
				}
			}

		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod(VIEW_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardJsp(DELETE_METHOD);
	}

}
