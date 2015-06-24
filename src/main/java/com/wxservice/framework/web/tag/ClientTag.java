package com.wxservice.framework.web.tag;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.BaseFieldTag;
import org.apache.struts.util.ResponseUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wxservice.framework.util.StringUtil;
import com.wxservice.service.ServiceConfig;
public class ClientTag extends BaseFieldTag {
	 String key=null;
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public int doStartTag() throws JspException {
			
			return (EVAL_BODY_TAG);
		}

		public void release() {

		}
}
