package com.wxservice.framework.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;


public class Flush extends AbstractOperator {

	public Flush() {

	}

	public Flush(Object obj) {
		super(obj);
	}
	/**
	 * 提交数据库操作...
	 */
	public void execute(Session session) throws HibernateException {
		session.flush();
	}

}
