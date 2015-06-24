package com.wxservice.rop.test;

import com.rop.client.CompositeResponse;
import com.rop.client.DefaultRopClient;
import com.wxservice.rop.request.LoginRequest;
import com.wxservice.rop.response.LoginResponse;

public class BaseServiceTest {
    public static final String SERVER_URL = "http://localhost:8080/router";
	public static final String APP_KEY = "wxservice";
	public static final String APP_SECRET = "qwertyuiopasdfghjkl1234567";
	public static final String TEST_USER = "123";
	public static final String TEST_PASSWORD = "321";
	

    public static LoginResponse login() {
       LoginRequest request = new LoginRequest();
        request.setUsercode(TEST_USER);
        request.setPasswd(TEST_PASSWORD);
        DefaultRopClient client = new DefaultRopClient(SERVER_URL, APP_KEY, APP_SECRET);
        CompositeResponse response =(CompositeResponse) client.buildClientRequest().get(request, LoginResponse.class, "user.login", "1.0");
        LoginResponse result=(LoginResponse)response.getSuccessResponse();
        return result;
    }

}
