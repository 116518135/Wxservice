package com.wxservice.framework.dao.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.wxservice.framework.dao.support.Page;


public class JdbcManager extends JdbcDaoSupport {
	public static String rows = "rows";
	public static String count = "count";

    /**
     * 分页，返回一个list
     * @param sql
     * @param pageNumber
     * @param limit
     * @return
     * @throws DataAccessException
     */
	public List queryForList(String sql, int pageNumber, int limit)
			throws DataAccessException {
		Map map = (Map) getJdbcTemplate().query(sql,
				new LimitResultSetExtractor(pageNumber, limit));
		List list = (List) map.get(rows);
		return list;
	}
    /**
     * 使用存储过程进行查询
     * @param sql
     * @return
     * @throws DataAccessException
     */
	public List queryForProcdure(String sql)
			throws DataAccessException {
		//QueryProcedure qp=null;
		//List list=(List)this.getJdbcTemplate().execute(qp);
		return new ArrayList();
	}
    /**
     * 分页查询，返回一个Page对象
     * @param sql
     * @param pageNumber
     * @param limit
     * @return
     * @throws DataAccessException
     */
	public Page queryForPage(String sql, int pageNumber, int limit)
			throws DataAccessException {
		Map map = (Map) getJdbcTemplate().query(sql,
				new LimitResultSetExtractor(pageNumber, limit));
		List rows = (List) map.get(this.rows);
		Integer count = (Integer) map.get(this.count);
		int startIndex = (pageNumber - 1) * limit;
		Page page = new Page(startIndex, count, limit, rows);
		return page;
	}

    /**
     * 执行一条需要查询的SQL语句,得到一个查询列表
     * @param sql
     * @return
     * @throws DataAccessException
     */
	public List queryForList(String sql) throws DataAccessException {
		List list = (List) getJdbcTemplate().query(sql,
				new ListResultSetExtractor());
		return list;
	}

	
	/**
	 * 执行一条需要查询的SQL语句,得到一个long值
	 * 
	 * @param sql
	 * @return
	 * @throws DataAccessException
	 */
	public long queryForLong(String sql) throws DataAccessException {
		return getJdbcTemplate().queryForLong(sql);
	}

	/**
	 * 执行一条需要查询的SQL语句,得到一个int值
	 * 
	 * @param sql
	 * @return
	 * @throws DataAccessException
	 */
	public int queryForInt(String sql) throws DataAccessException {
		return getJdbcTemplate().queryForInt(sql);
	}

	/**
	 * 执行一条需要查询的SQL语句,得到一个String值
	 * 
	 * @param sql
	 * @return
	 * @throws DataAccessException
	 */
	public String queryForString(String sql) throws DataAccessException {
		return String.valueOf(getJdbcTemplate().queryForObject(sql,
				Object.class));
	}

	/**
	 * 执行一条需要查询的SQL语句，得到一个Double值
	 * 
	 * @param sql
	 * @return
	 * @throws DataAccessException
	 */
	public double queryForDouble(String sql) throws DataAccessException {
		Number number = (Number) getJdbcTemplate().queryForObject(
				sql, Number.class);
		return (number != null ? number.doubleValue() : 0);
	}
	
	//更新
	public int update(final String sql) throws DataAccessException {
		return this.getJdbcTemplate().update(sql);
	}
	//更新
	public int[] batchUpdate(final String[] sql) throws DataAccessException {
		return this.getJdbcTemplate().batchUpdate(sql);
	}
	//更新
	public int[] batchUpdate(final List<String> list) throws DataAccessException {
		String[] sql=new String[list.size()];
		for(int i=0;i<list.size();i++){
			sql[i]=list.get(i);
		}
		return this.getJdbcTemplate().batchUpdate(sql);
	}

	/**
	 * 使用queryForList方法的时候，用到的一个回调类
	 * 
	 * @author mzhanker
	 * 
	 */
	protected static final class ListResultSetExtractor implements
			ResultSetExtractor {
		public Object extractData(ResultSet rs) throws SQLException {
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			List listOfRows = new ArrayList();
			while (rs.next()) {
				Map mapOfColValues = new LinkedHashMap(numberOfColumns);
				for (int i = 1; i <= numberOfColumns; i++) {
					mapOfColValues.put(rsmd.getColumnName(i).toLowerCase(), rs
							.getObject(i));
				}
				listOfRows.add(mapOfColValues);
			}
			return listOfRows;
		}
	}
    /**
     * 分页查询时需要用到的类
     * 描述：
     * 创建人：LJS
     */
	protected static final class LimitResultSetExtractor implements
			ResultSetExtractor {
		int startRecord = 1;

		int limit = 10;

		public LimitResultSetExtractor(int pageNumber, int limit) {
			this.startRecord = (pageNumber - 1) * limit + 1;
			this.limit = limit;
		}

		public Object extractData(ResultSet rs) throws SQLException {
			Map ret = new HashMap();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			List listOfRows = new ArrayList();
			int loop = 0;

			while (rs.next()) {
				loop++;
				if (loop >= startRecord && loop < startRecord + limit) { // 只取翻页显示所需要的数据
					Map mapOfColValues = new LinkedHashMap(numberOfColumns);
					for (int i = 1; i <= numberOfColumns; i++) {
						mapOfColValues.put(rsmd.getColumnName(i).toLowerCase(),
								rs.getObject(i));
					}
					listOfRows.add(mapOfColValues);
				}
			}
			ret.put(rows, listOfRows);
			ret.put(count, loop);
			return ret;
		}

	}

}
