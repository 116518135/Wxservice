package com.wxservice.framework.web.tag;

import java.util.Date;

import javax.servlet.jsp.JspException;

import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.FormatUtil;

public class WriteTag extends org.apache.struts.taglib.bean.WriteTag {
	protected String formatValue(Object valueToFormat) throws JspException {
		if (valueToFormat == null)
			return "";
		String v=null;
		if (valueToFormat instanceof Date) {

			v = DateUtil.formatSimplyDate((Date) valueToFormat);
		}
		if (valueToFormat instanceof Number) {
			if (valueToFormat != null) {
				Number dbl = (Number) valueToFormat;
				v = FormatUtil.format(property, dbl);
			}
		} else {
			v = valueToFormat.toString();
		}
		return v;
	}
}
