package com.wxservice.framework.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Update extends AbstractOperator {
	public Update(Object obj) {
		super(obj);
	}

	public Update(List<Object> list) {
		super(list);
	}

	/**
	 * 执行更新的动作
	 */

	public void execute(Session session) throws HibernateException {
		if(pojo!=null){
			session.update(pojo);
		}
	}
}
