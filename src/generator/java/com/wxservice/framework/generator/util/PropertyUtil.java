package com.wxservice.framework.generator.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.generator.pdm.Column;
import com.wxservice.framework.generator.pdm.Table;
import com.wxservice.framework.util.StringUtil;

public class PropertyUtil {

	public PropertyUtil() {
	}

	public static String getManagerClassName(Table table) {
		return getClassName(table.getCode()) + "Manager";
	}

	public static String getFormClassName(Table table) {
		return getClassName(table.getCode()) + "Form";
	}

	public static String getActionClassName(Table table) {
		return getClassName(table.getCode()) + "Action";
	}

	public static String getModelClassName(Table table) {
		return getClassName(table.getCode()) + "Model";
	}

	public static String getPoClassName(Table table) {
		return getClassName(table.getCode());
	}

	public static String getListProperty(String name) {
		return getPropertyName(name) + "s";
	}

	public static String getClassName(String name) {
		String property = getPropertyName(name);
		return property.substring(0, 1).toUpperCase() + property.substring(1);
	}

	public static String getSpringManagerName(Table table) {
		String manager = getManagerClassName(table);
		return manager.substring(0, 1).toLowerCase() + manager.substring(1);
	}

	public static String getGetterMethod(String name) {
		String property = getPropertyName(name);
		return "get" + property.substring(0, 1).toUpperCase()
				+ property.substring(1);
	}

	public static String getCapColumn(String name) {
		String property = getPropertyName(name);
		return property.substring(0, 1).toUpperCase() + property.substring(1);
	}

	public static String getSetterMethod(String name) {
		String property = getPropertyName(name);
		return "set" + property.substring(0, 1).toUpperCase()
				+ property.substring(1);
	}

	public static String getSelfName() {
		return PropertyUtil.class.getName();
	}

	public static String getPropertyName(String name) {
		if (name.equalsIgnoreCase("class"))
			return "clazz";
		if (name.equals("null"))
			return "nullable";

		return name.toLowerCase();
	}

	public static String getNow() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.format(new Date());
	}

	public static String getPKProperty(Table table) {
		return getPropertyName(table.getPk().getCode());
	}

	public static String getDataJavaType(Column column) {
		if (column.getDataType().toLowerCase().startsWith("varchar")
				|| column.getDataType().toLowerCase().startsWith("char")
				|| column.getDataType().toLowerCase().startsWith("varchar")
				|| column.getDataType().toLowerCase().startsWith("text")
				|| column.getDataType().toLowerCase().startsWith("nvarchar")) {
			return "String";
		} else if (column.getDataType().toLowerCase().startsWith("numeric")
				|| column.getDataType().toLowerCase().startsWith("decimal")) {
			return "java.math.BigDecimal";
		} else if (column.getDataType().toLowerCase().startsWith("datetime")
		        || column.getDataType().toLowerCase().startsWith("date")		
		) {
			return "java.util.Date";
		} else if (column.getDataType().toLowerCase().startsWith("int")
				|| column.getDataType().toLowerCase().startsWith("bit")
				|| column.getDataType().toLowerCase().startsWith("integer")
				|| column.getDataType().toLowerCase().startsWith("small")
				) {
			return "Integer";
		} else if (column.getDataType().toLowerCase().startsWith("bigint")
				) {
			return "Long";
		}
		throw new RuntimeException("\n未知的的数据类型:" + column.getTable().getCode()
				+ "." + column.getCode() + ":" + column.getDataType());
	}

	public static String getDataTypeHbm(Column column) {
		if (column.getDataType().startsWith("varchar")) {
			return "String";
		} else if (column.getDataType().startsWith("char") || column.getDataType().startsWith("text")) {
			return "String";
		} else if (column.getDataType().startsWith("numeric")
				|| column.getDataType().startsWith("decimal")) {
			if (column.getPrecision() == null
					|| column.getPrecision().length() == 0) {
				return "Integer";
			} else {
				return "double";
			}
		} else if (column.getDataType().startsWith("datetime")) {
			return "timestamp";
		} else if (column.getDataType().startsWith("int")) {
			return "integer";
		}
		throw new RuntimeException("\n未知的的数据类型:" + column.getTable().getCode()
				+ "." + column.getCode() + ":" + column.getDataType());
	}

	public static String getPopackage(Config config) {
		return "com.wxservice.database.po" + "." + config.getModule();
	}

	public static String getPopath(Config config) {
		return config.getWorkpath() + "/src/main/java/"
				+ StringUtil.replaceAll(getPopackage(config), ".", "/");
	}

	public static String getFormpackage(Config config) {
		return "com.wxservice.web.form" + "." + config.getModule();
	}

	public static String getFormpath(Config config) {
		return config.getWorkpath() + "/src/main/java/"
				+ StringUtil.replaceAll(getFormpackage(config), ".", "/");
	}

	public static String getManagerpackage(Config config) {
		return "com.wxservice.service" + "." + config.getModule();
	}

	public static String getManagerpath(Config config) {
		return config.getWorkpath() + "/src/main/java/"
				+ StringUtil.replaceAll(getManagerpackage(config), ".", "/");
	}

	public static String getJspfileName(Table table, String action) {
		String f1 = getPoClassName(table).toLowerCase();
		return f1 + "_" + action + ".jsp";
	}

	public static String getJspfilepath(Config config, Table table,
			String action) {
		return "/pages/" + config.getModule() + "/"
				+ getJspfileName(table, action);
	}

	public static String getJspfileabsolutepath(Config config, Table table,
			String action) {
		return config.getWorkpath() + "/src/main/webapp"
				+ getJspfilepath(config, table, action);
	}

}
