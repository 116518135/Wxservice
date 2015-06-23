package com.wxservice.framework.web.action.support;

import org.apache.struts.action.ActionForward;

import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.web.action.AbstractStrutsForward;
import com.wxservice.framework.web.action.ActionContext;

public class TextForward extends AbstractStrutsForward{
	   public TextForward (Object forward){
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
			   if(forward!=null){
				   String text=(String)forward;
				   this.renderText(context.getResponse(), text);
			   }else{
			       this.renderText(context.getResponse(), "");
			   }
			   return null;
		   }catch(Exception e){
			   return context.getMapping().findForward(SysFinal.STRUTS_SYSTEM_ERROR);
		   }
		
		
	   }
}
