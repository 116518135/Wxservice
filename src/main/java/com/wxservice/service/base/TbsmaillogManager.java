package com.wxservice.service.base;
import com.wxservice.database.po.base.Tbsmaillog;
import com.wxservice.database.po.regedit.Treguser;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.base.TbsmaillogForm;
/**
 * 
 * 描述：   Services
 * 创建人：Gererator
 */
public class TbsmaillogManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;
	public TbsmaillogManager(){

	}
	public Class getEntityClass() {
		return Tbsmaillog.class;
	}
	public String getTableKeyField() {
		return "tbsmaillog";
	}
	

	public void bindForm(BaseForm baseform, Object object) {
		if(object==null) return;
		Tbsmaillog po= (Tbsmaillog)object;
		TbsmaillogForm    form=(TbsmaillogForm)baseform;
		form.setTbsmaillog(po.getTbsmaillog());
				   form.setMail(po.getMail());
				   form.setStatus(po.getStatus());
				   form.setValidatecode(po.getValidatecode());
				   form.setLogdate(po.getLogdate());
				
	}
	
	public void bindEntity(BaseForm baseform, Object object) {
	
		Tbsmaillog po= (Tbsmaillog)object;
		TbsmaillogForm    form=(TbsmaillogForm)baseform;
		po.setTbsmaillog(form.getTbsmaillog());
				   po.setMail(form.getMail());
				   po.setStatus(form.getStatus());
				   po.setValidatecode(form.getValidatecode());
				   po.setLogdate(form.getLogdate());
				
	}
	
	
	/** 
     * 处理激活 
     * @param email 邮箱地址 
     * @param validateCode 验证码 
     */  
    public IStrutsForward activate(ActionContext context){  
       String code=context.getRequest().getParameter("code");
       String validatecode=context.getRequest().getParameter("validatecode");
       StringBuffer hql=new StringBuffer();
       hql.append(" from Tbsmaillog where code='").append(code).append("'");
       Tbsmaillog log=(Tbsmaillog)dao.get(hql.toString());
       if(log==null){
    	   context.getRequest().setAttribute("errorMessage", "该用户不存在或未申请邮箱绑定！");
    	   return forwardJsp("activate_failure");
       }
       if(!log.getValidatecode().equals(validatecode)){
    	   context.getRequest().setAttribute("errorMessage", "激活码不正确或者该链接已经过期！");
    	   return forwardJsp("activate_failure");
       }
       if(log.getStatus()==1){
    	   context.getRequest().setAttribute("errorMessage", "邮箱已激活，请登录！");
    	   return forwardJsp("activate_failure");
       }
       log.setStatus(1);
       dao.updateSave(log);
       hql.setLength(0);
       hql.append("from Treguser where code='").append(code).append("'");
       Treguser user=(Treguser)dao.get(hql.toString());
       user.setEmailflag(2);
       dao.updateSave(user);
       dao.evict(Treguser.class);
      
       return forwardJsp("activate_success");
      
    }

}


    