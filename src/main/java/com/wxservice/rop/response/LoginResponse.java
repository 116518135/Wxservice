package com.wxservice.rop.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "loginResponse")
public class LoginResponse{

    @XmlAttribute
    private Integer tbsuserid;

	public Integer getTbsuserid() {
		return tbsuserid;
	}

	public void setTbsuserid(Integer tbsuserid) {
		this.tbsuserid = tbsuserid;
	}
    

	
}

