package com.wxservice.framework.engine;
import java.util.Map;

import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;

public interface ICRUDLineOperator{
	/**
	 * 得到明细表的实体类
	 * @return
	 */
	public  Class getLineEntityClass();
	/**
	 * 得到明细表的主键名称
	 * @return
	 */
	public  String getLineTableKeyField();
	
	/**
	 * 从entity的数据传递给form
	 * @param form
	 * @param object
	 * @return
	 */
	public  void bindLineForm(BaseForm form, Object entity);
	
	/**
	 * 从form的数据传递给entity
	 * @param form
	 * @param object
	 * @return
	 */
	public  void bindLineEntity(BaseForm form, Object entity);
	
	
	/**
	 * View页面取明细hibernater 的HQL语句，绝大多数不需要在业务类中重写
	 * @param form
	 * @param hql
	 */
	public void createLineListHql(BaseForm form, StringBuffer hql);
	/**
	 * 取取Hibernate语句的时候，有时候需要根据业务类来过行重写
	 * @param hql
	 * @param context
	 */
	public void createLineListHqlWhere(StringBuffer hql, ActionContext context);
	/**
	 * 将取到实体 mapping到map的时候的扩展，默认有一个全部的
	 * @param context
	 * @param model
	 * @param entity
	 */
	public void listLineEx(ActionContext context, Map model, Object entity)throws Exception;
	/**
	 * 进入增加页面调用的方法
	 * @param context
	 * @return
	 */
	public IStrutsForward addLine(ActionContext context);
	/**
	 * 增加方法的扩展
	 * @param context
	 * @return
	 */
	public void addLineEx(ActionContext context);
	/**
	 * 增加保存
	 * @param context
	 * @return
	 */
	public IStrutsForward addSaveLine(ActionContext context);
	/**
	 * 增加保存的扩展
	 * @param context
	 * @param entity
	 * @param os
	 * @return
	 */
	public void addSaveLineEx(ActionContext context, Object entity,
	Operators os) ;
	
	/**
	 * 修改
	 * @param context
	 * @return
	 */
	public IStrutsForward updateLine(ActionContext context);
	/**
	 * 修改的扩展
	 * @param context
	 * @return
	 */
	public void updateLineEx(ActionContext context, Object entity);
	/**
	 * 修改保存
	 * @param context
	 * @return
	 */
	public IStrutsForward updateSaveLine(ActionContext context);
	/**
	 * 修改保存的扩展
	 * @param context
	 * @return
	 */
	public void updateSaveLineEx(ActionContext context, Object entity,
			Operators os);
	
	/**
	 * 继续增加
	 * @param context
	 * @return
	 */
	public IStrutsForward saveAdd(ActionContext context);
	/**
	 * 继续增加的扩展
	 * @param context
	 * @return
	 */
	public void saveAddEx(ActionContext context);
	
	/**
	 * 浏览
	 * @param context
	 * @return
	 */
	public IStrutsForward viewLine(ActionContext context);
	/**
	 * 浏览的扩展
	 * @param context
	 * @return
	 */
	public void viewLineEx(ActionContext context, Object entity);	
	/**
	 * 删除
	 * @param context
	 * @return
	 */
	public IStrutsForward deleteLine(ActionContext context);
	/**
	 * 删除的扩展
	 * @param context
	 * @param entity
	 * @param os
	 * @return
	 */
	public void deleteLineEx(ActionContext context, Object entity,
			Operators os);
	/**
	 * 删除前的检查函数
	 * @param context
	 * @param entity
	 * @param os
	 * @return
	 */
	public boolean isCanDeleteLine(ActionContext context, Object entity,String pkid) throws Exception ;	

	/**
	 * 初始化form中的下拉框
	 * @param context
	 */
	public void initFormComboLine(ActionContext context);
	/**
	 * 调viewline方法的时候，将id取名称类
	 * @param context
	 */
	public void initFormNameLine(ActionContext context,Object entity);
	
	
}
