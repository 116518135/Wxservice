package com.wxservice.framework.engine.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.ClassUtils;

import com.wxservice.framework.components.right.BaseRightService;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.dao.support.Page;
import com.wxservice.framework.engine.ICRUDLineOperator;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;

public abstract class CRUDLineOperatorImpl extends CRUDOperatorImpl implements
		ICRUDLineOperator {
	/**
	 * 将取到实体 mapping到map的时候的扩展，默认有一个全部的
	 * 
	 * @param context
	 * @param model
	 * @param entity
	 */
	public void listLineEx(ActionContext context, Map model, Object entity)
			throws Exception {
		SysUtil.copyProperties(model, entity);
	}

	@Override
	public void createLineListHql(BaseForm form, StringBuffer hql) {
		hql.append(" from ").append(getLineEntityName()).append(
				"  entity where entity.");
		hql.append(this.getTableKeyField());
		hql.append("=");
		hql.append(getEntityId(form));
	}

	@Override
	public void createLineListHqlWhere(StringBuffer hql, ActionContext context) {

	}

	@Override
	public void initFormNameLine(ActionContext context, Object entity) {
		// TODO Auto-generated method stub

	}

	/**
	 * 得到实例类名称
	 * 
	 * @return
	 */
	protected String getLineEntityName() {
		return ClassUtils.getShortName(getLineEntityClass());
	}

	@Override
	public IStrutsForward view(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		String pk = null;
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
				context.getRequest().setAttribute(
						BaseRightService.RIGHT_OBJECT, entity);
				// 显示明细
				StringBuffer hql = new StringBuffer();
				createLineListHql(form, hql);
				createLineListHqlWhere(hql, context);
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
						Object entityline = list.get(i);
						listLineEx(context, model, entityline);
						webList.add(model);
					}
				}
				context.getRequest().setAttribute("LISTS", webList);
			}
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(LIST_METHOD);
		}
		processSuccess(context, false);
		return forwardJsp(VIEW_PAGE);
	}

	// 绑定数据

	/**
	 * 得到明细表的实体类
	 * 
	 * @return
	 */
	public Class getLineEntityClass() throws BusinessException {
		throw new BusinessException("未定义明细类");
	}

	/**
	 * 得到明细表的主键名称
	 * 
	 * @return
	 */
	public String getLineTableKeyField() {
		throw new BusinessException("未定义明细的主键");
	}

	// 增加明细
	/**
	 * 从entity的数据传递给form
	 * 
	 * @param form
	 * @param object
	 * @return
	 */
	public void bindLineForm(BaseForm form, Object entity) {

	}

	/**
	 * 从form的数据传递给entity
	 * 
	 * @param form
	 * @param object
	 * @return
	 */
	public void bindLineEntity(BaseForm form, Object entity) {

	}

	@Override
	public IStrutsForward addLine(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		try {
			token.saveTokenLine(context.getRequest());
			initFormComboLine(context);
			addLineEx(context);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(VIEW_METHOD);
		}
		processSuccess(context, false);
		form.setMethod("addSaveLine");
		return forwardJsp(EDITLINE_PAGE);
	}

	@Override
	public void addLineEx(ActionContext context) {

	}

	@Override
	public void initFormComboLine(ActionContext context) {

	}

	@Override
	public IStrutsForward addSaveLine(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		String pk = null;
		try {
			if (token.isTokenValidLine(context.getRequest(), true)) {
				// 生成实例
				pk = save(context, pk);
			}
		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod(ADDLINE_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardMethod(VIEWLINE_METHOD);
	}

	// 保存
	protected String save(ActionContext context, String pk) throws Exception,
			IllegalAccessException, InvocationTargetException {
		Object entity = createlineEntity();
		bindLineEntity(context.getForm(), entity);
		// 判断主键是否重复
		Operators os = new Operators();
		os.addAddObject(entity);
		addSaveLineEx(context, entity, os);
		dao.execute(os);
		pk = getLineIdValue(entity);
		BeanUtils.setProperty(context.getForm(), this.getLineTableKeyField(),
				pk);

		return pk;
	}
	@Override
	public void addSaveLineEx(ActionContext context, Object entity, Operators os) {

	}

	@Override
	public IStrutsForward saveAdd(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		String pk = null;
		try {
			if (token.isTokenValidLine(context.getRequest(), true)) {
				// 生成实例
				pk = save(context, pk);
				saveAddEx(context);

			}
		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod(ADDLINE_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardMethod(ADDLINE_METHOD);
	}

	@Override
	public void saveAddEx(ActionContext context) {

	}

	@Override
	public IStrutsForward updateLine(ActionContext context) {
		try {
			token.saveTokenLine(context.getRequest());// 保存token
			Object entity = getLineEntity(context);
			bindLineForm(context.getForm(), entity);
			initFormComboLine(context);
			initFormNameLine(context, entity);
			updateLineEx(context, entity);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(VIEWLINE_METHOD);
		}
		processSuccess(context, false);
		context.getForm().setMethod("updateSaveLine");
		return forwardJsp(EDITLINE_PAGE);
	}

	public void updateLineEx(ActionContext context, Object entity) {

	}

	public void updateSaveLineEx(ActionContext context, Object entity,
			Operators os) {

	}

	@Override
	public IStrutsForward updateSaveLine(ActionContext context) {
		String pk = null;
		try {
			if (token.isTokenValidLine(context.getRequest(), true)) {
				// 修改保存
				Object entity = getLineEntity(context);
				if (entity == null) {
					BusinessException exception = new BusinessException(
							NOEXISTRECORD);
					throw exception;
				} else {
					pk = getLineIdValue(entity);
					Operators os = new Operators();
					os.addUpdateObject(entity);
					bindLineEntity(context.getForm(), entity);
					updateSaveLineEx(context, entity, os);
					dao.execute(os);
				}
			}
		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod(UPDATELINE_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardMethod(VIEWLINE_METHOD);
	}

	@Override
	public void deleteEx(ActionContext context, Object entity, Operators os) {
       StringBuffer sql=new StringBuffer();
       sql.append(" delete from ");
       sql.append(getLineEntityName());
       sql.append(" where ");
       sql.append(this.getTableKeyField());
       sql.append("=");
       sql.append(getEntityId(context.getForm()));
       os.addScriptObject(sql.toString());
	}
	@Override
	public IStrutsForward deleteLine(ActionContext context) {
		String pk = null;
		try {
			if (token.isTokenValidLine(context.getRequest(), true)) {
				// 得到实体
				Object entity = getLineEntity(context);
				if (entity == null) {
					BusinessException exception = new BusinessException(
							NOEXISTRECORD);
					throw exception;
				} else {
					pk = getLineIdValue(entity);
					if (isCanDeleteLine(context, entity, pk)) {
						Operators os = new Operators();
						os.addDeleteObject(entity);
						deleteLineEx(context, entity, os);
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
			return forwardMethod(VIEWLINE_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardJsp("delete");
	}

	public boolean isCanDeleteLine(ActionContext context, Object entity,
			String pkid) throws Exception {
		return true;
	}

	public void deleteLineEx(ActionContext context, Object entity, Operators os) {

	}

	public void viewLineEx(ActionContext context, Object entity) {

	}

	//
	@Override
	public IStrutsForward viewLine(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		String pk = null;
		try {
			token.saveTokenLine(context.getRequest());
			Object entity = getLineEntity(context);
			if (entity == null) {
				BusinessException exception = new BusinessException(
						NOEXISTRECORD);
				throw exception;
			} else {
				pk = getLineIdValue(entity);
				bindLineForm(context.getForm(), entity);
				viewLineEx(context, entity);
				initFormComboLine(context);
				initFormNameLine(context, entity);
				Object entitymaster = getEntity(context);
				context.getRequest().setAttribute(
						BaseRightService.RIGHT_OBJECT, entitymaster);
			}
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardJsp(VIEW_METHOD);
		}
		processSuccess(context, false);
		return forwardJsp(VIEWLINE_PAGE);
	}

	protected Serializable getLineEntityId(ActionContext context) {
		return getLineEntityId(context.getForm());
	}

	protected Serializable getLineEntityId(BaseForm form) {
		try {
			Serializable id = (Serializable) PropertyUtils.getSimpleProperty(
					form, this.getLineTableKeyField());
			return id;
		} catch (Exception e) {
			throw new IllegalArgumentException("取实体主键值发生错误......", e);
		}
	}

	/**
	 * 取主键值，用于写log
	 * 
	 * @param entity
	 * @return
	 */
	protected String getLineIdValue(Object entity) {
		String field = this.getLineTableKeyField();
		String value = "";
		if (entity != null) {
			try {
				value = (String) BeanUtils.getProperty(entity, field);
			} catch (Exception e) {
				SystemLogger.error("取主键值发生错误", e);
			}
		}
		return value;
	}

	/**
	 * 得到实例
	 * 
	 * @param context
	 * @return
	 */
	protected Object getLineEntity(ActionContext context) {
		Serializable id = getLineEntityId(context);
		return dao.get(getLineEntityClass(), id);
	}

	/**
	 * 得到实例
	 * 
	 * @param id
	 * @return
	 */
	protected Object getLineEntity(String id) {
		return dao.get(getLineEntityClass(), id);
	}

	public Object createlineEntity() throws Exception {
		Object object = null;
		object = this.getLineEntityClass().newInstance();
		return object;
	}
}
