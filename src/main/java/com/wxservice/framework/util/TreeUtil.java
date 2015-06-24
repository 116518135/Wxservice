package com.wxservice.framework.util;

public class TreeUtil {
	public static void doStringJson(String name, String text, StringBuffer json) {
		json.append("'");
		json.append(name);
		json.append("':");
		json.append("'");
		json.append(text);
		json.append("'");
	}

	public static void doBooleanJson(String name, String text, StringBuffer json) {
		json.append("'");
		json.append(name);
		json.append("':");
		json.append(text);
	}
}
