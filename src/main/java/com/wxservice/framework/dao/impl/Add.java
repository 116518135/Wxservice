package com.wxservice.framework.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;


public class Add extends AbstractOperator {

	public Add(Object obj) {
		super(obj);
	}

	/**
	 * 处理增加的pojo
	 */
	public void execute(Session session) throws HibernateException {
		if (pojo != null) {
			session.save(pojo);
		}

	}

}
