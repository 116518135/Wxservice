package com.wxservice.framework.util;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MapUtil {
	public String getMapKeyFromValue(Map<String,String> map, String value) {   
        Set set = map.keySet();   
        Iterator it = set.iterator();   
        while (it.hasNext()) {   
            String key =(String)it.next();   
            if (value.equals(map.get(key))) {   
                return key;   
            }   
        }   
        throw new NullPointerException("没有对应的Key值");   
    }   

    
	public static String getString(Map<Object,Object> map, String key,boolean nullOk){
		Object o=map.get(key);
		if (o != null) {
			return o.toString();
		} else {
			if(nullOk){
				return null;
			}else{
				return "";
			}
		}
	}

	public static Date getDate(Map<Object,Object> map, String key,boolean nullOk,String pattern){
		Object o=map.get(key);
		if (o != null) {
	        Date d = (Date) DateUtil.parse(o.toString(), pattern);
	        return d;
		} else {
			if(nullOk){
				return null;
			}else{
				return new Date();
			}
		}
	}	
	
	public static Integer getInteger(Map<Object,Object> map, String key,boolean nullOk){
		Object o=map.get(key);
		if (o != null) {
			if(o instanceof Number){
				return ((Number)o).intValue();
			}else{
				return MathUtil.parsetInt(o.toString(),0);
			}
			
		} else {
			if(nullOk){
				return null;
			}else{
				return 0;
			}
		}
	}	
	
	/**
	 * 将字符串转换成实数
	 * @param map
	 * @param key
	 * @param nullOk
	 * @return
	 */
	public static Number getNumber(Map<Object,Object> map, String key,boolean nullOk){
		Object o=map.get(key);
		if (o != null) {
			if(o instanceof Number){
				return (Number)o;
			}else{
				return MathUtil.convert(o);
			}
			
		} else {
			if(nullOk){
				return null;
			}else{
				return 0;
			}
		}
	}

   
	

}
