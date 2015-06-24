package com.wxservice.framework.excel.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxservice.database.po.base.Tbsuser;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.excel.ExcelContext;
import com.wxservice.framework.util.MapUtil;
import com.wxservice.framework.util.Md5;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.session.ClientSession;

public class UserExcelProcess extends SimpleExcelProcess {
	
	public List execute(ExcelContext context) throws Exception {
		List errorList = new ArrayList();
		try {
			//对员工模板处理
			List results=context.getResults();
			//判断Excel中是否有错误,有错则全不插
			lookForError(errorList,results);
			if(errorList.size()!=0) return errorList;
			ClientSession client = WebUtil.getClientSession(context.getRequest());
			for (int i = 0; i < results.size(); i++) {
				Map map = (Map) results.get(i);
				String code = MapUtil.getString(map, "code", false);
				String name = MapUtil.getString(map, "name", false);
				Integer stopflag = MapUtil.getInteger(map, "stopflag", true);
				String  passwd = MapUtil.getString(map, "passwd", false);
				Operators os = new Operators();
				Tbsuser tbsuser = new Tbsuser();
				tbsuser.setName(name);
				tbsuser.setStopflag(stopflag);
				if(StringUtil.isNotBlank(passwd)){
					tbsuser.setPasswd(Md5.md5(passwd));
				}else{
					tbsuser.setPasswd(null);
				}				
				os.addAddObject(tbsuser);
				dao.execute(os);
				
			}
		} catch (Exception e) {
			Map errorMap = new HashMap();
			createErrorMap(errorMap,"数据错误","","","执行操作员导入保存的时候发生错误");
			errorList.add(errorMap);
			SystemLogger.error(e.getMessage(), e);
		}
		
		return errorList;
	}

	

	private void createErrorMap(Map errorMap, String type, Object rows,
			String keyvalue, String message) {
		errorMap.put("type", type);
		errorMap.put("rows", rows);
		errorMap.put("keyvalue", keyvalue);
		errorMap.put("message", message);
		
	}

	//组织erro的map
	private void lookForError(List errorList, List results) {
		for (int i = 0; i < results.size(); i++) {
			Map map = (Map) results.get(i);
			String code =new String(MapUtil.getString(map, "code", false));
			String createtbscmpcode = MapUtil.getString(map, "createtbscmpcode", true);
			if(check(code)){
				Map errorMap = new HashMap();
				createErrorMap(errorMap,"数据错误",map.get("rowid"),code,"员工编号重复");
				errorList.add(errorMap);
			}
			
			
		}
		
	}


	private boolean check(String code) {
		Boolean flag=false;
		StringBuffer sql =new StringBuffer(" from Tbsuser as a where a.code='");
		sql.append(code).append("'");
		Tbsuser entity =(Tbsuser)dao.get(sql.toString());
		if(null!=entity) flag=true;
        return flag;
	}
}
