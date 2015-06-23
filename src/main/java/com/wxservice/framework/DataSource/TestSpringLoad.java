package com.wxservice.framework.DataSource;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class TestSpringLoad {
	public static void main(String[] arg){
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:ApplicationContext-*.xml");
		
//		DynamicLoadBean dynamicLoadBean = (DynamicLoadBean)ac.getBean("dynamicLoadBean");
//		dynamicLoadBean.loadBean("classpath:datasource.xml");
//		
//		Department vo = new Department();
//		vo.setDepartmentName("aaaa");
//		DepartmentBus dep = (DepartmentBus)ac.getBean("departmentBus");
//		
//		SpObserver.putSp("dataSource3");
//		dep.daoSupport.insert(vo);
//		log.debug("successful!");
	}


	
}
