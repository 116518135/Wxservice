package com.wxservice.framework.web.action.support;

import org.apache.struts.action.ActionForward;

import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.AbstractStrutsForward;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;

public class MethodForward extends AbstractStrutsForward{
	   public MethodForward (Object forward){
		   super(forward);
	   }
	   /**
	    * 不返回，继续调用Manager中的Method的方法
	    */
	   public ActionForward findForward(ActionContext context){
		   if(forward instanceof String){
			   String method=(String)forward;
			   context.getForm().setMethod(method);
			   
			   IStrutsForward newforward=context.getAction().dispatch(context);
			   return newforward.findForward(context);
		   }else{
			   SystemLogger.error("Forward类型应该为String,而不是:"+forward.toString());
			   return context.getMapping().findForward(SysFinal.STRUTS_SYSTEM_ERROR);
		   }
		
	   }
}
