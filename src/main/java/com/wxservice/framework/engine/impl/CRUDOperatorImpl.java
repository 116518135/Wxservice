package com.wxservice.framework.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.wxservice.framework.components.right.BaseRightService;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.dao.support.Page;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;


public abstract class CRUDOperatorImpl extends AbstractCRUDOperator {
	@Override
	public IStrutsForward list(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		try {
			StringBuffer hql = new StringBuffer();
			createListHql(form, hql);
			createListHqlWhere(hql, context);
			SystemLogger.debug(hql.toString());
			int pageNumber = form.getEc_p();
			int limit = form.getEc_crd();
			Page page = dao.iterate(hql.toString(), pageNumber, limit);
			form.setPage(page);
			List list = (List) page.getResult();
			List<Map> webList = new ArrayList<Map>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map model = new HashMap();
					Object entity = list.get(i);
					listEx(context, model, entity);
					webList.add(model);
				}

			}
			context.getRequest().setAttribute("LISTS", webList);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardJsp(LIST_PAGE);
		}
		processSuccess(context, false);
		return forwardJsp(LIST_PAGE);
	}

	@Override
	public IStrutsForward add(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		try {
			token.saveToken(context.getRequest());
			initFormCombo(context);
			addEx(context);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(LIST_METHOD);
		}
		processSuccess(context, false);
		form.setMethod("addSave");
		return forwardJsp(EDIT_PAGE);
	}

	@Override
	public IStrutsForward addSave(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		String pk = null;
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				// 生成实例
				Object entity = createEntity();
				bindEntity(context.getForm(), entity);
				// 判断主键是否重复
				boolean isUnique = isUniques(entity);
				if (!isUnique) {
					BusinessException exception = new BusinessException("主键重复");
					throw exception;
				} else {
					Operators os = new Operators();
					os.addAddObject(entity);
					addSaveEx(context, entity, os);
					dao.execute(os);
					pk = getIdValue(entity);
					BeanUtils.setProperty(context.getForm(), this
							.getTableKeyField(), pk);
				}
			}
		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod(ADD_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardMethod(VIEW_METHOD);
	}
	
	@Override
	public IStrutsForward view(ActionContext context) {
        BaseForm form = (BaseForm) context.getForm();
        String pk=null;
		try {
			token.saveToken(context.getRequest());
			Object entity = getEntity(context);
			if (entity == null) {
				BusinessException exception = new BusinessException(
						NOEXISTRECORD);
				throw exception;
			} else {
				pk = getIdValue(entity);
				bindForm(context.getForm(), entity);
	
				viewEx(context, entity);
				context.getRequest().setAttribute(BaseRightService.RIGHT_OBJECT, entity);
			}
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(LIST_METHOD);
		}
		processSuccess(context, false);
		return forwardJsp(VIEW_PAGE);
	}
	@Override
	public IStrutsForward update(ActionContext context) {
		try {
			token.saveToken(context.getRequest());//保存token
			Object entity = getEntity(context);
			
			this.bindForm(context.getForm(), entity);
			initFormCombo(context);
			initFormName(context,entity);
			updateEx(context, entity);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(VIEW_METHOD);
		}
		processSuccess(context, false);
		context.getForm().setMethod("updateSave");
		return forwardJsp(EDIT_PAGE);
	}
	
	@Override
	public IStrutsForward updateSave(ActionContext context) {
        String pk=null;
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				// 修改保存
				Object entity = getEntity(context);
				if (entity == null) {
					BusinessException exception = new BusinessException(
							NOEXISTRECORD);
					throw exception;
				} else {
					pk = getIdValue(entity);
					Operators os = new Operators();
					os.addUpdateObject(entity);
					bindEntity(context.getForm(), entity);
					updateSaveEx(context, entity, os);
					dao.execute(os);
				}
			}
		} catch (Exception e) {
			processFailure(context, e, true,pk);
			return forwardMethod(UPDATE_METHOD);
		}
		processSuccess(context, true,pk);
		return forwardMethod(VIEW_METHOD);
	}
	
	@Override
	public IStrutsForward delete(ActionContext context) {
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
		return forwardMethod(LIST_METHOD);
	}



	@Override
	public void createListHql(BaseForm form, StringBuffer hql) {
		hql.append(" from ").append(getEntityName()).append(
				"  entity where 1=1 ");
		String findwhere = form.getFindwhere();
		if (StringUtils.isNotBlank(findwhere)) {
			hql.append(findwhere).append(" ");
		}
	}

	@Override
	public void createListHqlWhere(StringBuffer hql, ActionContext context) {

	}

	@Override
	public void listEx(ActionContext context, Map model, Object entity)
			throws Exception {
		SysUtil.copyProperties(model, entity);
	}


	@Override
	public void bindEntity(BaseForm form, Object entity) {

	}

	@Override
	public void bindForm(BaseForm form, Object entity) {

	}

	@Override
	public void initFormCombo(ActionContext context) {

	}

	@Override
	public void initFormName(ActionContext context,Object entity) {

	}

	
	@Override
	public void addEx(ActionContext context) {

	}

	

	@Override
	public void addSaveEx(ActionContext context, Object entity, Operators os) {

	}

	@Override
	public boolean isCanDelete(ActionContext context, Object entity,String pkid) throws Exception {
		
		return isCanDelete(getTableKeyField(),pkid,"");
	}

	
	@Override
	public void deleteEx(ActionContext context, Object entity, Operators os) {

	}

	@Override
	public IStrutsForward find(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		try {
			initFormCombo(context);
		} catch (Exception e) {
			processFailure(context, e, false);
		}
		processSuccess(context, false);
		return forwardJsp(FIND_PAGE);
	}



	

	@Override
	public void updateSaveEx(ActionContext context, Object entity,
			Operators os) {
		

	}

	

	@Override
	public void viewEx(ActionContext context, Object entity) {
       
	}


	@Override
	public void updateEx(ActionContext context, Object entity) {
		
	}


}
