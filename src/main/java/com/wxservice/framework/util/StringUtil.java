package com.wxservice.framework.util;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {
	public static final String EMPTY_STRING = "";
	// 逗号
	final public static String COMMA = ",";

	// 冒号
	final public static String COLON = ":";

	// 句号
	final public static String FULL_STOP = ".";

	/**
	 * 将字符串转换成boolean类型
	 * 
	 * @param theString
	 * @return
	 */
	public static boolean toBoolean(String theString) {
		if (theString == null) {
			return false;
		}

		theString = theString.trim();

		if (theString.equalsIgnoreCase("y")
				|| theString.equalsIgnoreCase("yes")
				|| theString.equalsIgnoreCase("true")
				|| theString.equalsIgnoreCase("1")) {
			return true;
		}

		return false;
	}

	/**
	 * 确保一个字符串不为空
	 * 
	 * @param theString
	 * @return
	 */
	public static final String notNull(String theString) {
		if (theString == null) {
			return EMPTY_STRING;
		}

		return theString;
	}

	/**
	 * 字符串替换
	 * 
	 * @param text
	 * @param key
	 * @param value
	 * @return
	 */
	public static String replaceAll(String text, String key, String value) {
		String buffer = text;
		if (buffer != null && key != null && value != null) {
			int length = key.length();
			for (int start = buffer.indexOf(key); start != -1; start = buffer
					.indexOf(key, start + value.length())) {
				buffer = buffer.substring(0, start) + value
						+ buffer.substring(start + length);
			}
		}
		return buffer;
	}

	/**
	 * 以逗号为间隔，将String转换成list
	 * 
	 * @param s
	 * @return
	 */
	public static List string2List(String s) {
		return string2List(s, ",");
	}

	/**
	 * 以sep为间隔，将String转换成list
	 * 
	 * @param s
	 * @param sep
	 * @return
	 */
	public static List string2List(String s, String sep) {
		return string2List(s, sep, s != null ? s.length() : Integer.MAX_VALUE);
	}
	
	
	/**
	 * 以sep为间隔，将list转换成String
	 * 
	 * @param list
	 * @param sep
	 * @return
	 */
	public static String list2String(List list,String sep){
		StringBuffer sb = new StringBuffer(); 
		if (list != null && list.size() > 0) {  
	            for (int i = 0; i < list.size(); i++) {  
	                if (list.get(i) == null || list.get(i) == "") {  
	                    continue;  
	                }  
	               sb.append(list.get(i));  
	               sb.append(sep);  
	            } 
	       sb = sb.delete(sb.length() - 1, sb.length());
	       return  sb.toString();  
	    }  
	   
		return "";
	}
	

	/**
	 * 以sep为间隔，将list<Integer>转换成String
	 * 
	 * @param list   
	 * @param sep
	 * @param field  字段名
	 * @return
	 */
	public static String list2String2(List list,String sep,String field){
		StringBuffer sb = new StringBuffer(); 
		if (list != null && list.size() > 0) {  
	            for (int i = 0; i < list.size(); i++) {  
	                if (list.get(i) == null || list.get(i) == "") {  
	                    continue;  
	                }  
	               Map map = (Map) list.get(i);
	               String appendtext = String.valueOf(MapUtil.getInteger(map, field, false));
	               sb.append(appendtext);  
	               sb.append(sep);  
	            } 
	       sb = sb.delete(sb.length() - 1, sb.length());
	       return  sb.toString();  
	    }  
	   
		return "";
	}
	
	
	/**
	 * 以sep为间隔，将String转换成list
	 * 
	 * @param s
	 * @param sep
	 * @param maxSize
	 * @return
	 */
	public static List string2List(String s, String sep, int maxSize) {
		List l = null;
		if (s != null) {
			l = new Vector();
			for (int i = 0; i < maxSize;) {
				int index = s.indexOf(sep, i);
				String token;
				if (index != -1) {
					token = s.substring(i, index);
				} else {
					token = s.substring(i);
				}
				if (token.length() > 0 && !token.equals(sep)) {
					l.add(token.trim());
				}
				i += token.length() + sep.length();
			}
		}
		return l;
	}

	public static String cutString(String s, int len) {
		if (StringUtil.isNotBlank(s)) {
			if (s.length() > len) {
				String str1 = s.substring(0, len);
				return str1 + "......";
			}else{
				return s;
			}
		}
		return "";
	}
	
	public static String string2Sql(String s,String sep){
		List<String> list=string2List(s,sep);
		StringBuffer result=new StringBuffer();
		for(int i=0;i<list.size();i++){
			String str=list.get(i);
			if(i>0){
				result.append(",");
			}
			result.append("'");
			result.append(str);
			result.append("'");
		}
		return result.toString();
	}

	/*
	 * 6位随机码，设置新密码用
	 */
	public static Integer string6(){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i <6; i++){
			result = result * 10 + array[i];
		}
			
		return result;
	}
}
