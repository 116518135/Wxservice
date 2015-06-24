package com.wxservice.framework.generator.pdm;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.wxservice.framework.generator.config.Config;

public class PdmAccessor {
	Config config = null;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	public Tables readFromPdm(String pdmfilename) throws Exception {
		Tables tables = new Tables();
		SAXBuilder builder = new SAXBuilder();
		InputStream in = null;
		Document doc = null;

		in = new FileInputStream(pdmfilename);
		doc = builder.build(in);
		Element rootElement = doc.getRootElement();
		List tableElements = new ArrayList();
		visitElement(rootElement, tableElements, PdmFinal.TABLE);
		assert tableElements.size() == 1;
		if (tableElements != null && tableElements.size() > 0) {
			for (int i = 0; i < tableElements.size(); i++) {
				Element el = (Element) tableElements.get(i);
				if (this.isAssignTable(el)) {
					Table table = this.doTable(el);
					if (table != null) {
						tables.put(table.getCode().toLowerCase(), table);
					}
				}

			}
		}

		return tables;

	}

	private void visitElement(Element ele, List list, String xmlSign) {
		if (ele.getQualifiedName().equals(xmlSign)) {

			list.add(ele);
		}
		List children = ele.getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Element child = (Element) iter.next();
			visitElement(child, list, xmlSign);
		}
	}



	public boolean isAssignTable(Element el) {
		List children = el.getChildren();
		for (int i = 0; i < children.size(); i++) {
			Element e = (Element) children.get(i);
			if (e.getName().equals(PdmFinal.TABLE_Code)) {
				String code = e.getText();
				if (code.equalsIgnoreCase(config.getTable()) || code.equalsIgnoreCase(config.getDtltable())) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private Table doTable(Element el) {
		Table table = new Table();
		table.setId(el.getAttributeValue(PdmFinal.TABLE_ATTR_ID));
		List children = el.getChildren();
		Element primaryKey = null;
		Element keys = null;

		for (int i = 0; i < children.size(); i++) {
			Element e = (Element) children.get(i);

			if (e.getName().equals(PdmFinal.TABLE_Code)) {
				table.setCode(e.getText());
				System.out.println("开始处理:------" + e.getText());
			} else if (e.getName().equals(PdmFinal.TABLE_Name)) {
				table.setName(e.getText());
				System.out.println("开始处理:------" + e.getText());
			} else if (e.getName().equals(PdmFinal.TABLE_Comment)) {
				table.setComment(e.getText());
			} else if (e.getName().equals(PdmFinal.TABLE_Columns)) {
				table.setColumns(doColumns(e, table));
			} else if (e.getName().equals(PdmFinal.TABLE_ObjectID)) {
				table.setObjectID(e.getText());
			}
		}
		if ("".equals(table.getCode()) || null == table.getCode()) {
			return null;
		}
		table.setPk(doPrimaryKey(el, table));
		return table;
	}

	private Column doPrimaryKey(Element el, Table table) {
		List tempList = new ArrayList();
		this.visitElement(el, tempList, PdmFinal.TABLE_Keys_Key_Columns);
		assert tempList.size() > 0;
		Element t = (Element) tempList.get(0);
		List keyList = t.getChildren();
		Column result = null;
		assert keyList != null;
		assert keyList.size() > 0;
		Element e = (Element) keyList.get(0);
		String id = e.getAttributeValue(PdmFinal.PrimaryKey_Key_Ref);
		List columns = table.getColumns();
		if (columns != null && columns.size() > 0) {
			for (int i = 0; i < columns.size(); i++) {
				Column col = (Column) columns.get(i);
				if (col.getId().equals(id)) {
					result = col;
					columns.remove(col);
					break;
				}

			}
		}
		return result;
	}

	private List doColumns(Element el, Table table) {
		List ret = new ArrayList(20);
		List columns = el.getChildren();
		for (int i = 0; i < columns.size(); i++) {
			Column column = doColumn((Element) (columns.get(i)), table);
			ret.add(column);
		}

		return ret;
	}

	private Column doColumn(Element el, Table table) {
		Column ret = new Column();
		ret.setTable(table);
		ret.setId(el.getAttributeValue(PdmFinal.COLUMNE_ATTR_ID));
		List children = el.getChildren();
		for (int i = 0; i < children.size(); i++) {
			Element e = (Element) children.get(i);
			if (e.getName().equals(PdmFinal.COLUMN_Code)) {
				ret.setCode(e.getText());
			} else if (e.getName().equals(PdmFinal.COLUMN_Name)) {
				ret.setName(e.getText());

			} else if (e.getName().equals(PdmFinal.COLUMN_Comment)) {
				ret.setComment(e.getText());
			} else if (e.getName().equals(PdmFinal.COLUMN_DefaultValue)) {
				ret.setDefaultValue(e.getText());
			} else if (e.getName().equals(PdmFinal.COLUMN_DataType)) {
				ret.setDataType(e.getText());
			} else if (e.getName().equals(PdmFinal.COLUMN_Length)) {
				ret.setLength(e.getText());
			} else if (e.getName().equals(PdmFinal.COLUMN_Precision)) {
				ret.setPrecision(e.getText());
			} else if (e.getName().equals(PdmFinal.COLUMN_Mandatory)) {
				ret.setMandatory(e.getText());
			} else if (e.getName().equals(PdmFinal.COLUMN_ObjectID)) {
				ret.setObjectID(e.getText());
			}
			
		}
		// 设置messageKey
		return ret;
	}

	public static void main(String[] args) {
		try {
			PdmAccessor accessor = new PdmAccessor();
			Config config=new Config();
			config.setTable("tsysmenu,tsyslog");
			accessor.setConfig(config);
			String file = "D:\\project\\ucard\\doc\\ucard.pdm";
			Tables tables = accessor.readFromPdm(file);
			System.out.println(tables.toString());

			// 注意 @column(length=xxx precision=xxx)

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

}
