package com.wxservice.framework.generator.pdm;

import org.apache.commons.lang.StringUtils;

public class Column extends PdmObject {
	private Table table;

	String dataType;

	String length;

	String precision;

	String defaultValue;

	String mandatory;

	// 打印的时候是否显示星号
	String mask; // 是否打印星号
	String listid; // 一般用于主键

	String onblur = " "; // 是否有回车事件 onblur=""
	String maxlength = " "; //
	String onkeydown = " ";// onkeydown="nextFocus('passwd')"
	
	String messageKey=null;

	public boolean noNull() {
		return "1".equals(getMandatory());
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getDefaultValueText() {
		return defaultValue == null ? "" : defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDataType() {
		return dataType;
	}

	public String getDataTypeText() {
		return dataType == null ? "" : dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getLength() {
		return length;
	}

	public String getLengthText() {
		return length == null ? "" : length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getPrecision() {
		return precision;
	}

	public String getPrecisionText() {
		return precision == null ? "" : precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("column:");
		sb.append(id);
		sb.append("|");
		sb.append(name);
		sb.append("|");
		sb.append(code);
		sb.append("|");
		sb.append(dataType);
		sb.append("|");
		sb.append(length);
		sb.append("|");
		sb.append(this.precision);
		sb.append("\n");
		return sb.toString();
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getListid() {
		return listid;
	}

	public void setListid(String listid) {
		this.listid = listid;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getOnkeydown() {
		return onkeydown;
	}

	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	public String getHibernateColumn() {
		String hc = "";
		if (StringUtils.isNotBlank(length)) {
			hc = "@Column(length=" + this.length;
			if (StringUtils.isNotBlank(this.precision)) {
				hc = hc + "," + "precision=" + precision;
			}
			hc = hc + ")";
		}
		return hc;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

}
