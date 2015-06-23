package com.wxservice.service.report;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxservice.database.po.report.Trpplugin;
import com.wxservice.database.po.report.Trpreportdatasource;
import com.wxservice.database.po.report.Trpreportplugin;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.dao.support.Page;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.report.TrpreportdatasourceForm;
import com.wxservice.web.form.report.TrpreportpluginForm;
/**
 * 
 * 描述：   Services
 * 项目名称：七匹狼运动信息化平台   
 * 创建人：Gererator
 */
public class TrpreportpluginManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;
	public TrpreportpluginManager(){

	}
	public Class getEntityClass() {
		return Trpreportplugin.class;
	}
	public String getTableKeyField() {
		return "trpreportpluginid";
	}
	

	public void bindForm(BaseForm baseform, Object object) {
		if(object==null) return;
		Trpreportplugin po= (Trpreportplugin)object;
		TrpreportpluginForm    form=(TrpreportpluginForm)baseform;
		form.setTrpreportpluginid(po.getTrpreportpluginid());
				   form.setTrpreportid(po.getTrpreportid());
				   form.setName(po.getName());
				   form.setSpringname(po.getSpringname());
				   form.setFieldalias(po.getFieldalias());
				   form.setTemplate(po.getTemplate());
				
	}
	
	public void bindEntity(BaseForm baseform, Object object) {
	
		Trpreportplugin po= (Trpreportplugin)object;
		TrpreportpluginForm    form=(TrpreportpluginForm)baseform;
		po.setTrpreportpluginid(form.getTrpreportpluginid());
				   po.setTrpreportid(form.getTrpreportid());
				   po.setName(form.getName());
				   po.setSpringname(form.getSpringname());
				   po.setFieldalias(form.getFieldalias());
				   po.setTemplate(form.getTemplate());
				
	}
	
/*	@Override
	public void initFormCombo(ActionContext context) throws Exception {
		// TODO Auto-generated method stub
		TrpreportpluginForm form = (TrpreportpluginForm) context.getForm();
		List springnamelist = this.nameManager.getOptions(Trpplugin.class, "springname",
		"trppluginid");
		form.setSpringnamelist(springnamelist);
		
	}*/
	public IStrutsForward addSaveagain(ActionContext context) {
		TrpreportpluginForm form = (TrpreportpluginForm) context.getForm();
		String pk = null;
		this.addSave(context);
		form.setName(null);
		form.setSpringname(null);
		form.setTemplate(null);
		form.setFieldalias(null);
		form.setTrpreportpluginid(null);
//		this.add(context);
		processSuccess(context, true, pk);
		return forwardMethod(ADD_METHOD);
	}
	@Override
	public void addEx(ActionContext context) {
		// TODO Auto-generated method stub
		context.getRequest().setAttribute("methodflag", "addSave");
		TrpreportpluginForm form = (TrpreportpluginForm) context.getForm();
	}
	@Override
	public void updateEx(ActionContext context, Object entity) {
		// TODO Auto-generated method stub
		context.getRequest().setAttribute("methodflag", "update");
		
	}
	public boolean isUniques(Object entity) throws Exception {
		Trpreportplugin po = (Trpreportplugin) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreportplugin as a where a.name='");
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
	@Override
	public void addSaveEx(ActionContext context, Object entity, Operators os)
			 {
		// TODO Auto-generated method stub
		Trpreportplugin po = (Trpreportplugin) entity;
		TrpreportpluginForm form = (TrpreportpluginForm) context.getForm();
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpplugin as a where a.trppluginid='");
		hql.append(form.getTrppluginid());
		hql.append("'");
		/*List list = dao.iterate(hql.toString());
		Trpplugin trpplugi=(Trpplugin)list.get(0);
		po.setSpringname(trpplugi.getSpringname());
		po.setFieldalias(trpplugi.getFieldalias());
		po.setTemplate(trpplugi.getTemplate());*/
	}
	public void updateSaveEx(ActionContext context, Object entity,
			Operators os) {
		Trpreportplugin po=(Trpreportplugin)entity;
		StringBuffer hql=new StringBuffer();
		hql.append(" from Trpreportplugin as a where a.name='");
		hql.append(po.getName());
		hql.append("' and a.trpreportid=");
		hql.append(po.getTrpreportid());
		hql.append(" and a.trpreportpluginid!=");
		hql.append(po.getTrpreportpluginid());
		List list=dao.iterate(hql.toString());
		if(list.size()>0){
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
		
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
					if (isCanDelete(context, entity,pk)) {
						Operators os = new Operators();
						os.addDeleteObject(entity);
						deleteEx(context, entity, os);
						dao.execute(os);
					}else{
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


