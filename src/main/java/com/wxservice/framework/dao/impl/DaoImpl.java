package com.wxservice.framework.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.dao.IOperator;
import com.wxservice.framework.dao.jdbc.JdbcManager;
import com.wxservice.framework.dao.support.DBUtils;
import com.wxservice.framework.dao.support.Page;
import com.wxservice.framework.util.SystemLogger;



public class DaoImpl extends HibernateDaoSupport implements IDao {

	DataSource dataSource = null;
	JdbcManager jdbcManager = null;

	/**
	 * 执行一个Operator操作
	 * 
	 * @param os
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#execute(com.wxservice.framework.dao.IOperator)
	 */
	public void execute(final IOperator os) throws DataAccessException {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				os.execute(session);
				return null;
			}
		});
	}

	/**
	 * 增加保存一个pojo
	 * 
	 * @param o
	 * @return
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#addSave(java.lang.Object)
	 */
	public Object addSave(final Object o) throws DataAccessException {
		return this.getHibernateTemplate().save(o);

	}

	/**
	 * 修改保存一个pojo
	 * 
	 * @param o
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#updateSave(java.lang.Object)
	 */
	public void updateSave(final Object o) throws DataAccessException {
		this.getHibernateTemplate().update(o);
	}

	/**
	 * 删除一个pojo
	 * 
	 * @param o
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#delete(java.lang.Object)
	 */
	public void delete(final Object o) throws DataAccessException {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Delete del = new Delete(o);
				del.execute(session);
				return null;
			}
		});
	}

	/**
	 * 执行一条sql语句
	 * 
	 * @param o
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#script(java.lang.Object)
	 */
	public void script(final Object o) throws DataAccessException {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Script script = new Script(o);
				script.execute(session);
				return null;
			}
		});
	}

	/**
	 * 执行一批sql语句
	 * 
	 * @param o
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#batchScript(java.lang.Object)
	 */
	public void batchScript(final Object o) throws DataAccessException {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				BatchScript script = new BatchScript(o);
				script.execute(session);
				return null;
			}
		});
	}

	/**
	 * 去除在缓存中的pojo
	 * 
	 * @param cls
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#evict(java.lang.Class)
	 */
	public void evict(final Class cls) throws DataAccessException {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				session.getSessionFactory().evict(cls);
				return null;
			}
		});
	}

	/**
	 * 通过一条hql语句，取得一个pojo
	 * 
	 * @param hsql
	 * @return
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#get(java.lang.String)
	 */
	public Object get(final String hsql) throws DataAccessException {
		Object obj = null;
		obj = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Object ret = null;
				Query query = session.createQuery(hsql);
				query.setFetchSize(1);
				Iterator lt = query.iterate();
				if (lt.hasNext()) {
					ret = lt.next();
				}

				return ret;
			}
		});
		return obj;
	}

	/**
	 * 通过id,取到一个pojo,取不到抛出异常
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#load(java.lang.Class,
	 *      java.lang.Object)
	 */
	public Object load(final Class entityClass, final Object id)
			throws DataAccessException {
		if(id==null) return null;
		Object obj = null;
		obj = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Object o = session.load(entityClass, (Serializable) id);
				return o;
			}
		});
		return obj;
	}

	/**
	 * 通过id,取到一个pojo,取不到不抛出异常
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#get(java.lang.Class,
	 *      java.lang.Object)
	 */
	public Object get(Class entityClass, Object id) throws DataAccessException {
		if(id==null) return null;
		return this.getHibernateTemplate().get(entityClass, (Serializable) id);
	}

	/**
	 * 通过id,取到一个pojo,取不到不抛出异常
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#get(java.lang.Class, int)
	 */
	public Object get(Class entityClass, int id) throws DataAccessException {
		return this.get(entityClass, String.valueOf(id));
	}

	/**
	 * 通过一条hql语句，得到一个list列表,直接从数据库中取
	 * 
	 * @param hSql
	 * @return
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#list(java.lang.String)
	 */
	public List list(String hSql) throws DataAccessException {
		return this.getHibernateTemplate().find(hSql);
	}

	/**
	 * 通过一条sql语句，得到一个list列表,它首先从cache中取，取不到从数据库中取
	 * 
	 * @param hSql
	 * @return
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#iterate(java.lang.String)
	 */
	public List iterate(final String hSql) throws DataAccessException {

		List list = null;
		list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				ArrayList<Object> ret = new ArrayList<Object>();
				Query query = session.createQuery(hSql);
				Iterator lt = query.iterate();
				while (lt.hasNext()) {
					Object o = lt.next();
					ret.add(o);
				}
				return ret;
			}
		});

		return list;
	}

	/**
	 * 通过hql语句，得到一个Page对象
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @see com.wxservice.framework.dao.IDao#list(java.lang.String, int, int)
	 */
	public Page list(final String hql, final int pageNo, final int pageSize) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		StringBuffer countQueryString = new StringBuffer();
		countQueryString.append(" select count (*) ").append(
				DBUtils.removeSelect(DBUtils.removeOrders(hql)));

		List countlist = getHibernateTemplate().find(
				countQueryString.toString());
		long totalCount = (Long) countlist.get(0);
		if (totalCount < 1)
			return new Page();
		// 实际查询返回分页对象
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);

		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<Object> ret = null;
						Query query = session.createQuery(hql);
						query.setFirstResult(startIndex);
						query.setMaxResults(pageSize);
						ret = query.list();
						return ret;
					}
				});
		return new Page(startIndex, totalCount, pageSize, list);
	}

	/**
	 * 通过hql语句，得到一个Page对象.它首先从cache中取，取不到从数据库中取
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @see com.wxservice.framework.dao.IDao#iterate(java.lang.String, int, int)
	 */
	public Page iterate(final String hql, final int pageNo, final int pageSize) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		StringBuffer countQueryString = new StringBuffer();
		long totalCount = 0;
		countQueryString.append(" select count (*) ").append(
				DBUtils.removeSelect(DBUtils.removeOrders(hql)));
		List countlist = getHibernateTemplate().find(
				countQueryString.toString());
		if (countlist != null && countlist.size() > 0) {
			totalCount = (Long) countlist.get(0);
		}
		if (totalCount < 1)
			return new Page();
		// 实际查询返回分页对象
		// int startIndex_temp = Page.getStartOfPage(pageNo, pageSize);
		// if(startIndex_temp+pageSize>totalCount) {
		// startIndex_temp=1;
		// }
		final int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						ArrayList<Object> ret = new ArrayList<Object>();
						Query query = session.createQuery(hql);
						query.setFirstResult(startIndex);
						query.setMaxResults(pageSize);
						Iterator lt = query.iterate();
						while (lt.hasNext()) {
							Object o = lt.next();

							ret.add(o);
						}
						return ret;
					}
				});
		return new Page(startIndex, totalCount, pageSize, list);
	}

	/**
	 * 判断当前的对象是不是在数据库中唯一.
	 * 
	 * @param entityClass
	 * @param entity
	 * @return
	 * @see com.wxservice.framework.dao.IDao#isUnique(java.lang.Class,
	 *      java.lang.Object)
	 */
	public boolean isUnique(Class entityClass, Object entity) {
		boolean result = true;
		try {
			Serializable id = getId(entityClass, entity);
			if (id != null) {
				Object o = this.get(entityClass, id);
				if (o != null) {
					result = false;
				}
			}
		} catch (Exception e) {
			SystemLogger.error("检查唯一性发生错误!", e);
		}
		return result;
	}

	/**
	 * 通过class，pojo,得到它的ID
	 * 
	 * @param entityClass
	 * @param entity
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @see com.wxservice.framework.dao.IDao#getId(java.lang.Class,
	 *      java.lang.Object)
	 */
	public Serializable getId(Class entityClass, Object entity)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(entityClass);
		return (Serializable) PropertyUtils.getProperty(entity,
				getIdName(entityClass));
	}

	/**
	 * 取得实体主键名字
	 * 
	 * @param clazz
	 * @return
	 * @see com.wxservice.framework.dao.IDao#getIdName(java.lang.Class)
	 */
	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz
				+ " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName()
				+ " has no identifier property define.");
		return idName;
	}

	/**
	 * 得到DataSource
	 * 
	 * @return
	 * @see com.wxservice.framework.dao.IDao#getDataSource()
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 设置Datasource
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 得到一个表里面的field的最大值，field必须为int或double对象
	 * 
	 * @param table
	 * @param field
	 * @return
	 * @throws DataAccessException
	 * @see com.wxservice.framework.dao.IDao#getMax(java.lang.String,
	 *      java.lang.String)
	 */
	public int getMax(String table, String field) throws DataAccessException {
		int max = 1;
		StringBuffer buf = new StringBuffer();
		buf.append("select max(a." + field + ") from ").append(table).append(
				" as a ");
		Integer o = (Integer) this.get(buf.toString());
		if (o != null) {
			max = o.intValue() + 1;
		}
		return max;
	}

	/**
	 * 得到JdbcManager
	 * 
	 * @return
	 * @see com.wxservice.framework.dao.IDao#getJdbcManager()
	 */
	public JdbcManager getJdbcManager() {
		return jdbcManager;
	}

	/**
	 * 设置JdbcManager
	 * 
	 * @param jdbcManager
	 */
	public void setJdbcManager(JdbcManager jdbcManager) {
		this.jdbcManager = jdbcManager;
	}

}
