package com.wxservice.framework.engine.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.wxservice.framework.util.MapUtil;
import com.wxservice.framework.util.StringUtil;


public class SysConfig {
	public static List<Class> pojolist=new ArrayList<Class>();
	public static Properties props = new Properties();
	public static String software_version="";
	// 删除前需要检查的脚本
	public Map scriptBeforeDelete = null;

	/**
	 * 返回 props 的值
	 * 
	 * @return props
	 */

	public static Properties getProps() {
		return props;
	}
	/**
	 * 取ucard.property中的整数值
	 * @param key
	 * @return
	 */
    public static  int getIntConfig(String key){
    	return MapUtil.getInteger(props, key, false);
    }
    /**
     * 取ucard.property中的字符串
     * @param key
     * @return
     */
    public static  String getStringConfig(String key){
    	return MapUtil.getString(props, key, false);
    }
    /**
     * 取ucard.property中的boolean值
     * @param key
     * @param dft
     * @return
     */
    public static  boolean getBooleanConfig(String key,boolean dft){
    	String temp=getStringConfig(key);
    	if ("true".equals(temp) || "on".equals(temp)) {
			return true;
		} else if ("false".equals(temp) || "off".equals(temp)) {
			return false;
		} else {
			return dft;
		}
    }
	/**
	 * 设置 props 的值
	 * 
	 * @param props
	 */

	public static void setProps(Properties props) {
		SysConfig.props = props;
	}

	public Map getScriptBeforeDelete() {
		return scriptBeforeDelete;

	}

	public void setScriptBeforeDelete(Map scriptBeforeDelete) {
		this.scriptBeforeDelete = scriptBeforeDelete;
	}
	public static String getSoftware_version() {
		return software_version;
	}
	public  void setSoftware_version(String software_version) {
		SysConfig.software_version = software_version;
	}
	

}
