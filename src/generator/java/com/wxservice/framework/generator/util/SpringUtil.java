package com.wxservice.framework.generator.util;
import java.io.File;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringUtil {
	
	 public static final String springFilePath = "/src/generator/resources/spring.xml";
	 public static final String log4jPath = "src/generator/resources/Log4j.properties";
	    

     static ApplicationContext ctx = null;
    public  static void init() {
        try {
            File file = new File(log4jPath);
            PropertyConfigurator.configure(file.getAbsolutePath());
            ctx = new FileSystemXmlApplicationContext(springFilePath);
        } catch (Exception e) {
           e.printStackTrace(System.out); 
        }

    }

   public static Object getBean(String name){
       if(ctx==null){
           init();
       }
       return ctx.getBean(name);
   }
  
 

}
