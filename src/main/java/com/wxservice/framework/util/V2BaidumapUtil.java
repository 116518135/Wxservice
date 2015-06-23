package com.wxservice.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

/**
 * googlemap通过对象获取地址 描述： 创建人：WWT
 */
public class V2BaidumapUtil {
	private static String output="json";
	private static String key="19d25773094f5bbdfd647eb02c15ed62";
	/** 
     * 根据经纬度返回地址
     * @param 经纬度
     * @return 返回地址数据, add 
     */
    public static String getCoordinate(String addr) { 
    	String add="";
        String url = "http://api.map.baidu.com/geocoder/v2/?location="+addr+"&pois=1&output="+output+"&ak="+key;
        
        URL baiduMapURL = null; 
        HttpURLConnection  httpsConn = null; 
        // 进行转码 
        try { 
            baiduMapURL = new URL(url); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } 
        StringBuffer sb = new StringBuffer();   

        try { 
            httpsConn = (HttpURLConnection )baiduMapURL.openConnection(); 
            InputStream inputStream = httpsConn.getInputStream(); 
            Reader reader = new InputStreamReader(inputStream, "UTF-8");  
            BufferedReader bufferedReader = new BufferedReader(reader);   
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {   
            	     sb.append(str);   
             }   
            reader.close();   
            httpsConn.disconnect();  
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        //无经纬度返回
        if(sb.length()<100){
        	add=null;
        }else{
        	 Gson gson = new Gson();
             V2BaiduMapJsonBean json = gson.fromJson(sb.toString(), V2BaiduMapJsonBean.class);         
             add=json.getResult().getFormatted_address();
        }
        return add;
    }
    
   
	public static void main(String[] args) {
		String str=getCoordinate("24.983424,118.322987");
		System.out.print("add:"+str);
	}

}
