package com.wxservice.framework.web.tag;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.bean.MessageTag;


public class ResourceTag extends MessageTag {
	public int doStartTag() throws JspException {

		String key = this.key;
		if (key == null) {//key也可以是动态的哦
			// Look up the requested property value
			Object value = TagUtils.getInstance().lookup(pageContext, name,
					property, scope);
			if (value != null && !(value instanceof String)) {
				JspException e = new JspException(messages.getMessage(
						"message.property", key));
				TagUtils.getInstance().saveException(pageContext, e);
				throw e;
			}
			key = (String) value;
		}

		// Construct the optional arguments array we will be using
		Object args[] = new Object[] { arg0, arg1, arg2, arg3, arg4 };

        // TODO 资源TAG没有做完
		String message ="";

		if (message == null) {
			JspException e = new JspException("retrieve message errror !"
					+ "\"" + key + "\"");
			TagUtils.getInstance().saveException(pageContext, e);
			throw e;
		}

		TagUtils.getInstance().write(pageContext, message);

		return (SKIP_BODY);

	}

}
