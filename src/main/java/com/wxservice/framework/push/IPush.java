package com.wxservice.framework.push;
import java.util.Map;
public interface IPush {
     public String push(Map<String,String> params);
     public String systempush(Map<String,String> params);
}
