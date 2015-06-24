package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.setting.SettingConfig;
import com.wxservice.framework.generator.util.VelocityParser;
import com.wxservice.framework.util.StringUtil;

public class BillPoGenerator {
	String toolPath = "D:\\project\\wxservice";
	String path;
	String tablerule;
	boolean overWrite = false;
	String poPackage;
	String formPackage;
	String managerPackage;
	String jspStrutsPath;
	String poPath;
	String formPath;
	String managerPath;
	String jspPath;
	String formName;
	String managerName;
	String springName;
	SettingConfig xmlconfig = null;
	String po;
	String poDtl;
	String poCelldtl;
	String poBoxdtl;
	int createPoFlag = 0;

	public BillPoGenerator() {

	}

	public void init(String pkg, String tablerule) {
		this.createPoFlag = 1;
		this.tablerule = tablerule;
		poPackage = "com.wxservice.database.po.main.core" + "." + pkg;
		poPath = "\\src\\main\\java\\"
				+ StringUtil.replaceAll(this.poPackage, ".", "\\");
		po = pkg.toUpperCase() + "Billmaster" + tablerule;
		poDtl = pkg.toUpperCase() + "Billdtl" + tablerule;
		poCelldtl = pkg.toUpperCase() + "Billcelldtl" + tablerule;
		poBoxdtl = pkg.toUpperCase() + "Billboxdtl" + tablerule;
	}

	public void initVelocity(VelocityParser parser) throws Exception {
		parser.addToContext("path", path);
		parser.addToContext("poPackage", poPackage);
		parser.addToContext("poName", po);
		parser.addToContext("poDtlName", poDtl);
		parser.addToContext("poCellDtlName", poCelldtl);
		parser.addToContext("poBoxDtlName", poBoxdtl);
		parser.addToContext("formPackage", formPackage);
		parser.addToContext("managerPackage", managerPackage);
		parser.addToContext("formName", formName);
		parser.addToContext("springName", springName);
		parser.addToContext("managerName", managerName);
		parser.addToContext("tablerule", tablerule);

	}

	public void poProcess(VelocityParser parser) throws Exception {
		String template;
		String filename;
		template = "/src/generator/resources/template3/Master.vm";
		filename = toolPath + poPath + "//" + po + ".java";
		parser.parseVelocity(template);
		parser.processTemplate(filename);

		template = "/src/generator/resources/template3/Detail.vm";
		filename = toolPath + poPath + "//" + poDtl + ".java";
		parser.parseVelocity(template);
		parser.processTemplate(filename);

		template = "/src/generator/resources/template3/BoxDetail.vm";
		filename = toolPath + poPath + "//" + poBoxdtl + ".java";
		parser.parseVelocity(template);
		parser.processTemplate(filename);

		template = "/src/generator/resources/template3/CellDetail.vm";
		filename = toolPath + poPath + "//" + poCelldtl + ".java";
		parser.parseVelocity(template);
		parser.processTemplate(filename);
	}

	public void run() {
		try {
			VelocityParser parser = new VelocityParser();
			initVelocity(parser);
			parser.setOverwrite(true);
			this.poProcess(parser);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static void main(String[] args) {
		try {
			BillPoGenerator billGenerator = new BillPoGenerator();
			String[] rulenames = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
					"j" };
			for (int i = 1; i <= rulenames.length; i++) {
				String pkg = rulenames[i-1];
				for (int rule = 1; rule <= 20; rule++) {
					billGenerator.init(pkg, String.valueOf(rule));
				//	billGenerator.run();
				}
				System.out.println("生成po成功:"+pkg);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
