package com.wxservice.framework.components.right;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.service.NameManager;

public class BaseRightService {
	public final static String RIGHT_KEY = "SYSTEM-RIGHT";
	public final static String RIGHT_OBJECT = "SYSTEM-RIGHT-ORBJECT";
	public static final int FBROWSE = 1;// 浏览
	public static final int FEDIT = 2;// 编辑
	public static final int FBROWSEAMT = 3;// 浏览金额
	public static final int FAUDIT = 4;// 审核
	public static final int FUNAUDIT = 5;// 反审核
	public static final int FSTOP = 6;// 终止
	public static final int FPERFORM = 7;// 执行
	public static final int FUNPERFORM = 8;// 反执行
	public static final int FCHECK = 9;// 校验
	public static final int FUNCHECK = 10;// 反校检
	public static final int FACCOUNT = 11;// 入帐
	public static final int FUNACCOUNT = 12;// 反入帐
	public static final int FFINISH = 13;// 完成
	public static final int FPRINT = 14;// 打印   
	
	public static final String REPORT_EXPORT_RIGHT_KEY="REPORT_EXPORT_RIGHT_KEY";
	//权限位数的表未意义 1:表示该菜单需要这个权限 2表示该菜单不需要这个权限
	// 基础信息 11222222222221
	// 订单     11111112222221  审核 反审核 执行，终止
	//通知单    11111212222221  审核 反审核 执行
	//出入库单  11122221111111  反执行 校验 反校验 入帐 反入帐 完成
	public static final int cmpprop_ZGS = 0;// 总公司
	public static final int cmpprop_QD =  1;//  渠道
	public static final int cmpprop_GYS = 2;// 供应商
	public static final int cmpprop_JMD = 3;// 加盟店
	public static final int cmpprop_ZYD = 4;// 直营店
	public static final int cmpprop_WLS = 5;// 物料供应商
	public static final int cmpprop_SCS = 6;// 成品生产商
	//组织权限位数的表示意义 0:不可见 1:只能读 2具有编辑权限 3:只有超级系统管理员才有编辑权限
	//默认为： 2200000
	public static final String right_nobrowse = "0"; // 不可见
	public static final String right_onlybrowse = "1"; // 只能读
	public static final String right_canedit = "2"; // 具有编辑权限
	public static final String right_onlysystemedit = "3";// 只有超级系统管理员才有编辑权限
	
	public IDao dao=null;
	public NameManager nameManager=null;

	protected HashMap<String, Method> service_methods = new HashMap<String, Method>();

	protected Class service_types[] = { HttpServletRequest.class,
			BaseForm.class };
    /**
     * 创建权限Service的一个method
     * @param service
     * @param methodStr
     * @return
     */
	protected Method createMethod(Object service, String methodStr) {
		try {
			synchronized (service_methods) {
				String key = service.getClass().getName() + "." + methodStr;
				Method method = (Method) service_methods.get(key);
				if (method == null) {
					method = service.getClass().getMethod(methodStr,
							service_types);
					service_methods.put(key, method);
				}
				return (method);
			}
		} catch (Exception e) {
			SystemLogger.error("Method传入有错误,RightService中不存在[" + methodStr
					+ "]", e);
			return null;
		}
	}
    /**
     * 调用权限Service的method方法
     * @param request
     * @param form
     * @param method
     * @return
     */
	public boolean invokeMethod(HttpServletRequest request, BaseForm form,
			String method) {
		try {
			Method m = createMethod(this, method);
			Object args[] = { request, form };
			Boolean result = (Boolean) m.invoke(this, args);
			return result.booleanValue();
		} catch (Exception e) {
			SystemLogger.error("调用权限Service的"+method+"方法发生错误",e);
		}
		return false;
	}

	public  boolean testJudge(HttpServletRequest request, BaseForm form)
			throws Exception {
		System.out.println(123);
		return true;
	}
	
	public static void main(String[] args){
		BaseRightService right=new BaseRightService();
		right.invokeMethod(null,null,"testJudge");
	}
	public IDao getDao() {
		return dao;
	}
	public void setDao(IDao dao) {
		this.dao = dao;
	}
	public NameManager getNameManager() {
		return nameManager;
	}
	public void setNameManager(NameManager nameManager) {
		this.nameManager = nameManager;
	}

}
