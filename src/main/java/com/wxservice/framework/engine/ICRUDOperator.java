package com.wxservice.framework.engine;
import java.util.Map;

import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;

public interface ICRUDOperator {
	
	/**
	 * 得到表单的实体类
	 * @return
	 */
	public  Class getEntityClass();
	/**
	 * 得到表单的主键名称
	 * @return
	 */
	public  String getTableKeyField();
	
	/**
	 * 从entity的数据传递给form
	 * @param form
	 * @param object
	 * @return
	 */
	public  void bindForm(BaseForm form, Object entity);
	
	/**
	 * 从form的数据传递给entity
	 * @param form
	 * @param object
	 * @return
	 */
	public  void bindEntity(BaseForm form, Object entity);
	
	/**
	 * 进入list页面执行的方法
	 * @param context
	 * @return
	 */
	public IStrutsForward list(ActionContext context);
	/**
	 * 调list方法的时候，取hibernater 的HQL语句，绝大多数不需要在业务类中重写
	 * @param form
	 * @param hql
	 */
	public void createListHql(BaseForm form, StringBuffer hql);
	/**
	 * 取取Hibernate语句的时候，有时候需要根据业务类来过行重写
	 * @param hql
	 * @param context
	 */
	public void createListHqlWhere(StringBuffer hql, ActionContext context);
	/**
	 * 将取到实体 mapping到map的时候的扩展，默认有一个全部的
	 * @param context
	 * @param model
	 * @param entity
	 */
	public void listEx(ActionContext context, Map model, Object entity) throws Exception;
	/**
	 * 进入增加页面调用的方法
	 * @param context
	 * @return
	 */
	public IStrutsForward add(ActionContext context);
	/**
	 * 增加方法的扩展
	 * @param context
	 * @return
	 */
	public void addEx(ActionContext context);
	/**
	 * 增加保存
	 * @param context
	 * @return
	 */
	public IStrutsForward addSave(ActionContext context);
	/**
	 * 增加保存的扩展
	 * @param context
	 * @param entity
	 * @param os
	 * @return
	 */
	public void addSaveEx(ActionContext context, Object entity,
	Operators os) ;
	
	/**
	 * 修改
	 * @param context
	 * @return
	 */
	public IStrutsForward update(ActionContext context);
	/**
	 * 修改的扩展
	 * @param context
	 * @return
	 */
	public void updateEx(ActionContext context, Object entity);
	
	
	/**
	 * 修改保存
	 * @param context
	 * @return
	 */
	public IStrutsForward updateSave(ActionContext context);
	/**
	 * 修改保存的扩展
	 * @param context
	 * @return
	 */
	public void updateSaveEx(ActionContext context, Object entity,
			Operators os);
	
	/**
	 * 浏览
	 * @param context
	 * @return
	 */
	public IStrutsForward view(ActionContext context);
	/**
	 * 浏览的扩展
	 * @param context
	 * @return
	 */
	public void viewEx(ActionContext context, Object entity);	
	/**
	 * 删除
	 * @param context
	 * @return
	 */
	public IStrutsForward delete(ActionContext context);
	/**
	 * 删除的扩展
	 * @param context
	 * @param entity
	 * @param os
	 * @return
	 */
	public void deleteEx(ActionContext context, Object entity,
			Operators os);
	/**
	 * 删除前的检查函数
	 * @param context
	 * @param entity
	 * @param os
	 * @return
	 */
	public boolean isCanDelete(ActionContext context, Object entity,String pkid) throws Exception ;	
	/**
	 * 查找
	 * @param context
	 * @return
	 */
	public IStrutsForward find(ActionContext context);
	/**
	 * 初始化form中的下拉框
	 * @param context
	 */
	public void initFormCombo(ActionContext context);
	/**
	 * 调view方法的时候，将id取名称类
	 * @param context
	 */
	public void initFormName(ActionContext context,Object entity);
	
	
}
