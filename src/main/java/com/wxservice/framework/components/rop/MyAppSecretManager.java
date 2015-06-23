package com.wxservice.framework.components.rop;


import com.rop.security.AppSecretManager;

import java.util.HashMap;
import java.util.Map;


public class MyAppSecretManager implements AppSecretManager {

    private static Map<String, String> appKeySecretMap = new HashMap<String, String>();

    static {
        appKeySecretMap.put("wxservice", "qwertyuiopasdfghjkl1234567");
    }

    @Override
    public String getSecret(String appKey) {
        return appKeySecretMap.get(appKey);
    }

    @Override
    public boolean isValidAppKey(String appKey) {
        return getSecret(appKey) != null;
    }
}

