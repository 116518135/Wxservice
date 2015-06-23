package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.generator.setting.SettingConfig;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.VelocityParser;

public class ConfigManagerProcess extends SimpleProcess {
	VelocityParser parser;

	Table table;

	Config config;
	public ConfigManagerProcess(VelocityParser parser, Table table, Config config) {
		super.action="configmanager";
		this.parser = parser;
		this.table = table;
		this.config = config;
	}


	private String createSpringService(Table table) {
		String springName = PropertyUtil.getSpringManagerName(table);
		String classname = PropertyUtil.getManagerpackage(config) + "."
				+ PropertyUtil.getManagerClassName(table);
		String str = "<bean id=\"" + springName + "\" class=\"" + classname
				+ "\" />";
		System.out.println(str);
		return str;
	}

	public void process() {
		try {
			SettingConfig xmlconfig = new SettingConfig(config.getWorkpath());
			String poStr = this.createSpringService(table);
			xmlconfig.genSpringService(poStr);
			debug("生成spring 配置成功" );
		} catch (Exception e) {
			debug("生成spring 配置失败");
			e.printStackTrace(System.out);
		}
	}


}
