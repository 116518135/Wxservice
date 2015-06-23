package com.wxservice.service.report;


import java.util.HashMap;
import java.util.Map;

import com.wxservice.database.po.report.Trphtmlctrl;
import com.wxservice.database.po.report.Trpplugin;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.report.TrphtmlcateForm;
import com.wxservice.web.form.report.TrppluginForm;


public class TrppluginManager extends CRUDOperatorImpl {

	public TrppluginManager() {

	}

	@Override
	public Class getEntityClass() {
		// TODO Auto-generated method stub
		return Trpplugin.class;
	}

	@Override
	public String getTableKeyField() {
		// TODO Auto-generated method stub
		return "trppluginid";
	}

	@Override
	public void bindForm(BaseForm form, Object entity) {

		Trpplugin trpplugin = (Trpplugin) entity;
		TrppluginForm trppluginForm = (TrppluginForm) form;
		trppluginForm.setTrppluginid(trpplugin.getTrppluginid());
		trppluginForm.setCode(trpplugin.getCode());
		trppluginForm.setName(trpplugin.getName());
		trppluginForm.setSpringname(trpplugin.getSpringname());
		trppluginForm.setFieldalias(trpplugin.getFieldalias());
		trppluginForm.setTemplate(trpplugin.getTemplate());
	}
	@Override
	public void bindEntity(BaseForm baseform, Object object) {
		Trpplugin po = (Trpplugin) object;
		TrppluginForm form = (TrppluginForm) baseform;
		po.setTrppluginid(form.getTrppluginid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		po.setSpringname(form.getSpringname());
		po.setFieldalias(form.getFieldalias());
		po.setTemplate(form.getTemplate());

	}

	@Override
	public boolean isUniques(Object entity) throws Exception {
		Trpplugin po = (Trpplugin) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpplugin as a where a.code='");
		hql.append(po.getCode());
		hql.append("'");
		Object o = dao.get(hql.toString());
		if (o == null) {
			return true;
		} else {
			return false;
		}

	}
	//得到
	public IStrutsForward getInfo(ActionContext context) {
		try {
			Map map = new HashMap();
			TrppluginForm form = (TrppluginForm) context.getForm();
			StringBuffer sql = new StringBuffer("from Trpplugin where 1 =1 ");
			if (StringUtil.isNotBlank(form.getTrppluginid()+"")) {
				sql.append("and trppluginid = ");
				sql.append(form.getTrppluginid());
			}
			Trpplugin po= (Trpplugin)dao.get(sql.toString());
			map.put("fieldalias", po.getFieldalias());
			map.put("template", po.getTemplate());
			map.put("springname", po.getSpringname());
			processSuccess(context, false);
			return this.forwardJson(map);
		} catch (Exception e) {
			processFailure(context, e, false);
			return this.forwardJsp(MAIN_PAGE);
		}
	}
}
