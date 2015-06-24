package com.wxservice.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.util.SystemOutLogger;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import com.google.gson.Gson;

/**
 * googlemap通过对象获取经纬度 描述： 创建人：YPR
 */
public class BaidumapUtil {
	private static String output="json";
	private static String key="a888fff071f16a0fb6bce6f0fbc384cd";
	/** 
     * 根据地址返回经纬度 
     * @param addr 
     * @return 返回经纬度数据, latLng[0]经度,latLng[1]维度 
     */
    public static String[] getCoordinate(String addr) { 
    	String[] strArr=new String[2];
        String url = "http://api.map.baidu.com/geocoder?address="+addr+"&output="+output+"&key="+key;
        
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
        	 strArr[0]=null;	
             strArr[1]=null;
        }else{
        	 Gson gson = new Gson();
             BaiduMapJsonBean json = gson.fromJson(sb.toString(), BaiduMapJsonBean.class);         
         	 strArr[0]=json.getResult().getLocation().getLng();
             strArr[1]=json.getResult().getLocation().getLat();
        }
        return strArr;
    }
    
   
	public static void main(String[] args) {
		String[] str=getCoordinate("厦门");
		
		System.out.print("lat:"+str[0]+" lng:"+str[1]);
	}

}
