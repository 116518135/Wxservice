package com.wxservice.framework.util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class BeanContext  {
    public static final String springFilePath = "classpath*:spring/*.xml";
     static ApplicationContext ctx = null;
    public  static void init() {
        try {
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
