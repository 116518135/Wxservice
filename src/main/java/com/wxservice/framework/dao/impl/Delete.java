package com.wxservice.framework.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Delete extends AbstractOperator {
	public Delete(Object obj) {
		super(obj);
	}

	
	/**
	 * 处理删除的pojo
	 */
	public void execute(Session session) throws HibernateException {
		if (pojo != null) {
			if (pojo instanceof String) {
				session.delete((String) pojo);
			} else {
				session.delete(pojo);
			}
		}
	}

}
