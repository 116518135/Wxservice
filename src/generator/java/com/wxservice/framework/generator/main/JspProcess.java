package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.VelocityParser;

public class JspProcess extends SimpleProcess {
	public static String strutsPath="/pages/";
	VelocityParser parser;

	Table table;

	Config config;

	public JspProcess(VelocityParser parser, Table table, Config config,String action) {
		this.parser = parser;
		this.table = table;
		this.config = config;
		this.action=action;
	}

	public String getPath(Config config) {
		return PropertyUtil.getJspfileabsolutepath(config, table, action);
	}

	public void process() {
		try {
			String template = this.getTemplate(config);
			parser.parseVelocity(template);
			String filename = this.getPath(config); 
			parser.processTemplate(filename);
			debug("生产JSP文件成功,路径为：" + filename);
		} catch (Exception e) {
			debug("生产JSP文件失败");
			e.printStackTrace(System.out);
		}
	}


}
