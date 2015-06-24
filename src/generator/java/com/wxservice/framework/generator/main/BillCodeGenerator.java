package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.setting.SettingConfig;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.SpringUtil;
import com.wxservice.framework.generator.util.VelocityParser;
import com.wxservice.framework.util.StringUtil;

public class BillCodeGenerator {
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

	public BillCodeGenerator() {

	}

	public void init(String path, String tablerule, int createPoFlag) {
		this.createPoFlag = createPoFlag;
		this.path = path;
		this.tablerule = tablerule;
		poPackage = "com.wxservice.database.po.main.core";
		formPackage = "com.wxservice.web.form.main";
		;
		managerPackage = "com.wxservice.service.main";
		jspStrutsPath = "/pages/main";
		poPath = "\\src\\main\\java\\"
				+ StringUtil.replaceAll(this.poPackage, ".", "\\");
		formPath = "\\src\\main\\java\\"
				+ StringUtil.replaceAll(this.formPackage, ".", "\\");
		managerPath = "\\src\\main\\java\\"
				+ StringUtil.replaceAll(this.managerPackage, ".", "\\");
		jspPath = "\\src\\main\\webapp\\"
				+ StringUtil.replaceAll(this.jspStrutsPath, "/", "\\");

		formName = PropertyUtil.getClassName(path) + "Form";
		managerName = PropertyUtil.getClassName(path) + "Manager";
		springName = path + "Manager";
		xmlconfig = new SettingConfig(toolPath);

		po = "Billmaster" + tablerule;
		poDtl = "Billdtl" + tablerule;
		poCelldtl = "Billcelldtl" + tablerule;
		poBoxdtl = "Billboxdtl" + tablerule;
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
		System.out.println("***create hiberate po:****");
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
		StringBuffer str = new StringBuffer();
		str.append(this.createSpringPo(po));
		str.append(this.createSpringPo(poDtl));
		str.append(this.createSpringPo(poBoxdtl));
		str.append(this.createSpringPo(poCelldtl));

	}

	public void formProcess(VelocityParser parser) throws Exception {
		System.out.println("***create hiberate po:****");
		String template;
		String filename;
		template = "/src/generator/resources/template3/form.vm";
		filename = toolPath + formPath + "//" + formName + ".java";
		parser.parseVelocity(template);
		parser.processTemplate(filename);
		this.createStrutsForm();
	}

	public void managerProcess(VelocityParser parser) throws Exception {
		System.out.println("***create hiberate po:****");
		String template;
		String filename;
		template = "/src/generator/resources/template3/manager.vm";
		filename = toolPath + managerPath + "//" + managerName + ".java";
		parser.parseVelocity(template);
		parser.processTemplate(filename);
		String str = this.createSpringService();
		xmlconfig.genSpringService(str);
	}

	public void jspProcess(VelocityParser parser) throws Exception {
		System.out.println("***create hiberate po:****");
		this.parseJsp(parser, "edit");
		this.parseJsp(parser, "find");
		this.parseJsp(parser, "list");
		this.parseJsp(parser, "view_master");
		this.parseJsp(parser, "view_other");
		this.parseJsp(parser, "view");

	}

	private void parseJsp(VelocityParser parser, String strff) throws Exception {
		String template;
		String filename;
		template = "/src/generator/resources/template3/bill_" + strff + ".vm";
		filename = toolPath + jspPath + "//" + path + "_" + strff + ".jsp";
		parser.parseVelocity(template);
		parser.processTemplate(filename);
	}

	private String createSpringPo(String poname) throws Exception {
		String classname = poPackage + "." + poname;
		String str = "<value>" + classname + "</value>";
		System.out.println(str);
		xmlconfig.genSpringPo(str.toString());
		return str;
	}

	private void createStrutsForm() throws Exception {
		String classname = formPackage + "." + formName;
		String str = "<form-bean name=\"" + formName + "\" type=\"" + classname
				+ "\" />";
		System.out.println(str);
		xmlconfig.genStrutsForm(str);
	}

	private void createStrutsAction() throws Exception {

		StringBuffer action = new StringBuffer();
		action
				.append("<action name=\""
						+ formName
						+ "\"   path=\"/"
						+ path
						+ "\" scope=\"request\" type=\"com.wxservice.framework.web.action.StrutsAction\">");
		action.append(this.createForward(path + "_list", "list"));
		action.append(this.createForward(path + "_view", "view"));
		action.append(this.createForward(path + "_edit", "edit"));
		action.append(this.createForward(path + "_find", "find"));
		action.append("</action>");
		System.out.println(action.toString());
		xmlconfig.genStrutsAction(action.toString());
	}

	private String createForward(String filename, String name) {
		String path = jspStrutsPath + "/" + filename + ".jsp";
		String str = "<forward name=\"" + name + "\" path=\"" + path + "\" />";
		return str;
	}

	private String createSpringService() {
		String classname = managerPackage + "." + managerName;
		String str = "<bean id=\"" + springName + "\" class=\"" + classname
				+ "\" />";
		System.out.println(str);
		return str;
	}

	public void run() {
		try {
			VelocityParser parser = new VelocityParser();
			initVelocity(parser);
			parser.setOverwrite(true);
			if (createPoFlag == 1) {
				this.poProcess(parser);
			}
			this.formProcess(parser);
			this.managerProcess(parser);
			this.jspProcess(parser);
			this.createStrutsAction();
			this.createSpringService();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	public void runPo() {
		try {
			VelocityParser parser = new VelocityParser();
			initVelocity(parser);
			parser.setOverwrite(true);
			if (createPoFlag == 1) {
				this.poProcess(parser);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static void main(String[] args) {
		try {
			String path="boxing";
			String tablerule="19";
			int createpoflag=1;
			BillCodeGenerator billGenerator = new BillCodeGenerator();
			billGenerator.init(path, tablerule,createpoflag);
			//billGenerator.run();
			//billGenerator.runPo();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
