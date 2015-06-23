package com.wxservice.framework.util;

public class LabelValue {
	String label;

	String value;

	int ivalue;

	double dvalue;
	public LabelValue(){
		
	}

	public LabelValue(String label, String strvalue) {
		this.label = label;
		this.value = strvalue;
	}

	public LabelValue(String label, int ivalue) {
		this.label = label;
		this.ivalue = ivalue;
	}

	public LabelValue(String label, double dvalue) {
		this.label = label;
		this.dvalue = dvalue;
	}

	public double getDvalue() {
		return dvalue;
	}

	public void setDvalue(double dvalue) {
		this.dvalue = dvalue;
	}

	public int getIvalue() {
		return ivalue;
	}

	public void setIvalue(int ivalue) {
		this.ivalue = ivalue;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
