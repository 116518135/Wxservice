package com.wxservice.framework.web.action.support;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.SystemLogger;

public class DateConverter implements Converter {
	private static final Log log = LogFactory.getLog(DateConverter.class);

	public DateConverter(String formatPattern) {

	}

	/**
	 * 转换函数
	 */
	public Object convert(Class arg0, Object value) {
		try {
			if (value instanceof java.util.Date) {
				return value;
			}else{
				String dateStr = (String) value;
				if (StringUtils.isNotBlank(dateStr)) {
					return DateUtil.parse(dateStr);
				}
			}
		} catch (Exception e) {
			SystemLogger.error("Action中,日期转换错误", e);
		}
		return null;
	}

}
