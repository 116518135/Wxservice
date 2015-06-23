package com.wxservice.framework.web.tag;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelUtils;
import org.extremecomponents.table.tag.TagUtils;

import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.FormatUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SysUtil;

public class ColumnTag extends org.extremecomponents.table.tag.ColumnTag {
	String pkid = null;// 主键id
	String javascriptname = null;// javascript的函数名，默认是view

	public void modifyColumnAttributes(TableModel model, Column column) {
		Object value = column.getPropertyValue();
		if (value instanceof Number) {
			if (value != null) {
				String property = column.getProperty();
				if (SysUtil.isBrowseAmt(property)) {
					Number dbl = (Number) value;
					value = FormatUtil.format(property, dbl);
					column.setValue(value);
				}else{
					column.setValue(SysFinal.BR0WSEAMT_HIDDENSIGN);
				}
			}

		} else if (value instanceof Date) {
			if (value != null) {
				value = DateUtil.formatSimplyDate((Date) value);
				column.setValue(value);
			}
		}

		if (StringUtils.isNotBlank(pkid)) {
			Object bean = TagUtils.getModel(this).getCurrentRowBean();
			Object pkvalue = TableModelUtils.getColumnPropertyValue(bean, pkid);
			column.addAttribute("pkid", pkvalue);
			column.setCell("com.wxservice.framework.web.tag.support.ViewCell");
		}
		if (StringUtils.isNotBlank(javascriptname)) {
			Object bean = TagUtils.getModel(this).getCurrentRowBean();
			column.addAttribute("javascript", javascriptname);
			column.setCell("com.wxservice.framework.web.tag.support.ViewCell");
		}
	}

	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public String getJavascriptname() {
		return javascriptname;
	}

	public void setJavascriptname(String javascriptname) {
		this.javascriptname = javascriptname;
	}
}
