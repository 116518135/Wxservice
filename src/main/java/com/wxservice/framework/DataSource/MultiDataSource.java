package com.wxservice.framework.DataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.start.SysSecurityContextHolder;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.WebUtil;

/**
 * @author FanGang
 * 
 */
public class MultiDataSource implements DataSource, ApplicationContextAware {

	private static final Log log = LogFactory.getLog(MultiDataSource.class);
	private ApplicationContext applicationContext = null;
	private DataSource dataSource = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection(java.lang.String,
	 *      java.lang.String)
	 */
	public Connection getConnection(String arg0, String arg1)
			throws SQLException {
		return getDataSource().getConnection(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getLogWriter()
	 */
	public PrintWriter getLogWriter() throws SQLException {
		return getDataSource().getLogWriter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getLoginTimeout()
	 */
	public int getLoginTimeout() throws SQLException {
		return getDataSource().getLoginTimeout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#setLogWriter(java.io.PrintWriter)
	 */
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		getDataSource().setLogWriter(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#setLoginTimeout(int)
	 */
	public void setLoginTimeout(int arg0) throws SQLException {
		getDataSource().setLoginTimeout(arg0);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public DataSource getDataSource(String dataSourceName) {
		log.debug("dataSourceName:" + dataSourceName);
		try {
			if (dataSourceName == null || dataSourceName.equals("")) {
				return this.dataSource;
			}
			return (DataSource) this.applicationContext.getBean(dataSourceName);
		} catch (NoSuchBeanDefinitionException ex) {
			throw new BusinessException("There is not the dataSource <name:"
					+ dataSourceName + "> in the applicationContext!");
		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		String dstype = WebUtil.getDstype();
		if (StringUtil.isBlank(dstype)) {
			// 这个只在登录的时候要用到。
			HttpServletRequest request = SysSecurityContextHolder.getContext()
					.getRequest();
			if (request != null) {
				dstype = request.getParameter("dstype");
			}
		}
		if (StringUtil.isBlank(dstype)) {
			throw new BusinessException("无法取到多数据源");
		}
		DataSource ds = (DataSource) WebUtil.getBean(dstype);
		return ds;
	}
	  public  Object unwrap(Class class1)
      throws SQLException{
		    return null;
	  }

  public  boolean isWrapperFor(Class class1)
      throws SQLException{
	  return false;
  }
}
