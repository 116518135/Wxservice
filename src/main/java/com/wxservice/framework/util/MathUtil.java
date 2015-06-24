package com.wxservice.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

public class MathUtil {

	public static int default_scale = 4;

	private static final int MAX_GENERATE_COUNT = 99999;
	private static int generateCount = 0;

	public static synchronized String getUniqueString() {
		if (generateCount > 99999)
			generateCount = 0;
		String uniqueNumber = Long.toString(System.currentTimeMillis())
				+ Integer.toString(generateCount);
		generateCount++;
		return uniqueNumber;
	}

	/**
	 * 
	 * 构造函数
	 */
	private MathUtil() {

	}

	/**
	 * 类型转换函数
	 * 
	 * @param o
	 * @return
	 */
	public static BigDecimal convert(Object o) {
		if (o == null) {
			return BigDecimal.ZERO;
		} else if (o instanceof BigDecimal) {
			return (BigDecimal) o;
		}
		String str = o.toString();
		if (StringUtil.isNotBlank(str)) {
			return new BigDecimal(str);
		} else {
			return BigDecimal.ZERO;
		}
	}

	/**
	 * 两个实数相加
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal add(Object v1, Object v2) {
		BigDecimal b1 = convert(v1);
		BigDecimal b2 = convert(v2);
		return b1.add(b2);

	}

	/**
	 * 两个实数相减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal sub(Object v1, Object v2) {
		BigDecimal b1 = convert(v1);
		BigDecimal b2 = convert(v2);
		return b1.subtract(b2);

	}

	/**
	 * 两个实数相乘
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal mul(Object v1, Object v2) {
		BigDecimal b1 = convert(v1);
		BigDecimal b2 = convert(v2);
		return b1.multiply(b2);

	}

	/**
	 * 相个实数相乘并四舍五入到Sacle位
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static BigDecimal mul(Object v1, Object v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("四舍五入的位数不能为负数");
		}
		BigDecimal b1 = convert(v1);
		BigDecimal b2 = convert(v2);
		return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP);

	}

	/**
	 * 两个实数相除,默认四舍五入到4位
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static BigDecimal div(Object v1, Object v2) {
		return div(v1, v2, default_scale);
	}

	/**
	 * 两个实数相除，默认四舍五入到scale位
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static BigDecimal div(Object v1, Object v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("四舍五入的位数不能为负数");
		}
		BigDecimal b1 = convert(v1);
		BigDecimal b2 = convert(v2);
		if (equal0(b2)) {
			throw new IllegalArgumentException("除数不能为0");
		}
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 两个实数比较
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static int compare(Object v1, Object v2) {
		BigDecimal b1 = convert(v1);
		BigDecimal b2 = convert(v2);
		return b1.compareTo(b2);
	}

	public static boolean equal0(Object v1) {
		BigDecimal b1 = convert(v1);
		return b1.compareTo(BigDecimal.ZERO) == 0;
	}

	public static boolean notEqual0(Object v1) {
		BigDecimal b1 = convert(v1);
		return b1.compareTo(BigDecimal.ZERO) != 0;
	}

	/**
	 * 两个整数比较
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static int compareInt(Integer v1, Integer v2) {

		if (v1 == null) {
			v1 = new Integer(0);
		}
		if (v2 == null) {
			v2 = new Integer(0);
		}
		return v1.compareTo(v2);
	}

	/**
	 * 判断两个整数是否相等
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean equal(Integer v1, Integer v2) {

		int result = compareInt(v1, v2);
		return result == 0;
	}

	/**
	 * 判断两个整数不等
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean notEqual(Integer v1, Integer v2) {
		int result = compareInt(v1, v2);
		return result != 0;
	}

	/**
	 * 实数的四舍五入函数
	 * 
	 * @param v
	 * @param scale
	 * @return
	 */
	public static BigDecimal round(Object v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("四舍五入的位数不能为负数");
		}
		BigDecimal b = convert(v);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 将字符串转换成整型
	 * 
	 * @param value
	 * @param defaultNum
	 * @return
	 */
	public static int parsetInt(String value, int defaultNum) {
		if (value != null && !value.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(value);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * 将string转换为double
	 * 
	 * @param value
	 * @param defaultNum
	 * @return
	 */
	public static double parseDouble(String value, double defaultNum) {
		if (StringUtils.isBlank(value)) {
			return defaultNum;
		}

		value = value.replaceAll(",", "");
		value = value.replaceAll(" ", "");
		value = value.replaceAll("￥", "");

		double num = defaultNum;
		try {
			num = Double.parseDouble(value);
		} catch (Exception ignored) {
		}
		return num;
	}

	/**
	 * 将string 转换为double
	 * 
	 * @param value
	 * @return
	 */
	public static double parseDouble(String value) {
		return parseDouble(value, 0);
	}

	public static int isNullInteger(Integer v) {
		if (v == null) {
			return 0;
		} else {
			return v.intValue();
		}
	}

	/**
	 * 计算百分比
	 * 
	 * @param value
	 * @return
	 */
	public static String getPercent(int x,int total){  
		   String result="";//接受百分比的值  
		   double x_double=x*100.0;  
		   double tempresult=x_double/total;  
		   DecimalFormat df1 = new DecimalFormat("0");    //##.00%   百分比格式，后面不足2位的用0补齐  
		   result= df1.format(tempresult);    
		   return result;  
		}  
	
	
}
