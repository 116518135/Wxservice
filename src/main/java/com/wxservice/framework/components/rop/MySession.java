package com.wxservice.framework.components.rop;

import java.util.HashMap;
import java.util.Map;

import com.rop.session.Session;

public class MySession implements Session {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> attributes = new HashMap<String, Object>();
	String usercode;
	Integer treguserid;
	String devicecode;

    public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public Integer getTreguserid() {
		return treguserid;
	}

	public void setTreguserid(Integer treguserid) {
		this.treguserid = treguserid;
	}

	public void setAttribute(String name, Object obj) {
        attributes.put(name, obj);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

	public String getDevicecode() {
		return devicecode;
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}
    
}
