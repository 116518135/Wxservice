package com.wxservice.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.framework.start.SysSecurityContextHolder;
import com.wxservice.framework.web.session.ClientSession;

public class FormatUtil {
	public static ClientSession getClientSession() {
		ClientSession client = null;
		HttpServletRequest request = SysSecurityContextHolder.getContext()
				.getRequest();
		if (request != null) {
			client = WebUtil.getClientSession(request);
		}
		if (client == null) {
			client = new ClientSession();
		}
		return client;
	}

	public static int getQtyRound() {
		int result = getClientSession().getQtyRound();
		return result;
	}

	public static int getAmtRound() {
		int result = getClientSession().getAmtRound();
		return result;
	}

	public static int getPriceRound() {
		int result = getClientSession().getPriceRound();

		return result;
	}

	public static int getDiscountRound() {
		int result = getClientSession().getDiscountRound();

		return result;
	}

	public static String format(Object o, int scale) {
		Number val = MathUtil.convert(o);
		return format(val, scale);
	}

	public static String format(Number val, int scale) {
		String pattern = "########0.";
		if (val == null) {
			val = new Integer(0);
		}
		if (scale == 0) {
			return String.valueOf(val.intValue());
		} else {
			for (int i = 0; i < scale; i++) {
				pattern += "0";
			}
			Number newval = MathUtil.round(val, scale);
			DecimalFormat form = new DecimalFormat(pattern);
			return form.format(newval);
		}
	}

	public static BigDecimal roundAmt(Number val) {
		if (val == null)
			return BigDecimal.ZERO;
		return MathUtil.round(val, getAmtRound());
	}

	public static BigDecimal roundQty(Number val) {
		if (val == null)
			return BigDecimal.ZERO;
		return MathUtil.round(val, getQtyRound());
	}

	public static BigDecimal roundPrice(Number val) {
		if (val == null)
			return BigDecimal.ZERO;
		return MathUtil.round(val, getPriceRound());
	}

	public static BigDecimal roundDiscount(Number val) {
		if (val == null)
			return BigDecimal.ZERO;
		return MathUtil.round(val, getDiscountRound());
	}

	public static BigDecimal roundDiscount(BigDecimal val) {
		if (val == null)
			return BigDecimal.ZERO;
		return new BigDecimal(formatDiscount(val.doubleValue()));
	}

	public static String formatAmt(Number val) {
		if (val != null) {
			return format(val, getAmtRound());
		} else {
			return "";
		}
	}

	public static String formatQty(Number val) {
		if (val != null) {
			return format(val, getQtyRound());
		} else {
			return "";
		}
	}

	public static String formatPrice(Number val) {

		if (val != null) {
			return format(val, getPriceRound());
		} else {
			return "";
		}

	}

	public static String formatSmsAmt(Number val) {
		if (val != null) {
			return format(val.doubleValue(), 1);
		} else {
			return "";
		}
	}

	public static String formatDiscount(Number val) {

		if (val != null) {
			return format(val.doubleValue(), getDiscountRound());
		} else {
			return "";
		}

	}
	
	public static String format(String property, Number dbl) {
		if (dbl == null)
			return "0";
		String value = "";
		try {

			property = property.toLowerCase();
			if(dbl instanceof Integer){
				value=dbl.toString();
			}else if (property.indexOf("price") >= 0) {
				value = formatPrice(dbl);
			} else if (property.indexOf("amt") >= 0) {
				value = formatAmt(dbl);
			} else if (property.indexOf("qty") >= 0
					|| property.indexOf("point") >= 0
					|| property.indexOf("period") >= 0
					|| property.indexOf("index") >= 0) {
				value = formatQty(dbl);
			} else if (property.indexOf("discount") >= 0
					|| property.indexOf("rate") >= 0
					|| property.indexOf("per") >= 0) {
				value = formatDiscount(dbl);

			} else {
				value = dbl.toString();
			}
		} catch (Exception e) {

		}
		return value;
	}

}
