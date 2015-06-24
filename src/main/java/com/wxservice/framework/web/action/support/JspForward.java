package com.wxservice.framework.web.action.support;

import org.apache.struts.action.ActionForward;

import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.AbstractStrutsForward;
import com.wxservice.framework.web.action.ActionContext;

public class JspForward extends AbstractStrutsForward{
	   public JspForward (Object forward){
		   super(forward);
	   }
       /**
        * 导向struts Forward
        * @param context
        * @return  
        * @see com.wxservice.framework.web.action.IStrutsForward#findForward(com.wxservice.framework.web.action.ActionContext)
        */	
	   public ActionForward findForward(ActionContext context){
		   if(forward instanceof String){
			   return context.getMapping().findForward((String)forward);
		   }else{
			   SystemLogger.error("Forward类型应该为String,而不是:"+forward.toString());
			   return context.getMapping().findForward(SysFinal.STRUTS_SYSTEM_ERROR);
		   }
		
	   }
}
