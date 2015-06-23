package com.wxservice.rop.request;
import com.rop.AbstractRopRequest;
public class LoginRequest extends AbstractRopRequest{
	private String usercode;
    private String passwd;
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

    
	

	
}

