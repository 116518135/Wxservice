package com.wxservice.framework.report.core;

import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ReflectionUtils;

import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.base.ReportPrint;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.exception.ExportException;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.FileUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.form.BaseForm;



public class SimpleExport implements IExport {
	public static final String _tempPath = "/WEB-INF/tmp";
	public static final String _tempExtName = ".rptdata";
	IDao dao = null;
	ActionContext actionContext = null;
	private String getTmpPath(BaseForm form) {
		StringBuffer path = new StringBuffer("");
		path.append(form.getServlet().getServletContext().getRealPath(""))
				.append("/").append(_tempPath).append("/");
		File directory = new File(path.toString());
		if (!directory.exists()) {// 目录不存在,必须强制建立
			try {
				FileUtils.forceMkdir(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		return path.toString();
	}
	private String getConfigPath(BaseForm form,HttpServletRequest request) {
		StringBuffer path = new StringBuffer("");
		path.append(form.getServlet().getServletContext().getRealPath(""))
				.append("/").append(_tempPath).append("/").append(request.getSession().getId()).append("_data/");
		return path.toString();
	}
	private String getConfigDir(BaseForm form,HttpServletRequest request) {
		String path=this.getConfigPath(form, request);
		File directory = new File(path.toString());
		if (!directory.exists()) {// 目录不存在,必须强制建立
			try {
				FileUtils.forceMkdir(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		return path.toString();
	}
	
	public void deleteConfigDir(BaseForm form,HttpServletRequest request){
		String path=this.getConfigPath(form, request);
		File directory = new File(path.toString());
		if (directory.exists()) {// 目录存在,必须删除
			try {
				FileUtils.deleteDirectory(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		
	}
	

	private String getCacheName(HttpServletRequest request, BaseForm form,
			ReportConfig rc) {
		StringBuffer file = new StringBuffer("");
		file.append(getConfigDir(form,request));
		file.append(rc.getReport().getCode());
		file.append("_");
		file.append(_tempExtName);
		return file.toString();
	}
	


	
	private String getExcelPath(BaseForm form,HttpServletRequest request) {
		StringBuffer path = new StringBuffer("");
		path.append(form.getServlet().getServletContext().getRealPath(""))
				.append("/").append(_tempPath).append("/").append(request.getSession().getId()).append("_excel/");
		return path.toString();
	}

	public String getExcelDir(BaseForm form,HttpServletRequest request){
        String file=this.getExcelPath(form, request);
		File directory = new File(file.toString());
		if (!directory.exists()) {// 
			try {
				FileUtils.forceMkdir(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		return file.toString();
	}
	
	public void deleteExcelDir(BaseForm form,HttpServletRequest request){
	     String file=this.getExcelPath(form, request);
			File directory = new File(file.toString());
		if (directory.exists()) {
			try {
				FileUtils.deleteDirectory(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
	}
	
	public String getExcelName(ReportRequest ctx){
		StringBuffer file = new StringBuffer("");
		file.append(this.getExcelDir(ctx.getContext().getForm(),ctx.getContext().getRequest()));
		file.append(ctx.getIndex());
		file.append("_");
		file.append(ctx.getRc().getReport().getCode());
		file.append(".xls");
		ctx.setIndex(ctx.getIndex()+1);
		return file.toString();
	}

	public boolean saveRc(ReportPrint rc, ReportConfig config, BaseForm form,
			HttpServletRequest request) {
		boolean result = false;
		try {
			this.deleteConfigDir(form, request);
			String file = getCacheName(request, form, config);
			//
			FileUtil.saveObject(rc, file.toString());
			//this.saveObject(rc, file);
			result = true;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			ReflectionUtils.handleReflectionException(e);
		}
		return result;
	}

	public ReportPrint loadRc(ReportConfig config, BaseForm form,
			HttpServletRequest request) {
		Object obj = null;
		try {
			String file = getCacheName(request, form, config);
			obj = FileUtil.loadObject(file);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			ReflectionUtils.handleReflectionException(e);
		}
		return (ReportPrint) obj;
	}

	
	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public ActionContext getActionContext() {
		return actionContext;
	}

	public void setActionContext(ActionContext actionContext) {
		this.actionContext = actionContext;
	}

	@Override
	public Object cache(ReportRequest ctx) throws ExportException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object export(ReportRequest ctx) throws ExportException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void export(ReportRequest ctx, String fileName)
			throws ExportException {
		// TODO Auto-generated method stub
		
	}
    
}
