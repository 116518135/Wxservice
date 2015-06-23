package com.wxservice.framework.components.rop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rop.security.ServiceAccessController;
import com.rop.session.Session;
import com.wxservice.framework.util.SystemLogger;


public class MyServiceAccessController implements ServiceAccessController {

    private static final Map<String, List<String>> aclMap = new HashMap<String, List<String>>();

    static {
//        ArrayList<String> serviceMethods = new ArrayList<String>();
//        serviceMethods.add("user.logon");
//        serviceMethods.add("user.logout");
//        serviceMethods.add("user.getSession");
//        aclMap.put("00003", serviceMethods);
    }

    @Override
    public boolean isAppGranted(String appKey, String method, String version) {
        if(aclMap.containsKey(appKey)){
            List<String> serviceMethods = aclMap.get(appKey);
            return serviceMethods.contains(method);
        }else{
            return true;
        }
    }

    @Override
    public boolean isUserGranted(Session session, String method, String version) {
    	SystemLogger.info("调用："+method+",版本："+version);
        return true;
    }
}

