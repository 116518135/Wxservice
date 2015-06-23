package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.VelocityParser;

public class ManagerProcess extends SimpleProcess {

	VelocityParser parser;

	Table table;

	Config config;

	public ManagerProcess(VelocityParser parser, Table table, Config config) {
		this.action = CodeGenerator.manager;
		this.parser = parser;
		this.table = table;
		this.config = config;
	}

	public String getPrefx() {
		return "com.wxservice.service.base";
	}

	public void process() {
		try {
			parser.addToContext("managerPackage",PropertyUtil.getManagerpackage(config));
			parser.addToContext("poPackage",PropertyUtil.getPopackage(config));
			parser.addToContext("formPackage",PropertyUtil.getFormpackage(config));
			String template = this.getTemplate(config);
			parser.parseVelocity(template);
			String filename = PropertyUtil.getManagerpath(config) + "/"
					+ PropertyUtil.getManagerClassName(table) + ".java";
			parser.processTemplate(filename);
			debug("生产Manager文件成功,路径为：" + filename);
		} catch (Exception e) {
			debug("生产Manager文件失败");
			e.printStackTrace(System.out);
		}
	}

}
