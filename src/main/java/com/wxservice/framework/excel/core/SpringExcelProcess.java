package com.wxservice.framework.excel.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.RequestUtils;

import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.excel.ExcelContext;
import com.wxservice.framework.util.SystemLogger;

public class SpringExcelProcess extends   SimpleExcelProcess {
	public static Map classcache = new HashMap();
	public List execute(ExcelContext context) throws Exception {
		List errorList = new ArrayList();
		try {
			Operators os = new Operators();
			for (int i = 0; i < context.getResults().size(); i++) {
				Map map = (Map) context.getResults().get(i);
				String classname=context.getMaster().getSpringname();
				Object bean = applicationInstance(classname);
				BeanUtils.populate(bean, map);
				os.addAddObject(bean);
			}
			dao.execute(os);
		} catch (Exception e) {

			Map errorMap = new HashMap();
			errorMap.put("type", "数据错误");
			errorMap.put("rows", "");
			errorMap.put("keyvalue", "");
			errorMap.put("message", "执行Hibernat批量保存的时候发生错误");
			errorList.add(errorMap);
			SystemLogger.error(e.getMessage(),e);
		}
		return errorList;
	}
	public  Class applicationClass(String className) {

		try {
			Class cls = (Class) classcache.get(className);
			if (cls == null) {
				ClassLoader classLoader = Thread.currentThread()
						.getContextClassLoader();
				if (classLoader == null) {
					classLoader = RequestUtils.class.getClassLoader();
				}
				cls = classLoader.loadClass(className);
				classcache.put(className, cls);
			}
			return cls;
		} catch (Exception e) {
			SystemLogger.error("类名没有找到:" + className, e);
		}
		return null;
	}

	public  Object applicationInstance(String className)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException {
		return (applicationClass(className).newInstance());

	}
}
