package com.wxservice.framework.generator.main;

import java.util.ArrayList;
import java.util.List;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Column;
import com.wxservice.framework.generator.pdm.ColumnGroup;
import com.wxservice.framework.generator.pdm.PdmAccessor;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.generator.pdm.Tables;
import com.wxservice.framework.generator.util.PropertyUtil;
import com.wxservice.framework.generator.util.SpringUtil;
import com.wxservice.framework.generator.util.VelocityParser;
import com.wxservice.framework.util.StringUtil;
public class CodeGenerator {
	public static String form = "form";
	public static String manager = "manager";
	public static String po = "po";
	public static String list = "list";
	public static String edit = "edit";
	public static String view = "view";
	public static String find = "find";
	public static String editline = "editline";
	public static String viewline = "viewline";
	public static String configstruts = "strutsxml";
	public static String configmanager = "managerxml";
	public static String configpo = "poxml";
	Config config = null;
	PdmAccessor pdm = null;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public PdmAccessor getPdm() {
		return pdm;
	}

	public void setPdm(PdmAccessor pdm) {
		this.pdm = pdm;
	}

	public void initVelocity(VelocityParser parser, Table table, Table dtlTable)
			throws Exception {
		parser.addToContext("config", config);
		parser.addToContext("title", config.getTitle());
		parser.addToContext("tableName", config.getTable());
		PropertyUtil propertyUtil = new PropertyUtil();

		parser.addToContext("propertyUtil", propertyUtil);
		parser.addToContext("authorname", "系统自动");
		parser.addToContext("createTime", PropertyUtil.getNow());
		// 处理主表需要编辑的字段
		if (StringUtil.isNotBlank(config.getColumns())) {
			List editcols = new ArrayList();
			List checkcols = new ArrayList();
			List groups = new ArrayList();
			doColumns(table, editcols, checkcols, groups, config.getColumns());
			parser.addToContext("editcols", editcols);
			parser.addToContext("checkcols", checkcols);
			parser.addToContext("groups", groups);
		}
		// 处理明细表需要编辑的字段
		if (dtlTable != null) {
			if (StringUtil.isNotBlank(config.getDtlcolumns())) {
				List editcols = new ArrayList();
				List checkcols = new ArrayList();
				List groups = new ArrayList();
				doColumns(dtlTable, editcols, checkcols, groups, config
						.getDtlcolumns());
				parser.addToContext("dtleditcols", editcols);
				parser.addToContext("dtlcheckcols", checkcols);
				parser.addToContext("dtlgroups", groups);
			}
		}
	}
	
   private Column getColumn(Table table,String code){
	   for (int i = 0; i < table.getColumns().size(); i++) {
			Column col = (Column) table.getColumns().get(i);
			if(code.equalsIgnoreCase(col.getCode())){
				return col;
			}
	   }		
	   return null;
   }

	private void doColumns(Table table, List editcols, List checkcols,
			List groups, String columns) throws Exception {

		List onblurList = new ArrayList();
		List editList = new ArrayList();
		List checkList = new ArrayList();
		// 格式为：字段名,失去焦点事件，保存前是否需要检查
		List columnlist = StringUtil.string2List(columns, "|");
		for (int i = 0; i < columnlist.size(); i++) {
			String columnstr = (String) columnlist.get(i);
			String columnname = null;
			String onblurname = null;
			String checkname = null;
			String temp[] = columnstr.split(";");
			if (temp.length == 3) {
				columnname = temp[0];
				onblurname = temp[1];
				checkname = temp[2];
			} else if (temp.length == 2) {
				columnname = temp[0];
				onblurname = temp[1];
			} else if (temp.length == 1) {
				columnname = temp[0];
				onblurname = "null";
			} else {
				throw new Exception(
						"编辑字段配置格式错误：columnname1;[onblur1];[ischeck1]|columnname2;[onblur2];[ischeck2]");
			}
			editList.add(columnname.toLowerCase());
			onblurList.add(onblurname);
			if (checkname != null
					&& "true".equalsIgnoreCase(checkname.toLowerCase())) {
				checkList.add(columnname.toLowerCase());
			}

		}
		ColumnGroup group = null;
		group = new ColumnGroup();
		Column before = null;
		for(int i=0;i<editList.size();i++){
			String code=(String)editList.get(i);
			Column col = this.getColumn(table, code);
			editcols.add(col);
			if(col==null){
				throw new Exception(  
				"编辑字段"+code+"不存在表"+table.name+"中");
			}
			if (before != null) {
				before.setOnkeydown(" onkeydown=\"nextFocus('"
						+ PropertyUtil.getPropertyName(col.getCode()
								+ "')\""));
			}
			if (!"null".equalsIgnoreCase(onblurList.get(i)
							.toString())) {
				col.setOnblur("onblur=\"" + onblurList.get(i).toString()
						+ "\"");
			}
			String datatype = PropertyUtil.getDataJavaType(col);
			if ("String".equalsIgnoreCase(datatype)) {
				col.setMaxlength("maxlength=\"" + col.getLength() + "\"");
			} else if ("java.util.Date".equalsIgnoreCase(datatype)) {

			} else {
				col.setMaxlength("maxlength=\"20\"");
			}
			
			before = col;

			if (group.left == null && group.right == null) {
				groups.add(group);
			}

			if (group.left == null) {
				group.left = col;
			} else if (group.left != null && group.right == null) {
				group.right = col;
			}
			if (group.left != null && group.right != null) {
				group = new ColumnGroup();
			}

			if (checkList.contains(col.getCode())) {
				checkcols.add(col);
				col.setMask("<font color='red'>*</font>");
			} else {
				col.setMask("");
			}
		}
	}

	public void run() {
		try {
			config.init();

			VelocityParser parser = new VelocityParser();
			parser.setOverwrite(config.isOverwrite());
			String pdmfile = config.getWorkpath() + "/doc/"
					+ config.getPdmFile();
			Tables tables = pdm.readFromPdm(pdmfile);
			Table table = tables.get(config.getTable().toLowerCase());

			if (table != null) {
				config.setTitle(table.getName());
			} else {
				System.out.println(config.getTable() + "表不存在");
				return;
			}
			if (table.getColumns() == null) {
				System.out.println(config.getTable() + "表不存在字段");
				return;
			}
			Table dtltable=null;
			if(StringUtil.isNotBlank(config.getDtltable())){
				dtltable= tables.get(config.getDtltable().toLowerCase());
			}
            if(dtltable!=null){
            	parser.addToContext("dtltable", dtltable);
            }
			parser.addToContext("table", table);
			initVelocity(parser, table,dtltable);
			String action = StringUtil.notNull(config.getAction())
					.toLowerCase();
			if (action.indexOf(form) >= 0) {
				IProcess po = new FormProcess(parser, table, config);
				po.process();
			}
			if (action.indexOf(manager) >= 0) {
				IProcess po = new ManagerProcess(parser, table, config);
				po.process();
			}
			if (action.indexOf(po) >= 0) {
				IProcess po = new PoProcess(parser, table, config);
				po.process();
			}
			if (action.indexOf(list) >= 0) {
				IProcess po = new JspProcess(parser, table, config, list);
				po.process();
			}
			if (action.indexOf(edit) >= 0) {
				IProcess po = new JspProcess(parser, table, config, edit);
				po.process();
			}
			if (action.indexOf(view) >= 0) {
				IProcess po = new JspProcess(parser, table, config, view);
				po.process();
			}
			if (action.indexOf(find) >= 0) {
				IProcess po = new JspProcess(parser, table, config, find);
				po.process();
			}
			if (action.indexOf(editline) >= 0) {
				IProcess po = new JspProcess(parser, table, config, editline);
				po.process();
			}
			if (action.indexOf(viewline) >= 0) {
				IProcess po = new JspProcess(parser, table, config, viewline);
				po.process();
			}
			if (action.indexOf(configstruts) >= 0) {
				IProcess po = new ConfigStrutsProcess(parser, table, config);
				po.process();
			}
			if (action.indexOf(configmanager) >= 0) {
				IProcess po = new ConfigManagerProcess(parser, table, config);
				po.process();
			}
			if (action.indexOf(configpo) >= 0) {
				IProcess po = new ConfigPoProcess(parser, table, config);
				po.process();
			}
			// 处理明细的po
			if (dtltable!=null) {
				if (action.indexOf(po) >= 0) {
					IProcess po = new PoProcess(parser, dtltable, config);
					po.process();
				}
				if (action.indexOf(configpo) >= 0) {
					IProcess po = new ConfigPoProcess(parser, dtltable, config);
					po.process();
				}

			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static void main(String[] args) {
		try {
			CodeGenerator main = (CodeGenerator) SpringUtil.getBean("codeGenerator");
			// IDao dao = (IDao) BeanContext.getBean("dao");
			// main.setDao(dao);
			// System.out.println(tool.getPdm().readFromPdm(tool.getConfig().getPdmFile()));
			main.run();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

}
