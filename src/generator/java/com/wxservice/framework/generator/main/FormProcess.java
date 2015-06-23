package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.VelocityParser;

public class FormProcess extends SimpleProcess {

	VelocityParser parser;

	Table table;

	Config config;

	public FormProcess(VelocityParser parser, Table table, Config config) {
		this.action = CodeGenerator.form;
		this.parser = parser;
		this.table = table;
		this.config = config;
	}


	public void process() {
		try {
			parser.addToContext("formPackage", PropertyUtil.getFormpackage(config));
			String template = this.getTemplate(config);
			parser.parseVelocity(template);
			String filename = PropertyUtil.getFormpath(config) + "/"
					+ PropertyUtil.getFormClassName(table) + ".java";
			parser.processTemplate(filename);
			debug("生产Form文件成功,路径为：" + filename);
		} catch (Exception e) {
			debug("生产Form文件失败");
			e.printStackTrace(System.out);
		}
	}

}
