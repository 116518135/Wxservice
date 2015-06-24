package com.wxservice.framework.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.ConnectionCallback;

import com.wxservice.framework.util.StringUtil;

public abstract class QueryProcedure implements ConnectionCallback {
	public String call;// 格式: 存储过程名|参数1,参数2,参数3,参数4,

	public QueryProcedure(String call) {
		this.call = call;
	}
    /**
     * 查询预备，初始化
     * @param con
     * @return
     * @throws SQLException
     */
	public CallableStatement prepareCall(Connection con) throws SQLException {
		if (StringUtils.isNotBlank(call)) {
			// 生成执行存储过程类
			StringBuffer sqlcall = new StringBuffer();
			sqlcall.append("{ call ");
			List list = StringUtil.string2List(call, "|");
			String callname =(String)list.get(0);
			sqlcall.append(callname);
			String[] params = null;
			if (list.size()== 2) {
				params = ((String) list.get(1)).split(",");
				if (params.length > 0) {
					sqlcall.append("(");
					boolean isD = false;
					for (String o : params) {
						if (isD) {
							sqlcall.append(",");
						}
						isD = true;
						sqlcall.append("?");

					}
					sqlcall.append(")");

				}
			}
			
			sqlcall.append("}");
			CallableStatement able = con.prepareCall(sqlcall.toString());
			if (params != null) {
				for (int i = 1; i <= params.length; i++) {
					able.setString(i, params[i-1]);
				}
			}
			return able;
		} else {
			throw new SQLException("存储过程格式错误,正确为:储过程名|参数1,参数2,参数3,参数4");
		}
	}
}
