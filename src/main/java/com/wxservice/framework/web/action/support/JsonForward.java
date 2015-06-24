package com.wxservice.framework.web.action.support;

import org.apache.struts.action.ActionForward;

import com.wxservice.framework.components.json.JSONTool;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.AbstractStrutsForward;
import com.wxservice.framework.web.action.ActionContext;

public class JsonForward extends AbstractStrutsForward{
	   public JsonForward (Object forward){
		   super(forward);
	   }
        /**
         * 导向struts forward
         * @param context
         * @return  
         * @see com.wxservice.framework.web.action.IStrutsForward#findForward(com.wxservice.framework.web.action.ActionContext)
         */
	   public ActionForward findForward(ActionContext context){
		   try{
			   String text=JSONTool.beanToJson(forward);
               SystemLogger.debug(text);
			   this.renderJson(context.getResponse(), text);
			   return null;
		   }catch(Exception e){
			   SystemLogger.error("Object["+forward.toString()+"]转换为JSONOBJECT时发生错误!"); 
			   return context.getMapping().findForward(SysFinal.STRUTS_SYSTEM_ERROR);
		   }
		
		
	   }
}
