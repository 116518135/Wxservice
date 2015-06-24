package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.generator.setting.SettingConfig;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.VelocityParser;

public  class ConfigStrutsProcess extends SimpleProcess {
	VelocityParser parser;

	Table table;

	Config config;
	public ConfigStrutsProcess(VelocityParser parser, Table table, Config config) {
		super.action="configform";
		this.parser = parser;
		this.table = table;
		this.config = config;
	}




	private String createStrutsForm(Table table) {
		String name = PropertyUtil.getFormClassName(table);
		String classname =PropertyUtil.getFormpackage(config)+ "." + name;
		String str = "<form-bean name=\"" + name + "\" type=\"" + classname
				+ "\" />";
		System.out.println(str);
		return str;
	}
	
	private String createStrutsAction(Table table) {
		String name = PropertyUtil.getFormClassName(table);
		String path = "/" + PropertyUtil.getPoClassName(table).toLowerCase();
		StringBuffer action = new StringBuffer();
		action
				.append("<action name=\""
						+ name
						+ "\"   path=\""
						+ path
						+ "\" scope=\"request\" type=\"com.wxservice.framework.web.action.StrutsAction\">");
		action.append(this.createForward(table, "list"));

		action.append(this.createForward(table, "view"));

		action.append(this.createForward(table, "edit"));

		action.append(this.createForward(table, "find"));
		
		action.append(this.createForward(table, "viewline"));
		action.append(this.createForward(table, "editline"));

		action.append("</action>");
		System.out.println(action.toString());
		return action.toString();
	}

	private String createForward(Table table, String name) {
		if (config.getAction().indexOf(name) <0) return "";
		String filename = PropertyUtil.getPoClassName(table).toLowerCase();
		String path = PropertyUtil.getJspfilepath(config, table, name);
		String str = "<forward name=\"" + name + "\" path=\"" + path + "\" />";
		return str;
	}

	public void process() {
		try {
			SettingConfig xmlconfig = new SettingConfig(config.getWorkpath());
			String formStr = this.createStrutsForm(table);
			String actionStr = this.createStrutsAction(table);
			xmlconfig.genStruts(formStr, actionStr);
			debug("生产STRUTS的配置成功" );
		} catch (Exception e) {
			debug("生产STRUTS的配置失败");
			e.printStackTrace(System.out);
		}
	}


}
