package com.wxservice.rop.test; 
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rop.utils.RopUtils;

public class UserServiceTest extends BaseServiceTest{
	
	
	@Test
	public  void testgetRanklist() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("method", "user.login");
        form.add("appKey", APP_KEY);
        form.add("v", "1.0");
        form.add("format", "json");
        form.add("locale", "zh_cn");
        form.add("usercode", "admin");
        form.add("passwd", "123");
        //对请求参数列表进行签名
        String sign = RopUtils.sign(form.toSingleValueMap(), APP_SECRET);
        //request定义了不需要签名.
        form.add("sign", sign);
        String response = restTemplate.postForObject(
        		SERVER_URL, form, String.class);
        System.out.print(response);
	}
} 
