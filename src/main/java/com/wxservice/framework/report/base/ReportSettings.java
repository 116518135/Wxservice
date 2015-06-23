package com.wxservice.framework.report.base;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class ReportSettings implements Serializable {
	List values = new ArrayList();

	public List getValues() {
		return values;
	}

	public void setValues(List values) {
		this.values = values;
	}
}
