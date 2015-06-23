package com.wxservice.framework.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.wxservice.framework.util.SystemLogger;

public class Script extends AbstractOperator {

	public Script(Object obj) {
		super(obj);
	}

	public void execute(Session session) throws HibernateException {
		String sql = null;
		Statement stmt = null;
		try {
			stmt = session.connection().createStatement();
			if (pojo != null) {
				sql = pojo.toString();
				SystemLogger.debug(sql);
				stmt.execute(sql);
			}
		} catch (SQLException e) {
			StringBuffer errStr = new StringBuffer();
			errStr.append("sql脚本有错误,sql=");
			errStr.append(sql);
			SystemLogger.error(errStr.toString(), e);
			throw new HibernateException(errStr.toString(), e);
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
                e.printStackTrace(System.out);
			}
		}
	}
}
