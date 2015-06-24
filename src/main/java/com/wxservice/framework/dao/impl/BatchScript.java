package com.wxservice.framework.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.support.JdbcUtils;

import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;

public class BatchScript extends AbstractOperator {

	public BatchScript(Object obj) {
		super(obj);
	}

	public void execute(Session session) throws HibernateException {
		String str = null;
		Statement stmt = null;
		try {
			if (pojo != null) {
				if (pojo instanceof String) {
					str = pojo.toString();
					SystemLogger.debug(str);
					String[] sql = str.split(";");// 批量语句是以分号隔开的
					stmt = session.connection().createStatement();
					int[] rowsAffected = new int[sql.length];
					for (int i = 0; i < sql.length; i++) {
						if (StringUtil.isNotBlank(sql[i])) {
							SystemLogger.info(sql[i]);
							stmt.addBatch(sql[i]);
						}
					}
					rowsAffected = stmt.executeBatch();
				} else if (pojo instanceof List) {
					String sql = null;
					stmt = session.connection().createStatement();
					List list = (List) pojo;
					int[] rowsAffected = new int[list.size()];
					for (int i = 0; i < list.size(); i++) {
						sql = (String) list.get(i);
						if (StringUtil.isNotBlank(sql)) {
							SystemLogger.info(sql);
							stmt.addBatch(sql);
						}
					}
					rowsAffected = stmt.executeBatch();
				}
			}
		} catch (SQLException e) {
			StringBuffer errStr = new StringBuffer();
			errStr.append("sql批量执行java脚本有错误,sql=");
			errStr.append(str);
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
