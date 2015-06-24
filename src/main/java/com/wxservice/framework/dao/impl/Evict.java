package com.wxservice.framework.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;


public class Evict extends AbstractOperator {
	public Evict(Object obj) {
		super(obj);
	}
	/**
	 * 清除缓存的pojo
	 */
	public void execute(Session session) throws HibernateException {
		if (pojo != null) {
			session.getSessionFactory().evict((Class) pojo);
		}
    }

}
