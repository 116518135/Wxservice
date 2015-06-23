package com.wxservice.framework.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;



import com.wxservice.framework.dao.IOperator;



public class Operators extends AbstractOperator {
	// 操作列表
	List<IOperator> objectList = new ArrayList<IOperator>();
	// 存储过程的操作
	Procedure store = new Procedure();

	/**
	 * 增加需要"增加的PO"
	 * 
	 * @param obj
	 */
	public void addAddObject(Object obj) {
		IOperator o = new Add(obj);
		objectList.add(o);
	}

	/**
	 * 增加需要"修改的PO"
	 * 
	 * @param obj
	 */
	public void addUpdateObject(Object obj) {
		IOperator o = new Update(obj);
		objectList.add(o);
	}

	/**
	 * 增加需要"删除的PO"
	 * 
	 * @param obj
	 */
	public void addDeleteObject(Object obj) {
		IOperator o = new Delete(obj);
		objectList.add(o);
	}
	/**
	 * 增加需要"清除cache的PO"
	 * 
	 * @param obj
	 */
	public void addEvictObject(Object obj) {
		IOperator o = new Evict(obj);
		objectList.add(o);
	}

	/**
	 * 增加需要"执行的sql语句"
	 * 
	 * @param obj
	 */
	public void addScriptObject(Object obj) {
		IOperator o = new Script(obj);
		objectList.add(o);
	}
	
	/**
	 * 增加批量"执行的sql语句"
	 * 
	 * @param obj
	 */
	public void addBatchScriptObject(Object obj) {
		IOperator o = new BatchScript(obj);
		objectList.add(o);
	}

	/**
	 * 提交数据库操作
	 */
	public void addFlushObject() {
		IOperator o = new Flush();
		objectList.add(o);
	}

	/**
	 * 执行所有的操作
	 * 
	 * @param session
	 * @throws HibernateException
	 */
	public void execute(Session session) throws HibernateException {
		for (int loop = 0; loop < objectList.size(); loop++) {
			IOperator oper = objectList.get(loop);
			oper.execute(session);
		}
		store.execute(session);// 执行存储过程

	}

	public Procedure getStore() {
		return store;
	}

	public List<IOperator> getObjectList() {
		return objectList;
	}

}