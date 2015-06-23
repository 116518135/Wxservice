package com.wxservice.framework.engine.impl;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.ClassUtils;

import com.wxservice.framework.engine.BaseCRUDEngine;
import com.wxservice.framework.engine.ICRUDOperator;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.form.BaseForm;

public abstract class AbstractCRUDOperator extends BaseCRUDEngine implements ICRUDOperator{
	public final static String LIST_PAGE = "list";

	public final static String EDIT_PAGE = "edit";

	public final static String VIEW_PAGE = "view";

	public final static String FIND_PAGE = "find";

	public final static String LEFT_PAGE = "left";

	public final static String RIGHT_PAGE = "right";

	public final static String MAIN_PAGE = "main";

	public final static String TREE_PAGE = "tree";
	public final static String VIEWLINE_PAGE = "viewline";
	public final static String EDITLINE_PAGE = "editline";


	public final static String LIST_METHOD = "list";
	public final static String ADD_METHOD = "add";
	public final static String ADDSAVE_METHOD = "addSave";
	public final static String VIEW_METHOD = "view";
	public final static String UPDATE_METHOD = "update";
	public final static String UPDATESAVE_METHOD = "updateSave";
	public final static String DELETE_METHOD = "delete";
	public final static String FIND_METHOD = "find";
	public final static String LEFT_METHOD = "left";
	public final static String RIGHT_method = "right";
	public final static String MAIN_METHOD = "main";
	public final static String TREE_METHOD = "tree";
	public final static String ADDLINE_METHOD = "addLine";
	public final static String ADDLINESAVE_METHOD = "addLineSave";
	public final static String VIEWLINE_METHOD = "viewLine";
	public final static String UPDATELINE_METHOD = "updateLine";
	public final static String UPDATELINESAVE_METHOD = "updateLineSave";
	public final static String DELETELINE_METHOD = "deleteLine";
	/**
	 * 注册常用的方法
	 */
	static{
		registMethodChinese(LIST_METHOD,"列表");
		registMethodChinese(ADD_METHOD,"增加");
		registMethodChinese(ADDSAVE_METHOD,"增加保存");
		registMethodChinese(VIEW_METHOD,"浏览");
		registMethodChinese(UPDATE_METHOD,"修改");
		registMethodChinese(UPDATESAVE_METHOD,"修改保存");
		registMethodChinese(DELETE_METHOD,"删除");
		registMethodChinese(FIND_METHOD,"查找");
		
		registMethodChinese(ADDLINE_METHOD,"增加明细");
		registMethodChinese(ADDLINESAVE_METHOD,"增加保存明细");
		registMethodChinese(VIEWLINE_METHOD,"浏览明细");
		registMethodChinese(UPDATELINE_METHOD,"修改明细");
		registMethodChinese(UPDATELINESAVE_METHOD,"修改保存明细");
		registMethodChinese(DELETELINE_METHOD,"删除明细");
		
		registMethodChinese(TREE_METHOD,"获取树型数据");
		registMethodChinese(RIGHT_method,"查询");
	}
	/*
	 * 取得主键参数
	 * 
	 * @param context
	 * @return
	 */
	protected Serializable getEntityId(ActionContext context) {
		try {
			Serializable id=(Serializable)PropertyUtils.getSimpleProperty(context.getForm(), this.getTableKeyField());
			return id;
		} catch (Exception e) {
			throw new IllegalArgumentException("取实体主键值发生错误......",e);
		}
	}
	
	protected Serializable getEntityId(BaseForm form) {
		try {
			Serializable id=(Serializable)PropertyUtils.getSimpleProperty(form, this.getTableKeyField());
			return id;
		} catch (Exception e) {
			throw new IllegalArgumentException("取实体主键值发生错误......",e);
		}
	}
	/**
	 * 取主键值，用于写log
	 * @param entity
	 * @return
	 */
	protected String getIdValue(Object entity) {
		String field = this.getTableKeyField();
		String value = "";
		if (entity != null) {
			try {
				value = (String)BeanUtils.getProperty(entity, field);
			} catch (Exception e) {
				SystemLogger.error("取主键值发生错误",e);
			}
		}
		return value;
	}
	/**
	 * 得到实例
	 * @param context
	 * @return
	 */
	protected Object getEntity(ActionContext context) {
		Serializable id = getEntityId(context);
		return dao.get(getEntityClass(), id);
	}

    /**
     * 得到实例
     * @param id
     * @return
     */
	protected Object getEntity(String id) {
		return dao.get(getEntityClass(), id);
	}

	/**
	 * 得到实例类名称
	 * 
	 * @return
	 */
	public String getEntityName() {
		return ClassUtils.getShortName(getEntityClass());
	}


}
