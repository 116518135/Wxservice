package com.wxservice.framework.generator.main;

import com.wxservice.framework.generator.config.Config;
import com.wxservice.framework.util.StringUtil;

public abstract class SimpleProcess implements IProcess {
	 public String action=null;
	 public String getTemplate(Config config){
	    	StringBuffer buf=new StringBuffer();
	    	//buf.append(config.getWorkpath());
	    	buf.append("/src/generator/resources/");
	    	buf.append(config.getTemplate());
	    	buf.append("/");
	    	buf.append(action);
	    	buf.append(".vm");
	    	debug("模板："+buf.toString());
	    	return buf.toString();
	    }
	    public void debug(String message){
	    	System.out.println("******:"+message);
	    }
	    
	    public String getAction(){
	    	return StringUtil.notNull(action);
	    }
	
}
