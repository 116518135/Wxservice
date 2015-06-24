package com.wxservice.framework.dao.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.wxservice.framework.dao.IOperator;


public class Procedure extends StoredProcedure implements IOperator {

	Map<Object, Object> map = new HashMap<Object, Object>();// 保存存储过程的参数值

	/**
	 * 构造函数，生成实例
	 */
	public Procedure() {
		super();

	}

	/**
	 * 设置存储过程传入参数的值
	 * 
	 * @param key
	 *            String
	 * @param obj
	 *            Object
	 */
	public void setValue(String key, Object obj) {
		map.put(key, obj);
	}

	/**
	 * 构造函数，同时设置数据源
	 * 
	 * @param ds
	 *            DataSource
	 */
	public Procedure(DataSource ds) {
		setDataSource(ds);
	}

	/**
	 * 执行存储过程
	 */
	public void execute(Session session) {
		if (StringUtils.isBlank(this.getSql())) {
			return;
		}
		session.flush();
		this.compile();
		this.execute(map);
	}

	public void execute() {
		if (StringUtils.isBlank(this.getSql())) {
			return;
		}
		this.compile();
		this.execute(map);
	}

	/**
	 * 设置存储过程字符型参数
	 * 
	 * @param param
	 *            String
	 */
	public void setVarcharParam(String param) {
		this.declareParameter(new SqlParameter(param, Types.VARCHAR));
	}

	/**
	 * 设置存储过程double型参数
	 * 
	 * @param param
	 *            String
	 */
	public void setDoubleParam(String param) {
		this.declareParameter(new SqlParameter(param, Types.DOUBLE));
	}

	/**
	 * 设置存储过程整型参数
	 * 
	 * @param param
	 *            String
	 */
	public void setIntegerParam(String param) {
		this.declareParameter(new SqlParameter(param, Types.INTEGER));
	}

	/**
	 * 设置字符型输出参数
	 * 
	 * @param param
	 *            String
	 */
	public void setVarcharOutParam(String param) {
		this.declareParameter(new SqlOutParameter(param, Types.VARCHAR));
	}

	/**
	 * 设置double输出参数
	 * 
	 * @param param
	 *            String
	 */
	public void setDoubleOutParam(String param) {
		this.declareParameter(new SqlOutParameter(param, Types.DOUBLE));
	}

	/**
	 * 设置整型输出参数
	 * 
	 * @param param
	 *            String
	 */
	public void setIntegerOutParam(String param) {
		this.declareParameter(new SqlOutParameter(param, Types.INTEGER));
	}

	public void execute(Session session, Object obj) throws HibernateException {
	};

	public void execute(Session session, List<Object> list)
			throws HibernateException {
	};

	public List getObjectList() {
		return null;
	};

	public void setObjectList(List<Object> objectList) {
	};

	public void addObject(Object obj) {
	}

	@Override
	public Object getPojo() {
		// TODO Auto-generated method stub
		return null;
	};
}