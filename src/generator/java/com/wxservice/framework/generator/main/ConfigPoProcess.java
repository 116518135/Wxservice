package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.generator.setting.SettingConfig;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.VelocityParser;

public class ConfigPoProcess extends SimpleProcess {
	VelocityParser parser;

	Table table;

	Config config;
	public ConfigPoProcess(VelocityParser parser, Table table, Config config) {
		super.action="configpo";
		this.parser = parser;
		this.table = table;
		this.config = config;
	}


	private String createSpringPo(Table table) {
		String classname =PropertyUtil.getPopackage(config)+ "."
				+ PropertyUtil.getPoClassName(table);
		String str = "<value>" + classname + "</value>";
		System.out.println(str);
		return str;
	}

	public void process() {
		try {
			SettingConfig xmlconfig = new SettingConfig(config.getWorkpath());
			String poStr = this.createSpringPo(table);
			xmlconfig.genSpringPo(poStr);
			debug("生产PO的配置成功" );
		} catch (Exception e) {
			debug("生产PO的配置失败");
			e.printStackTrace(System.out);
		}
	}


}
