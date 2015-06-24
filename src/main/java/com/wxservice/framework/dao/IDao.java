package com.wxservice.framework.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;

import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.dao.jdbc.JdbcManager;
import com.wxservice.framework.dao.support.Page;


public interface IDao {

	/**
	 * 执行一组操作，这组操作包括增加，修改，删除，执行sql脚本，执行存储过程，清除cache等， 执行的先后顺序是在列表中增加的先后顺序
	 */
	public abstract void execute(final IOperator os) throws DataAccessException;
	/**
	 * 增加保存一个对象
	 */
	public abstract Object addSave(final Object o) throws DataAccessException;
    /**
     * 修改保存一个对象
     * @param o
     * @throws DataAccessException
     */
	public abstract void updateSave(final Object o) throws DataAccessException;
	
	/**
	 * 删除一个对象
	 * @param o
	 * @throws DataAccessException
	 */

	public abstract void delete(final Object o) throws DataAccessException;
    /**
     * 执行一个sql脚步本
     * @param o
     * @throws DataAccessException
     */
	public abstract void script(final Object o) throws DataAccessException;
	
	public abstract void batchScript(final Object o) throws DataAccessException;

	/**
	 * 清除一个缓存对象
	 */
	public abstract void evict(final Class cls) throws DataAccessException;

	/**
	 * 得到一个hibernate po对象
	 */
	public abstract Object get(final String hsql) throws DataAccessException;
    
	
	/**
	 * 得到一个hibernate对象，如果不存在，会抛出异常
	 * @param entityClass
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public abstract Object load(Class entityClass, Object id)
			throws DataAccessException;
    /**
     * 得到一个hibernate对象，如果不存在，不会抛出异常
     * @param entityClass
     * @param id
     * @return
     * @throws DataAccessException
     */
	public abstract Object get(Class entityClass, Object id)
			throws DataAccessException;
    /**
     * 得到一个hibernate对象
     * @param entityClass
     * @param id
     * @return
     * @throws DataAccessException
     */
	public abstract Object get(Class entityClass, int id)
			throws DataAccessException;

	/**
	 * 得到一个list对象
	 * @param hSql
	 * @return
	 * @throws DataAccessException
	 */
	public abstract List list(String hSql) throws DataAccessException;
    /**
     * 得到一个list对象
     * @param hSql
     * @return
     * @throws DataAccessException
     */
	public abstract List iterate(final String hSql) throws DataAccessException;

	/**
	 * 得到第几页的list对象
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page list(final String hql, final int pageNo,
			final int pageSize);
    /**
     * 得到第几页的list对象，与list方法的区是，它会cache中取值，所以写程序是常用这个。
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     */
	public abstract Page iterate(final String hql, final int pageNo,
			final int pageSize);

	/**
	 * 判断当前列在数据库表中是否唯一.
	 * 
	 * @param entityClass
	 * @param entity
	 * @return
	 */
	public abstract boolean isUnique(Class entityClass, Object entity);

	/**
	 * 取得对象的主键值,辅助函数.
	 */
	public abstract Serializable getId(Class entityClass, Object entity)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException;

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	public abstract String getIdName(Class clazz);

	/**
	 * 取得DataSource
	 * 
	 * @return
	 */
	public abstract DataSource getDataSource();

	/**
	 * 取最大号
	 * 
	 * @param table
	 * @param field
	 * @return
	 * @throws DataAccessException
	 */
	public abstract int getMax(String table, String field)
			throws DataAccessException;
	
	public abstract JdbcManager getJdbcManager();
}
