package com.wxservice.framework.web.tag.support;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.web.tag.TextTag;

public class TagUtil {
	public static String hotkey_str = "hotkey_str";
	final public static String BUTTON_DATE_TYPE = "date";
	final public static String BUTTON_AREA_TYPE = "selectArea";
	final public static String BUTTON_DEPT_TYPE = "selectStore";
	final public static String BUTTON_USER_TYPE = "selectUser";
	final public static String BUTTON_CMP_TYPE = "selectCmp";
	final public static String BUTTON_MEMO_TYPE = "selectMemo";
	final public static String BUTTON_PRODUCT_TYPE = "selectProduct";

	public static String prepareDateButton(TextTag tag,HttpServletRequest request) {
		StringBuffer results=new StringBuffer();
		results.append("<button type=\"button\"  class=\"selectButton\" onclick=\"return showCalendar('");
		results.append( tag.getProperty());
		results.append("', '%Y/%m/%d');\"/>");
		results.append("<img src=\"");
		results.append(request.getContextPath());
		results.append("/images/calendar.gif\" width=\"12\"/></button>");
		return results.toString();
	}
	public static String prepareMemoButton(TextTag tag,HttpServletRequest request) {
		StringBuffer results=new StringBuffer();
		String map=tag.getMap();
		if (StringUtils.isBlank(map)) {
			map = "descrs,memo";
		}
		results
				.append("<button  type=\"button\"  class=\"selectButton\" onclick=\"javascript:selectMemo()\"/>");
		results.append("<img src=\"");
		results.append(request.getContextPath());
		results.append("/images/select.gif\" width=\"12\"/></button>");

		results.append("<script language='javascript'>\n");
		results.append(" function selectMemo(){\n");
		results.append("var url ='");
		results.append(request.getContextPath());
		results.append("/select.do?forward=main&action=selectMemo';\n");
		results
				.append("OpenImportWindow(url,'" + map
						+ "',600,550);\n");
		results.append("}\n");
		results.append("</script>\n");
		return results.toString();
	}
	
	public static String prepareStoreButton(TextTag tag,HttpServletRequest request) {
		StringBuffer results=new StringBuffer();
		String map=tag.getMap();
		if (StringUtils.isBlank(map)) {
			map = "tbsstoreid,tbsstoreid|storename,name";
		}
		String javascriptname="selectStore";
		if(StringUtil.isNotBlank(tag.getJavascriptname())){
			javascriptname=tag.getJavascriptname();
		}
		results.append("<button  type=\"button\" class=\"selectButton\" onclick=\"javascript:");
		results.append(javascriptname);
		results.append("()\"/>");
		results.append("<img src=\"");
		results.append(request.getContextPath());
		results.append("/images/select.gif\" width=\"12\"/></button>");

		results.append("<script language='javascript'>\n");
		results.append(" function ");
		results.append(javascriptname);
		results.append("(){\n");
		results.append("var url ='");
		results.append(request.getContextPath());
		results.append("/select.do?forward=main&action=selectStore");
		if (StringUtils.isNotBlank(tag.getQuerystring())) {
			results.append("&");
			results.append(StringUtil.notNull(tag.getQuerystring()));
		}

		results.append("';\n");
		results.append("OpenImportWindow(url,'");
		results.append(map);
		results.append("',600,550);\n");
		results.append("}\n");
		results.append("</script>\n");
		return results.toString();
	}
	
	public static String prepareCmpButton(TextTag tag,HttpServletRequest request) {
		StringBuffer results=new StringBuffer();
		String map=tag.getMap();
		if (StringUtils.isBlank(map)) {
			map = "tbscmpid,tbscmpid|cmpname,name";
		}
		String javascriptname="selectCmp";
		if(StringUtil.isNotBlank(tag.getJavascriptname())){
			javascriptname=tag.getJavascriptname();
		}
		results.append("<button type=\"button\"  class=\"selectButton\" onclick=\"javascript:");
		results.append(javascriptname);
		results.append("()\"/>");
		results.append("<img src=\"");
		results.append(request.getContextPath());
		results.append("/images/select.gif\" width=\"12\"/></button>");

		results.append("<script language='javascript'>\n");
		results.append(" function ");
		results.append(javascriptname);
		results.append("(){\n");
		results.append("var url ='");
		results.append(request.getContextPath());
		results.append("/select.do?forward=main&action=selectCmp");
		if (StringUtils.isNotBlank(tag.getQuerystring())) {
			results.append("&");
			results.append(StringUtil.notNull(tag.getQuerystring()));
		}
		results.append("';\n");
		results.append("OpenImportWindow(url,'");
		results.append(map);
		results.append("',600,550);\n");
		results.append("}\n");
		results.append("</script>\n");
		return results.toString();
	}
	
	public static String prepareUserButton(TextTag tag,HttpServletRequest request) {
		StringBuffer results=new StringBuffer();
		String map=tag.getMap();
		if (StringUtils.isBlank(map)) {
			map = "tbsuserid,tbsuserid|username,name";
		}
		String javascriptname="selectUser";
		if(StringUtil.isNotBlank(tag.getJavascriptname())){
			javascriptname=tag.getJavascriptname();
		}
		results.append("<button  type=\"button\" class=\"selectButton\" onclick=\"javascript:");
		results.append(javascriptname);
		results.append("()\"/>");
		
		results.append("<img src=\"");
		results.append(request.getContextPath());
		results.append("/images/select.gif\" width=\"12\"/></button>");

		results.append("<script language='javascript'>\n");
		results.append(" function ");
		results.append(javascriptname);
		results.append("(){\n");
		results.append("var url ='");
		results.append(request.getContextPath());
		results
				.append("/select.do?forward=main&action=selectUser");
		if (StringUtils.isNotBlank(tag.getQuerystring())) {
			results.append("&");
			results.append(StringUtil.notNull(tag.getQuerystring()));
		}
		results.append("';\n");
		results
				.append("OpenImportWindow(url,'" + map
						+ "',600,550);\n");
		results.append("}\n");
		results.append("</script>\n");
		return results.toString();
	}
	
	public static String prepareAreaButton(TextTag tag,HttpServletRequest request) {
		StringBuffer results=new StringBuffer();
		String map=tag.getMap();
		if (StringUtils.isBlank(map)) {
			map = "tbsareaid,tbsareaid|areaname,name";
		}
		results.append("<button  type=\"button\"  class=\"selectButton\" onclick=\"javascript:selectArea()\"/>");
		results.append("<img src=\"");
		results.append(request.getContextPath());
		results.append("/images/select.gif\" width=\"12\"/></button>");

		results.append("<script language='javascript'>\n");
		results.append(" function selectArea(){\n");
		results.append("var url ='");
		results.append(request.getContextPath());
		results.append("/tbsarea.do?forward=selectMain';\n");
		results
				.append("OpenImportWindow(url,'" + map
						+ "',600,550);\n");
		results.append("}\n");
		results.append("</script>\n");
		return results.toString();
	}
	
	public static String prepareProdButton(TextTag tag,HttpServletRequest request) {
		StringBuffer results=new StringBuffer();
		String map=tag.getMap();
		if (StringUtils.isBlank(map)) {
			map = "productid,productid";
		}
		results.append("<button type=\"button\" class=\"selectButton\" onclick=\"javascript:selectBillProduct()\"/>");
		results.append("<img src=\"");
		results.append(request.getContextPath());
		results.append("/images/select.gif\" width=\"12\"/></button>");
		return results.toString();
	}
}
