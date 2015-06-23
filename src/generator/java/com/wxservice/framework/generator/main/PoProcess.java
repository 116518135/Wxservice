package com.wxservice.framework.generator.main;
  
import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.VelocityParser;

public class PoProcess extends  SimpleProcess {
	VelocityParser parser;
	Table table;
	Config config;
	public PoProcess(VelocityParser parser, Table table, Config config) {
		this.action=CodeGenerator.po;
		this.parser = parser;
		this.table = table;
		this.config = config;
	}
	
	 public String getPrefx(){
		 return "com.wxservice.database.po";
	 }

	public void process(){
		  try{
			parser.addToContext("table", table);
			parser.addToContext("poPackage", PropertyUtil.getPopackage(config));
			String template =this.getTemplate(config);
			parser.parseVelocity(template);
			String filename = PropertyUtil.getPopath(config) + "/"
					+ PropertyUtil.getPoClassName(table) + ".java";
			parser.processTemplate(filename);
			debug("生产PO文件成功,路径为："+filename);
			//处理明细
			
		  }catch(Exception e){
			debug("生产PO文件失败");
			e.printStackTrace(System.out);  
		  }
	}
}
