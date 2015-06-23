package com.wxservice.framework.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public interface IOperator {
	/**
	 *处理
	 * @param session
	 * @throws HibernateException
	 */
	public void execute(Session session)  throws HibernateException;
    
	/**
	 * 获取一个原子操作对象
	 * @return
	 */
	public Object getPojo();
	

}
