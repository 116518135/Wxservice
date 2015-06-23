package com.wxservice.rop.util;

import java.util.UUID;

import com.rop.RopRequest;
import com.wxservice.framework.components.rop.MySession;

public class ServiceUtil {
	

	public static String createSessionid() {
		String uuid = UUID.randomUUID().toString();// 获取随机唯一标识符
		// 去掉标识符中的"-"
		uuid = uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
		return uuid;
	}

	public static MySession getSession(RopRequest request) {
		MySession session = (MySession) request.getRopRequestContext()
				.getSession();
		return session;
	}

	
	
	private static double EARTH_RADIUS = 6378.137; 

	
    private static double rad(double d) { 

        return d * Math.PI / 180.0; 

    } 

 

    public static double getDistance(double lat1, double lng1, double lat2, 

            double lng2) { 

        double radLat1 = rad(lat1); 

        double radLat2 = rad(lat2); 

        double a = radLat1 - radLat2; 

        double b = rad(lng1) - rad(lng2); 

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) 

                + Math.cos(radLat1) * Math.cos(radLat2) 

                * Math.pow(Math.sin(b / 2), 2))); 

        s = s * EARTH_RADIUS; 

        s = Math.round(s * 10000) / 10000; 

        return s; 

    } 
	public static void main(String[] args) {
		System.out.println(ServiceUtil.createSessionid());
	}
}
