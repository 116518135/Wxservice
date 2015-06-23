package com.wxservice.framework.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wxservice.framework.dao.IDao;
public class SqlUtil {
	public static String getAutoColumn(String table, IDao dao) {
		StringBuffer hql = new StringBuffer();
		hql
				.append(" select b.name,b.autoval from sysobjects a,syscolumns b where a.id=b.id and a.name='");
		hql.append(table);
		hql.append("'");
		List list = dao.getJdbcManager().queryForList(hql.toString());
		for (Object o : list) {
			Map map = (Map) o;
			String name = (String) map.get("name");
			Object auto = map.get("autoval");
			if (auto != null) {
				return name;
			}
		}
		return "";
	}
	
	public static String createScript(String table, Map datas,
			String autocolumn) {
		Map params = new HashMap();
		params.put("'", "''");
		StringBuffer sql = new StringBuffer();
		StringBuffer field = new StringBuffer();
		StringBuffer value = new StringBuffer();
		Iterator it = datas.entrySet().iterator();
		boolean firstflag = true;
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			String key = (String) pairs.getKey();
			if (key.equalsIgnoreCase(autocolumn)) {// 是自增长的列，自动去掉
				continue;
			}
			Object o = pairs.getValue();

			if (!firstflag) {
				field.append(",");
				value.append(",");
			}
			firstflag = false;
			field.append(key);
			if (o == null) {
				value.append("null");
			} else {
				if (o instanceof Number) {
					value.append(o.toString());
				} else if (o instanceof Date) {
					String v = DateUtil.formatDetailDate((Date) o);
					value.append("'");
					value.append(v);
					value.append("'");
				} else {
					String v = o.toString();
					if ("true".equals(v)) {
						value.append(1);
					} else if ("false".equals(v)) {
						value.append(0);
					} else {
						String newvalue = SysUtil.doVarible(o.toString(),
								params);
						value.append("'");
						value.append(newvalue);
						value.append("'");
					}

				}
			}
		}
		sql.append(" insert into ");
		sql.append(table);
		sql.append("( ");
		sql.append(field);
		sql.append(")values(");
		sql.append(value);
		sql.append(")");
		return sql.toString();
	}
}
