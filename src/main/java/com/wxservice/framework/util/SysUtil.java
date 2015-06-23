package com.wxservice.framework.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;

import com.wxservice.framework.components.License.LicenseService;
import com.wxservice.framework.components.right.BaseRightService;
import com.wxservice.framework.engine.support.SysConfig;
import com.wxservice.framework.start.SysSecurityContextHolder;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.service.RightService;


public class SysUtil {
	private static final Log log = LogFactory.getLog(SysUtil.class);
	public static final String _systemUpdatePath = "/grade";
	public static final String _downloadPath = "/download";
	public static final String _systemAttached = "/attached";
	public static final String _systemUpdateFile = "/ucard.rar";
	public static final String _systemSqlScript = "/ucard.sql";

	/**
	 * 只用于开始登录的时候
	 * 
	 * @param url
	 * @return
	 */
	public static String getUrlKey(String url) {
		if (StringUtils.isBlank(url)) {
			return "";
		}
		if (url.indexOf("report.do") >= 0 && url.indexOf("reportid") >= 0) {
			return url;
		}
		String key = url.substring(1, url.indexOf("?"));
		return key;
	}

	/**
	 * 处理一些变量
	 * 
	 * @param src
	 * @param map
	 * @return
	 */
	public static String doVarible(String src, Map map) {
		if (map == null) {
			return src;
		}
		if (map.size() == 0) {
			return src;
		}
		Iterator it = map.keySet().iterator();
		StringBuffer str = new StringBuffer(src);
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = (Object) map.get(key);
			if (value != null) {
				String temp = StringUtil.replace(str.toString(), key, value
						.toString());
				str.setLength(0);
				str.append(temp);
			}
		}
		return str.toString();
	}

	/**
	 * 
	 * @param m
	 *            需要的数字
	 * @param n
	 *            生成的位数之和
	 * @return
	 */
	public static String getCode(int m, int n) {

		String result = StringUtil.leftPad(String.valueOf(m), n, "0");

		return result;

	}

	/**
	 * 当一个用户出现几个权限组的时候,必须进行权限的合并， 两个字符串必须长度相等，
	 * 
	 * @param auth1
	 * @param auth2
	 * @return
	 */
	public static String authMerge(String auth1, String auth2) {
		if (StringUtil.isBlank(auth1)) {
			return auth2;
		}
		if (StringUtil.isBlank(auth2)) {
			return auth1;
		}
		StringBuffer auth = new StringBuffer();
		int len1 = auth1.length();
		int len2 = auth2.length();
		int len = len1;
		if (len1 > len2)
			len = len2;
		for (int i = 0; i < len; i++) {
			String s1 = auth1.substring(i, i + 1);
			String s2 = auth2.substring(i, i + 1);
			if ("1".equals(s1) || "1".equals(s2)) {
				auth.append("1");
			} else if ("2".equals(s1) || "2".equals(s2)) {
				auth.append("2");
			} else {
				auth.append("0");
			}

		}
		return auth.toString();
	}

	/**
	 * 权限判断,invoke表示方法名
	 * 
	 * @param context
	 * @param invoke
	 * @return
	 */
	public static boolean isRight(PageContext pageContext, String invoke) {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		BaseForm form = (BaseForm) request.getAttribute(SysFinal.STRUTS_FORM);
		BaseRightService right = (BaseRightService) WebUtil.getBean(
				"rightService", form);
		if (StringUtil.isBlank(invoke)) {
			return true;
		} else {
			boolean result = right.invokeMethod(request, form, invoke);
			return result;
		}
	}

	// 判断是否有浏览金额的权限
	public static boolean isBrowseAmt(PageContext pageContext, String invoke) {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		return isBrowseAmt(request, invoke);
	}

	// 判断是否有浏览金额的权限
	public static boolean isBrowseAmt(HttpServletRequest request, String invoke) {
		int nouseflag = SysConfig.getIntConfig("esk.rightservie.noflag");
		if (nouseflag == 1) {// 不使用浏览金额的权限
			return true;
		}
		Object o = request.getAttribute(SysFinal.BR0WSEAMT_KEY);
		if (o != null) {
			return "1".equals(o.toString());
		} else {
			BaseForm form = (BaseForm) request
					.getAttribute(SysFinal.STRUTS_FORM);
			BaseRightService right = (BaseRightService) WebUtil.getBean(
					"rightService", form);
			boolean result = true;
			if (StringUtil.isBlank(invoke)) {
				invoke = "isBrowseAmt";
			}
			result = right.invokeMethod(request, form, invoke);
			if (result) {
				request.setAttribute(SysFinal.BR0WSEAMT_KEY, 1);
			} else {
				request.setAttribute(SysFinal.BR0WSEAMT_KEY, 0);
			}
			return result;
		}
	}

	public static boolean isBrowseReportAmt(HttpServletRequest request) {
		int nouseflag = SysConfig.getIntConfig("esk.rightservie.noflag");
		if (nouseflag == 1) {
			return true;
		}
		Object o = request.getAttribute(SysFinal.BR0WSEAMT_KEY);
		if (o != null) {
			return "1".equals(o.toString());
		} else {
			BaseForm form = (BaseForm) request
					.getAttribute(SysFinal.STRUTS_FORM);
			RightService right = (RightService) WebUtil.getBean("rightService",
					form);
			boolean result = true;
			try {
				result = right.isReportBrowseAmt(request, form);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
			if (result) {
				request.setAttribute(SysFinal.BR0WSEAMT_KEY, 1);
			} else {
				request.setAttribute(SysFinal.BR0WSEAMT_KEY, 0);
			}
			return result;
		}
	}

	public static boolean isBrowseAmt() {
		HttpServletRequest request = SysSecurityContextHolder.getContext()
				.getRequest();
		return isBrowseAmt(request, "");
	}
	//传递一个路径。判断这个路径是不是有浏览权限
	public static boolean isPathBrowse(String key) {
		HttpServletRequest request = SysSecurityContextHolder.getContext()
				.getRequest();
		RightService right = (RightService) WebUtil.getBean("rightService",
				request);
		boolean result=right.hasRight(request, key, BaseRightService.FBROWSE);
		return result;

	}
	
	public static boolean isBrowseAmt(String field) {
		if (StringUtil.isNotBlank(field)) {
			if (field.toLowerCase().indexOf(SysFinal.BR0WSEAMT_DISCOUNT) >= 0
					|| field.toLowerCase().indexOf(SysFinal.BR0WSEAMT_AMT) >= 0
					|| field.toLowerCase().indexOf(SysFinal.BR0WSEAMT_PRICE) >= 0) {
				HttpServletRequest request = SysSecurityContextHolder
						.getContext().getRequest();
				return isBrowseAmt(request, "");
			}
		}
		return true;
	}
	public static boolean isBrowseReportAmt(String field) {
		if (StringUtil.isNotBlank(field)) {
			if (field.toLowerCase().indexOf(SysFinal.BR0WSEAMT_DISCOUNT) >= 0
					|| field.toLowerCase().indexOf(SysFinal.BR0WSEAMT_AMT) >= 0
					|| field.toLowerCase().indexOf(SysFinal.BR0WSEAMT_PRICE) >= 0) {
				HttpServletRequest request = SysSecurityContextHolder
						.getContext().getRequest();
				return isBrowseReportAmt(request);
			}
		}
		return true;
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or <code>Object.class</code> if
	 *         cannot be determined
	 */
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or <code>Object.class</code> if
	 *         cannot be determined
	 */
	public static Class getSuperClassGenricType(Class clazz, int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			log.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			log.warn("Index: " + index + ", Size of " + clazz.getSimpleName()
					+ "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			log
					.warn(clazz.getSimpleName()
							+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}

	public static void copyProperties(Map dest, Object orig) {

		try {
			PropertyDescriptor origDescriptors[] = PropertyUtils
					.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();

				Object value = PropertyUtils.getSimpleProperty(orig, name);
				dest.put(name, value);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}

	public static String getAbsUrl(String relUrl, HttpServletRequest request) {
		if (relUrl == null) {
			return "";
		}
		String url = request.getRequestURL().toString();
		StringBuffer buf = new StringBuffer();
		buf.append(url.substring(0, url.lastIndexOf("/")));
		buf.append(relUrl);
		return buf.toString();

	}

	public static boolean match(String txt, String reg) {

		Pattern pattern = Pattern.compile(reg);
		Matcher m = pattern.matcher(txt);
		while (m.find()) {
			return true;
		}
		return false;
	}

	public static int checkTel(String tel) {

		if (match(tel,
				"^(134|135|136|137|138|139|150|151|152|157|158|159|188|187){1}\\d{8}$")) {
			return 0;
		}
		if (match(tel, "^(130|131|132|156|155|185|186){1}\\d{8}$")) {
			return 1;
		}
		if (match(tel, "^(133|153|189){1}\\d{8}$")) {
			return 2;
		}
		return -1;

	}

	public static String getClassname(String table) {
		if (table == null)
			return "";
		String str = table.toLowerCase();
		StringBuffer classname = new StringBuffer(str.substring(0, 1)
				.toUpperCase());
		classname.append(str.substring(1));
		return classname.toString();
	}

	public static Map doMapping(String mapping, Object entity) {
		Map map = new HashMap();
		try {
			map.put("result", "1");
			String[] str = StringUtil.split(mapping, "|");
			for (String temp : str) {
				String[] str1 = StringUtil.split(temp, ",");
				String field = str1[1].toLowerCase();
				map.put(field, BeanUtils.getProperty(entity, field));
			}
		} catch (Exception e) {
			SystemLogger.error("处理Mapping的时候发生错误:doMapping", e);
		}
		return map;
	}

	

	public static String getSystemUpdateDir() {
		StringBuffer path = new StringBuffer("");
		path.append(LicenseService.systemPath).append("/").append(
				_systemUpdatePath).append("/");
		File directory = new File(path.toString());
		if (!directory.exists()) {// 目录不存在,必须强制建立
			try {
				FileUtils.forceMkdir(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		return path.toString();
	}

	public static String getSystemUpdateFile() {
		StringBuffer file = new StringBuffer();
		file.append(getSystemUpdateDir());
		file.append(_systemUpdateFile);
		return file.toString();
	}

	public static String getSystemSqlScript() {
		StringBuffer file = new StringBuffer();
		file.append(getSystemUpdateDir());
		file.append(_systemSqlScript);
		return file.toString();
	}

	public static void updateHost(String server) {
		String filename = getSystemUpdateFile();
		if (StringUtils.isNotBlank(server)) {
			String[] str = server.split(",");
			for (String line : str) {
				UcardGradeup.gradeHost(filename, line);
			}
		}
		// 删除升级文件
		File file = new java.io.File(filename);
		if (!file.exists()) {
			System.out.println("升级文件不存在");
			return;
		} else {
			file.delete();
		}
	}

	public static String getClassname(Object o) {
		String str = o.getClass().getName();
		List<String> list = StringUtil.string2List(str, ".");
		return list.get(list.size() - 1);
	}

	public static String getSystemDownloadDir() {
		StringBuffer path = new StringBuffer("");
		path.append(LicenseService.systemPath).append("/")
				.append(_downloadPath).append("/");
		File directory = new File(path.toString());
		if (!directory.exists()) {// 目录不存在,必须强制建立
			try {
				FileUtils.forceMkdir(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		return path.toString();
	}

	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		t.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}
	
	public static String getBillProdLike(){
		int likeflag=SysConfig.getIntConfig("esk.bill.prodlike");
		if(likeflag==0){
			return "%";
		}
		return "";
	}
	
	public static int getDsTypeflag(){
		int flag = SysConfig.getIntConfig("esk.account.useflag");
		return flag;
	}

	public static String getCacheKey(String classname){
		String dsname=WebUtil.getDstype();
		StringBuffer buf=new StringBuffer();
		buf.append(dsname);
		buf.append(".");
		buf.append(classname);
		return buf.toString();
	}

}
