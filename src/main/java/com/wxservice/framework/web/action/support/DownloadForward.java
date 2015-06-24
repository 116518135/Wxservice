package com.wxservice.framework.web.action.support;


import java.net.URLEncoder;

import org.apache.struts.action.ActionForward;

import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.AbstractStrutsForward;
import com.wxservice.framework.web.action.ActionContext;


public class DownloadForward extends AbstractStrutsForward{
	   public DownloadForward (Object forward){
		   super(forward);
	   }
	   /**
	    * 设置下载的时候保存文件的名称,在业务类里面设用
	    * @param filedisplay
	    * @param context
	    * @throws Exception
	    */
	   public void setDownloadFile(String filedisplay,ActionContext context) throws Exception{
		   String filenamedisplay = URLEncoder.encode(filedisplay,"UTF-8");
		   context.getResponse().addHeader("Content-Disposition","attachment;filename=" + filenamedisplay);
	   }
	   /**
	    * 转向strutsforward
	    * @param context
	    * @return  
	    * @see com.wxservice.framework.web.action.IStrutsForward#findForward(com.wxservice.framework.web.action.ActionContext)
	    */
	   public ActionForward findForward(ActionContext context){
		   if(forward instanceof byte[]){
			   byte[] bytes=(byte[])forward;
			   this.renderDownload(context.getResponse(), bytes);
			   return null;
		   }else{
			   SystemLogger.error("Forward类型应该为byte[],而不是:"+forward.toString());
			   return context.getMapping().findForward(SysFinal.STRUTS_SESSION_ERROR);
		   }
		
	   }
}
