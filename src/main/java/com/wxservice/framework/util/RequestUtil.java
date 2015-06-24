package com.wxservice.framework.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	/**
	 * 得到一个String类型参数
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String name) {
		String theString = request.getParameter(name);
		return StringUtil.notNull(theString);
	}

	/**
	 * 得到一个boolean类型参数
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static boolean getBooleanParameter(HttpServletRequest request,
			String name) {
		return getBooleanParameter(request, name, false);
	}

	/**
	 * 得到一个boolean类型参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultVal
	 * @return
	 */
	public static boolean getBooleanParameter(HttpServletRequest request,
			String name, boolean defaultVal) {
		String temp = request.getParameter(name);
		if ("true".equals(temp) || "on".equals(temp)) {
			return true;
		} else if ("false".equals(temp) || "off".equals(temp)) {
			return false;
		} else {
			return defaultVal;
		}
	}

	/**
	 * 得到一个int类型参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static int getIntParameter(HttpServletRequest request, String name,
			int defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {

				ignored.printStackTrace(System.out);
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * 得到一个int[]类型参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static int[] getIntParameters(HttpServletRequest request,
			String name, int defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new int[0];
		}
		int[] values = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * 得到一个double类型参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static double getDoubleParameter(HttpServletRequest request,
			String name, double defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {

				ignored.printStackTrace(System.out);
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * 得到一个double[]类型参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static double[] getDoubleParameters(HttpServletRequest request,
			String name, double defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new double[0];
		}
		double[] values = new double[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Double.parseDouble(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * 得到一个long型参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static long getLongParameter(HttpServletRequest request,
			String name, long defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
				ignored.printStackTrace(System.out);
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * 得到一个long[]类型参数
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static long[] getLongParameters(HttpServletRequest request,
			String name, long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new long[0];
		}
		long[] values = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * 从request中取string Attribute
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getAttribute(HttpServletRequest request, String name) {
		return getAttribute(request, name, false);
	}

	/**
	 * 从request中取string 属性
	 * 
	 * @param request
	 * @param name
	 * @param emptyStringsOK
	 * @return
	 */
	public static String getAttribute(HttpServletRequest request, String name,
			boolean emptyStringsOK) {
		String temp = (String) request.getAttribute(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			} else {
				return temp;
			}
		} else {
			return null;
		}
	}

	/**
	 * 从request中取boolean 属性
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static boolean getBooleanAttribute(HttpServletRequest request,
			String name) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && temp.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 从request中取int属性
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static int getIntAttribute(HttpServletRequest request, String name,
			int defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);

			} catch (Exception ignored) {
				ignored.printStackTrace(System.out);
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * 从request中取long 属性
	 * 
	 * @param request
	 * @param name
	 * @param defaultNum
	 * @return
	 */
	public static long getLongAttribute(HttpServletRequest request,
			String name, long defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {

				ignored.printStackTrace(System.out);
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static Map getRequestMap(HttpServletRequest request) {
		Map parameters = new HashMap();
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			parameters.put(key, request.getParameter(key));
		}
		return parameters;
	}

}