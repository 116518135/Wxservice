package com.wxservice.rop.service;

import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;
import com.rop.response.BusinessServiceErrorResponse;
import com.wxservice.database.po.base.Tbsuser;
import com.wxservice.framework.util.Md5;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.rop.request.LoginRequest;
import com.wxservice.rop.response.LoginResponse;

@ServiceMethodBean(version = "1.0") 
public class UserService {
	@ServiceMethod(method = "user.login", version = "1.0", needInSession = NeedInSessionType.NO,ignoreSign = IgnoreSignType.YES)
	public Object login(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		String usercode=request.getUsercode();
		String passwd=request.getPasswd();
		try {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tbsuser as a where a.code='").append(usercode).append("'");
		Tbsuser po = (Tbsuser) WebUtil.getDao().get(hql.toString());
		if(null==po){
			return new BusinessServiceErrorResponse(request
					.getRopRequestContext().getMethod(),
					"USER_CODE_NOT_EXIST", request.getRopRequestContext()
							.getLocale(), usercode);
		}
		if(!po.getPasswd().equals(Md5.md5(passwd))){
			return new BusinessServiceErrorResponse(request
					.getRopRequestContext().getMethod(),
					"USER_PASSWORD_ERROR", request.getRopRequestContext()
							.getLocale(), usercode);

		}
		response.setTbsuserid(po.getTbsuserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
