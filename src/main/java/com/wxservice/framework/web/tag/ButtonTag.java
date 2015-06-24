package com.wxservice.framework.web.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;

import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.web.tag.support.TagUtil;

public class ButtonTag extends org.apache.struts.taglib.html.ButtonTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9094641813188436664L;
	protected String name = Constants.BEAN_KEY; // 取到formbean
	protected String invoke = null;
	protected String hotkey = null;
	protected String icon = null;
	protected static String lineEnd = System.getProperty("line.separator");

	public static final String MORE_MENU_FALSE = "false";
	protected String moremenu = MORE_MENU_FALSE;

	public String getInvoke() {
		return invoke;
	}

	public void setInvoke(String invoke) {
		this.invoke = invoke;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    public String getStyleClass() {
        return "";
    }
	public int doEndTag() throws JspException {
		// Generate an HTML element
		if (!this.getDisabled()) {

			StringBuffer results = new StringBuffer();
			if (MORE_MENU_FALSE.equalsIgnoreCase(this.getMoremenu())) {
				results.append(getElementOpen());
				prepareAttribute(results, "name", prepareName());
				prepareButtonAttributes(results);
				if (StringUtils.isNotBlank(icon)) {
					prepareAttribute(results, "icon", icon);
				}
				results.append(prepareEventHandlers());
				results.append(prepareStyles());
				prepareOtherAttributes(results);
				results.append(getElementClose());
			} else {
				results.append(" menu.add({");
				results.append("id: '");
				results.append(this.getName());
				results.append("',");
				results.append("text:'");
				results.append(this.getValue());
				results.append("'");
				if (StringUtil.isNotBlank(this.getOnclick())) {
					results.append(",");
					results.append("handler:");
					results.append(this.getOnclick());
				}
				if (StringUtil.isNotBlank(this.getIcon())) {
					results.append(",");
					results.append("iconCls:'");
					results.append(this.getIcon());
					results.append("'");
				}
				results.append("});");

			}
			TagUtils.getInstance().write(pageContext, results.toString());

		}

		return (EVAL_PAGE);

	}

	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();

		this.setDisabled(!this.isRight());

		if (this.getDisabled()) {// 不显示button
			return this.SKIP_BODY;
		}

		this.text = null;
		String hotKey = this.renderHotkey();
		StringBuffer v = new StringBuffer();
		v.append(this.getValue());
		if (StringUtils.isNotBlank(hotKey)) {
			v.append("(");
			v.append(hotKey);
			v.append(")");
		}
		this.setValue(v.toString());

		return (EVAL_BODY_TAG);

	}

	public boolean isRight() {
		if (StringUtil.isNotBlank(invoke)) {
			return SysUtil.isRight(pageContext, invoke);
		}
		return true;

	}

	// 形式表示为：name,key,true 第一个表示名称，第二个表示需要按的键值，第三个是否带上ctrl键

	protected String renderHotkey() {
		if (!this.getDisabled() && StringUtils.isNotBlank(hotkey)) {
			String name = "";
			String key = "";
			boolean ctrlKey = false;
			String[] str = hotkey.split(",");
			if (StringUtil.isBlank(hotkey)) {
				return "";
			}
			if (str.length == 1) {
				name = "";
				key = hotkey;
			} else {
				name = str[0];
				key = str[1];
			}
			if (str.length == 3) {
				if ("true".equalsIgnoreCase(str[2])) {
					ctrlKey = true;
				}
			}
			StringBuffer results = new StringBuffer();
			String hotkeystr = (String) pageContext
					.getAttribute(TagUtil.hotkey_str);
			if (StringUtils.isNotBlank(hotkeystr)) {
				results.append(hotkeystr);
			}
			results.append(lineEnd);
			results.append(" if (");

			results.append(" key==");
			results.append(key);
			if (ctrlKey) {
				results.append(" && event.ctrlKey");
			}
			results.append(" ) {");
			results.append("event.returnValue   =   false;event.keyCode=0;");

			results.append(this.getOnclick().replaceAll("javascript:", ""));
			results.append(";");
			results.append("}");
			pageContext.setAttribute(TagUtil.hotkey_str, results.toString());
			return name;

		}
		return "";
	}

	public void release() {
		super.release();
		name = null;
		invoke = null;
		hotkey = null;
		icon = null;
		moremenu = null;
	}

	public String getHotkey() {
		return hotkey;
	}

	public void setHotkey(String hotkey) {
		this.hotkey = hotkey;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMoremenu() {
		return moremenu;
	}

	public void setMoremenu(String moremenu) {
		this.moremenu = moremenu;
	}
}
