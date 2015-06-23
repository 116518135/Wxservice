package com.wxservice.framework.components.json;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Writer;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JSONTool  extends JSONSerializer{
    /** * logger. */
    private static Log logger = LogFactory.getLog(JSONTool.class);

    /** * 工具类需要的保护构造方法. */
    protected JSONTool() {
    }

    /**
     * write.
     *
     * @param bean obj
     * @param writer 输出流
     * @param excludes 不转换的属性数组
     * @param datePattern date到string转换的模式
     * @throws Exception 写入数据可能出现异常
     */
    public static void write(Object bean, Writer writer,
        String[] excludes, String datePattern) throws Exception {
        JsonConfig jsonConfig = configJson(excludes, datePattern);
 
        JSON json = JSONSerializer.toJSON(bean, jsonConfig);
        System.out.println(json.toString());
        json.write(writer);
    }
    
    public static String beanToJson(Object bean) throws Exception{
    	JsonConfig jsonConfig = configJson(null,null);
    	JSON json=null;
    	if(bean instanceof Map){
    		json = JSONObjectExtend.fromObject(bean);
    	}else if( bean instanceof String){
    		return bean.toString();
    	}else {
    		json = JSONSerializer.toJSON(bean, jsonConfig);
    	}
        //
    	
        return json.toString();
    }

    /**
     * 配置json-lib需要的excludes和datePattern.
     *
     * @param excludes 不需要转换的属性数组
     * @param datePattern 日期转换模式
     * @return JsonConfig 根据excludes和dataPattern生成的jsonConfig，用于write
     */
    public static JsonConfig configJson(String[] excludes,
        String datePattern) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.registerJsonValueProcessor(Date.class,
            new DateJsonValueProcessor(datePattern));
      

        return jsonConfig;
    }

    /**
     * data={"id":"1"}用json的数据创建指定的pojo.
     *
     * @param <T> Object
     * @param data json字符串
     * @param clazz 需要转换成bean的具体类型
     * @param excludes 不需要转换的属性数组
     * @param datePattern 日期转换模式
     * @return T
     * @throws Exception java.lang.InstantiationException,
     *                   java.beans.IntrospectionException,
     *                   java.lang.IllegalAccessException
     */
    public static <T extends Object> T json2Bean(String data,
        Class<T> clazz, String[] excludes, String datePattern)
        throws Exception {
        // JsonUtils.configJson(excludes, datePattern);
        T entity = clazz.newInstance();

        return json2Bean(data, entity, excludes, datePattern);
    }

    /**
     * data={"id":"1"}用json里的数据，填充指定的pojo.
     *
     * @param <T> Object
     * @param data json字符串
     * @param entity 需要填充数据的bean
     * @param excludes 不需要转换的属性数组
     * @param datePattern 日期转换模式
     * @return T
     * @throws Exception java.lang.InstantiationException,
     *                   java.beans.IntrospectionException,
     *                   java.lang.IllegalAccessException
     */
    public static <T extends Object> T json2Bean(String data, T entity,
        String[] excludes, String datePattern) throws Exception {
        // JsonUtils.configJson(excludes, datePattern);
        JSONObject jsonObject = JSONObject.fromObject(data);

        return json2Bean(jsonObject, entity, excludes, datePattern);
    }

    /**
     * 根据Class生成entity，再把JSONObject中的数据填充进去.
     *
     * @param <T> Object
     * @param jsonObject json对象
     * @param clazz 需要转换成bean的具体类型
     * @param excludes 不需要转换的属性数组
     * @param datePattern 日期转换模式
     * @return T
     * @throws Exception java.lang.InstantiationException,
     *                   java.beans.IntrospectionException,
     *                   java.lang.IllegalAccessException
     */
    public static <T extends Object> T json2Bean(JSONObject jsonObject,
        Class<T> clazz, String[] excludes, String datePattern)
        throws Exception {
        // JsonUtils.configJson(excludes, datePattern);
        T entity = clazz.newInstance();

        return json2Bean(jsonObject, entity, excludes, datePattern);
    }

    /**
     * 把JSONObject中的数据填充到entity中.
     *
     * @param <T> Object
     * @param jsonObject json对象
     * @param entity 需要填充数据的node
     * @param excludes 不需要转换的属性数组
     * @param datePattern 日期转换模式
     * @return T
     * @throws Exception java.lang.InstantiationException,
     *                   java.beans.IntrospectionException,
     *                   java.lang.IllegalAccessException
     */
    public static <T extends Object> T json2Bean(JSONObject jsonObject,
        T entity, String[] excludes, String datePattern)
        throws Exception {
        // JsonUtils.configJson(excludes, datePattern);
        for (Object object : jsonObject.entrySet()) {
            Map.Entry entry = (Map.Entry) object;
            String propertyName = entry.getKey().toString();
            String propertyValue = entry.getValue().toString();

            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName,
                        entity.getClass());
                Class propertyType = propertyDescriptor.getPropertyType();

                Method writeMethod = propertyDescriptor.getWriteMethod();

                if (propertyType == String.class) {
                    writeMethod.invoke(entity, propertyValue);
                } else if (propertyType == Long.class) {
                    writeMethod.invoke(entity,
                        Long.parseLong(propertyValue));
                } else if (propertyType == Byte.class) {
                    writeMethod.invoke(entity,
                        Byte.parseByte(propertyValue));
                } else if (propertyType == Integer.class) {
                    writeMethod.invoke(entity,
                        Integer.parseInt(propertyValue));
                } else if (propertyType == Short.class) {
                    writeMethod.invoke(entity,
                        Short.parseShort(propertyValue));
                } else if (propertyType == Float.class) {
                    writeMethod.invoke(entity,
                        Float.parseFloat(propertyValue));
                } else if (propertyType == Double.class) {
                    writeMethod.invoke(entity,
                        Double.parseDouble(propertyValue));
                } else if (propertyType == Date.class) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
                    writeMethod.invoke(entity,
                        dateFormat.parse(propertyValue));
                }
            } catch (IntrospectionException ex) {
                logger.info(ex);

                continue;
            }
        }

        return entity;
    }

    /**
     * data=[{"id":"1"},{"id":2}]用json里的数据，创建pojo队列.
     *
     * @param <T> Object
     * @param data json字符串
     * @param clazz 需要转换成node的具体类型
     * @param excludes 不需要转换的属性数组
     * @param datePattern 日期转换模式
     * @return List
     * @throws Exception java.lang.InstantiationException,
     *                   java.beans.IntrospectionException,
     *                   java.lang.IllegalAccessException
     */
    public static <T extends Object> List<T> json2List(String data,
        Class<T> clazz, String[] excludes, String datePattern)
        throws Exception {
        // JsonUtils.configJson(excludes, datePattern);
        List<T> list = new ArrayList<T>();
        JSONArray jsonArray = JSONArray.fromObject(data);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            T node = json2Bean(jsonObject, clazz, excludes, datePattern);
            list.add(node);
        }

        return list;
    }
    
  
}
