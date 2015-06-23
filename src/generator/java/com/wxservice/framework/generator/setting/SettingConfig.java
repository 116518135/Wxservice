package com.wxservice.framework.generator.setting;
  
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.wxservice.framework.generator.util.FileHelper;

public class SettingConfig {
	String dataAccessContext = "/src/main/resources/spring/dataAccessContext-hibernate.xml";

	String serviceContext = "/src/main/resources/spring/serviceContext.xml";

	String strutsconfig = "/src/main/webapp/WEB-INF/struts-config.xml";

	String serviceContext_path = "/beans"; // 判断id

	String dataAccessContext_path = "/beans/bean/property/list"; // judge

	// value

	String strutsconfig_path_form = "/struts-config/form-beans"; // judge

	// name

	String strutsconfig_path_action = "/struts-config/action-mappings";// 判断

	// name

	public SettingConfig(String path) {
		dataAccessContext = path + dataAccessContext;
		serviceContext = path + serviceContext;
		strutsconfig = path + strutsconfig;
	}

	private Document getDocument(String xmlFileName) throws Exception {
		// 先备份
		String content = FileHelper.readFile(xmlFileName);
		FileHelper.saveToFile(xmlFileName + ".bak", content, true);
		// 备份好了
		SAXReader reader = new SAXReader();
		reader.setIncludeExternalDTDDeclarations(false);
		reader.setIncludeInternalDTDDeclarations(false);
		File file = new File(xmlFileName);

		if (file.exists()) {
			return reader.read(xmlFileName);
		} else {
			return DocumentHelper.createDocument();
		}
	}

	// 得到当前的Element
	private Element getElement(Document document, String path) throws Exception {
		Element parent = (Element) document.selectSingleNode(path);
		return parent;
	}

	// 判断当前的配置是否已经配置好了.
	private boolean isExists(Element parent, Element append, String filepath) {
		boolean exists = false;
		Element element = null;
		for (Iterator i = parent.elementIterator(); i.hasNext();) {
			element = (Element) i.next();
			if (filepath.endsWith("struts-config.xml")) {
				if (element.attributeValue("name").equalsIgnoreCase(
						append.attributeValue("name"))) {
					exists = true;
					break;
				}
			}
			if (filepath.endsWith("serviceContext.xml")) {
				if (element.attributeValue("id").equalsIgnoreCase(
						append.attributeValue("id"))) {
					exists = true;
					break;
				}
			}
			if (filepath.endsWith("dataAccessContext-hibernate.xml")) {
				String e1 = element.getText().trim();
				String e2 = append.getText().trim();
				if (e1.equalsIgnoreCase(e2)) {
					exists = true;
					break;
				}

			}

		}
		if (exists) {
			parent.remove(element);//先删除
		}

		return exists;
	}

	private void genService(String filepath, String path, String appendStr)
			throws Exception {
		Document document = this.getDocument(filepath);
		Element parent = this.getElement(document, path);
		Document tempdoc = DocumentHelper.parseText(appendStr);
		Element append = tempdoc.getRootElement();
		// 		
		if (this.isExists(parent, append, filepath)) {

		}
		parent.add(append);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter output = new XMLWriter(new FileWriter(new File(filepath)),
				format);
		output.write(document);
		output.close();
	}

	private void genStrutsService(String filepath, String formStr,
			String actionStr) throws Exception {
		Document document = this.getDocument(filepath);
		Element parent = this.getElement(document, this.strutsconfig_path_form);
		Document tempdoc = DocumentHelper.parseText(formStr);
		Element formappend = tempdoc.getRootElement();
		// 		
		if (this.isExists(parent, formappend, filepath)) {

		}

		
		parent.add(formappend);

		parent = this.getElement(document, this.strutsconfig_path_action);
		tempdoc = DocumentHelper.parseText(actionStr);
		Element actionappend = tempdoc.getRootElement();
		if (this.isExists(parent, actionappend, filepath)) {

		}
		parent.add(actionappend);

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter output = new XMLWriter(new FileWriter(new File(filepath)),
				format);
		
		output.write(document);
		output.close();
	}

	public void genSpringService(String appendStr) throws Exception {
		this.genService(this.serviceContext, this.serviceContext_path,
				appendStr);
	}

	public void genSpringPo(String appendStr) throws Exception {
		this.genService(this.dataAccessContext, this.dataAccessContext_path,
				appendStr);
	}

	public void genStrutsForm(String appendStr) throws Exception {
		this.genService(this.strutsconfig, this.strutsconfig_path_form,
				appendStr);
	}

	public void genStrutsAction(String appendStr) throws Exception {
		this.genService(this.strutsconfig, this.strutsconfig_path_action,
				appendStr);
	}

	public void genStruts(String formStr, String actionStr) throws Exception {
		this.genStrutsService(this.strutsconfig, formStr, actionStr);
	}

	public static void main(String[] args) {
		try {
			String path = "D:\\developer\\examples\\esale\\";
			SettingConfig config = new SettingConfig(path);
			config
					.genSpringService("<bean id=\"dao1\" class=\"my.framework.dao.DaoImpl\"/>");

			config
					.genSpringPo("<value>com.sales.database.po.tsys.TestEntity1</value>");
			String formstr = "<form-bean name=\"LoginForm1\"         type=\"com.sales.web.base.LoginForm1\"/>";
			StringBuffer action = new StringBuffer();
			action
					.append("<action name=\"FymisQueryForm1\"   path=\"/fymis\" scope=\"request\" type=\"my.framework.web.action.StrutsAction\">");
			action
					.append("<forward name=\"fymis\" path=\"/WEB-INF/pages/fymis/fymisquery.jsp\"/>");
			action.append("</action>");
			config.genStruts(formstr, action.toString());

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
