package com.wxservice.web.form.base;

import com.wxservice.framework.web.form.BaseForm;

public class ChartForm extends BaseForm {
	
	public ChartForm() {
		setAnonymous("1");
		this.setService("chartManager");
		this.setModuleTitle("图表");
	}

	
	

}
