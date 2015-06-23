package com.wxservice.framework.util;

public class SysFinal {
	public static String CLIENT_SESSION ="SYSTEM_ClIENTSESSION";//servlet的session key
	public static String WEB_MESSAGE = "SYSTEM_MESSAGE";//弹出的key
	public static String WEB_RAND = "SYSTEM_WEBRAND";//随机数的key
	public static String WEB_PATH = "SYSTEM_PATH";//web path的key
	public static String RANDOM_CODE = "RANDOM_CODE";//验证码的key
	public static final int AMT_ROUND = 2;//默认的金额小数位
	public static final int DISCOUNT_ROUND = 3;//默认的折扣小数位
	public static final int PRICE_ROUND = 2;//默认的单价小数位
	public static final int AMOUNT_ROUND = 0;//默认的数量小数位
	public static final String MONEY_MASK = "******";//金额屏蔽符号
	public static final String STRUTS_SYSTEM_ERROR = "systemError";
	public static final String STRUTS_SESSION_ERROR = "sessionError";
	public static final String STRUTS_ACCESS_ERROR = "accessError";
	public static final String STRUTS_FORM = "struts_form";//form的小数位
	public static final int SYSTEM_ZGSID = -1; // 默认的总公司编号
	public static final int SYSTEM_ROLEID = -1; // 默认的系统权限
	public static final int SYSTEM_USERID = -1; // 默认的超级用户
	public static final String LINE_SEPARATOR = System.getProperty("line.separator"); // 取换行符
	public static final String BR0WSEAMT_KEY="browseamt";
	public static final String BR0WSEAMT_DISCOUNT="discount";
	public static final String BR0WSEAMT_AMT="amt";
	public static final String BR0WSEAMT_PRICE="price";
	public static final String BR0WSEAMT_HIDDENSIGN="******";

}
