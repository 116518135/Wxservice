package com.wxservice.framework.report;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.report.Trpreport;
import com.wxservice.framework.components.online.UserInfoTool;
import com.wxservice.framework.engine.BaseEngine;
import com.wxservice.framework.engine.support.SysConfig;
import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.ConditionProcess;
import com.wxservice.framework.report.core.IExport;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.DateUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.action.support.DownloadForward;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.web.form.report.ReportForm;

public class ReportManager extends BaseEngine {
	ConditionProcess conditionProcess = null;

	public ReportManager() {
		super();
	}

	public void createSession(ActionContext context) {
		ClientSession client = null;
		ReportForm form = (ReportForm) context.getForm();
		if (client == null && checkProxyFlag()&&StringUtil.isNotBlank(form.getServer_url())) {
			client = form.getClient();
			HttpSession session = context.getRequest().getSession(true);
			client.setSessionid(session.getId());
			session.setAttribute(SysFinal.CLIENT_SESSION, client);
			UserInfoTool.addOnlineUser(client);
		}
	}

	public boolean checkProxyFlag() {
		if (serviceConfig.getBooleanConfig("sws.report.proxyflag", true)) {
			return true;
		}
		return false;
	}

	// 进入工具页面
	public IStrutsForward condition(ActionContext context) {
		processCondition(context);
		return this.forwardJsp("condition");
	}

	public void processCondition(ActionContext context) {
		token.saveToken(context.getRequest());// 生成令牌
		ReportForm form = (ReportForm) context.getForm();
		try {
			Trpreport rp = ReportUtil.getReport(dao, form.getReportid());
			if (checkProxyFlag()) {
				ClientSession client = WebUtil.getClientSession(context
						.getRequest());
				form.setClient(client);
				String report_server = SysConfig
						.getStringConfig("sws.report.server");
				if (StringUtil.isNotBlank(report_server)) {
					form.setServer_url(report_server);
				}
				if (StringUtil.isNotBlank(rp.getServerurl())) {
					form.setServer_url(rp.getServerurl());
				}
			}

			ReportConfig rc = null;
			if (!ReportUtil.existsReportConfig(rp.getCode(), form)) {// 判断编译后的文件存不存在
				rc = ReportUtil.createReportConfig(dao, rp);// 重新编译,加载
				ReportUtil.saveReportConfig(rc, form);
			} else {
				rc = ReportUtil.loader(rp.getCode(), form);
			}
			if (StringUtils.isNotBlank(rc.getReport().getProcessclass())) {
				form.setService(rc.getReport().getProcessclass());
			}
			String condition = conditionProcess.toHtml(rc, context);
			form.setCondition(condition);
		} catch (Exception e) {
			SystemLogger.error("进入报表查询页面发生错误", e);
		}
	}

	// 进入工具页面
	public IStrutsForward debugcondition(ActionContext context) {
		processCondition(context);
		return this.forwardJsp("debugcondition");
	}

	public IStrutsForward xsl(ActionContext context) {
		ReportForm form = (ReportForm) context.getForm();
		ReportRequest rr = new ReportRequest();
		try {
			this.createSession(context);
			initQuery(context, form, rr);
			Trpreport rp = rr.getRc().getReport();
			if (!this.xslEx(context, rr)) {
				return this.forwardJsp("reporterror");
			}
			IExport export = this.getXslExport(form, context);
			byte[] bytes = (byte[]) export.export(rr);
			IStrutsForward forward = null;
			if (rr.getRarFlag() == 1) {
				forward = new DownloadForward(bytes);
				((DownloadForward) forward).setDownloadFile("_" + rp.getName()
						+ ".rar", context);
			} else {
				// forward = new ExcelForward(bytes);
				forward = new DownloadForward(bytes);
				((DownloadForward) forward).setDownloadFile("_" + rp.getName()
						+ "/" + rr.getVars().get("$billno") + ".xls", context);
			}
			rr.release();
			rr = null;
			return forward;

		} catch (Exception e) {
			return printErrorMsg("查询报表发生错误!", e, rr, form);
		}

	}

	

	public IStrutsForward printErrorMsg(String messgae, Exception e,
			ReportRequest rr, ReportForm form) {
		StringBuffer msg = new StringBuffer();
		msg.append(messgae);
		msg.append("[报表编号为:");
		if (rr != null && rr.getRc() != null && rr.getRc().getReport() != null) {
			msg.append(rr.getRc().getReport().getCode());
		}
		msg.append("]");
		msg.append(e.getMessage());
		SystemLogger.error(msg.toString(), e);
		form.setHtmlContent(msg.toString());// 将错误系统打印前台
		return this.forwardJsp("error");
	}

	public IStrutsForward cacheXsl(ActionContext context) {
		token.saveToken(context.getRequest());// 生成令牌
		ReportForm form = (ReportForm) context.getForm();
		ReportRequest rr = new ReportRequest();
		try {

			initQuery(context, form, rr);
			Trpreport rp = rr.getRc().getReport();
			IExport export = this.getXslExport(form, context);
			byte[] bytes = (byte[]) export.cache(rr);
			IStrutsForward forward = null;
			if (rr.getRarFlag() == 1) {
				forward = new DownloadForward(bytes);
				((DownloadForward) forward).setDownloadFile("_" + rp.getName()
						+ ".rar", context);
			} else {
				// forward = new ExcelForward(bytes);
				forward = new DownloadForward(bytes);
				((DownloadForward) forward).setDownloadFile("_" + rp.getName()
						+ ".xls", context);
			}

			return forward;

		} catch (Exception e) {
			return printErrorMsg("查询报表发生错误!", e, rr, form);

		}

	}

	protected IExport getXslExport(ReportForm form, ActionContext context)
			throws IOException {

		IExport o = (IExport) WebUtil.getBean("xslExport", context.getForm());
		return o;
	}

	public IStrutsForward forword(String processor) {
		if (ReportUtil.display_dcube.equals(processor)) {
			return this.forwardJsp("dcube");
		}
		if (ReportUtil.display_dhskudcube.equals(processor)) {
			return this.forwardJsp("dhskudcube");
		} else {
			return this.forwardJsp("html");
		}
	}

	protected IExport getExport(ReportRequest rr, ActionContext context)
			throws IOException {
		IExport o = null;
		String processor = rr.getRc().getReport().getDispprocessor();
		if (ReportUtil.display_dcube.equals(processor)
				|| ReportUtil.display_dhskudcube.equals(processor)) {
			o = (IExport) WebUtil.getBean("dcubeExport", context.getForm());
		} else {
			o = (IExport) WebUtil.getBean("htmlExport", context.getForm());
		}
		return o;
	}

	public IStrutsForward html(ActionContext context) {
		ReportForm form = (ReportForm) context.getForm();
		ReportRequest rr = new ReportRequest();
		try {
			this.createSession(context);

			initQuery(context, form, rr);
			if (!this.htmlEx(context, rr)) {
				return this.forwardJsp("reporterror");
			}
			IExport export = this.getExport(rr, context);
			String content = (String) export.export(rr);
			form.setHtmlContent(content);
			form.setModuleTitle(rr.getRc().getReport().getName());
			String displayprocessor = rr.getRc().getReport().getDispprocessor();
			rr.release();
			rr = null;
			return forword(displayprocessor);

		} catch (Exception e) {
			return printErrorMsg("查询报表发生错误!", e, rr, form);
		}

	}

	public IStrutsForward debughtml(ActionContext context) {
		ReportForm form = (ReportForm) context.getForm();
		ReportRequest rr = new ReportRequest();
		try {
			this.createSession(context);

			initQuery(context, form, rr);
			if (!this.htmlEx(context, rr)) {
				return this.forwardJsp("reporterror");
			}
			IExport export = this.getExport(rr, context);
			String content = (String) export.export(rr);
			if (rr.getErrormap().get("sqlerror") != null) {
				String sqlerror = rr.getErrormap().get("sqlerror").toString();
				sqlerror = sqlerror.replaceAll(",", ",<br>");
				form.setHtmlContent(sqlerror);//
				form.setModuleTitle(rr.getRc().getReport().getName());
				return this.forwardJsp("sqlerror");
			}
			form.setHtmlContent(content);
			form.setModuleTitle(rr.getRc().getReport().getName());
			String displayprocessor = rr.getRc().getReport().getDispprocessor();
			rr.release();
			rr = null;
			return forword(displayprocessor);

		} catch (Exception e) {
			return printErrorMsg("查询报表发生错误!", e, rr, form);
		}

	}

	public IStrutsForward cacheHtml(ActionContext context) {
		token.saveToken(context.getRequest());// 生成令牌
		ReportForm form = (ReportForm) context.getForm();
		ReportRequest rr = new ReportRequest();
		try {
			initQuery(context, form, rr);
			IExport export = this.getExport(rr, context);
			String content = (String) export.cache(rr);
			form.setHtmlContent(content);
			form.setModuleTitle(rr.getRc().getReport().getName());
			String displayprocessor = rr.getRc().getReport().getDispprocessor();
			rr.release();
			rr = null;
			return forword(displayprocessor);
		} catch (Exception e) {
			return printErrorMsg("查询报表发生错误!", e, rr, form);
		}
	}

	public void initQuery(ActionContext context, ReportForm form,
			ReportRequest rr) {
		ReportConfig rc = ReportUtil.loader(form.getReportid(), form);
		rr.setContext(context);
		rr.setRc(rc);
		rr.setDao(dao);
		setPublicVariable(rr, rc);
	}

	protected void setPublicVariable(ReportRequest rr, ReportConfig rc) {
		rr.setAttribute("$title", rc.getReport().getName());
		ClientSession client = WebUtil.getClientSession(rr.getContext()
				.getRequest());
		if(client.getCmpid()!=null)
		rr.setAttribute("$cmpid", client.getCmpid().toString());
		rr.setAttribute("$cmpname", client.getCmpname());
		rr.setAttribute("$username", client.getUsername());
		rr.setAttribute("$usercode", client.getUsercode());
		rr.setAttribute("$today", DateUtil.formatDetailDate(new Date()));
	}

	public ConditionProcess getConditionProcess() {
		return conditionProcess;
	}

	public void setConditionProcess(ConditionProcess conditionProcess) {
		this.conditionProcess = conditionProcess;
	}

	public boolean htmlEx(ActionContext context, ReportRequest rr) {
		return true;
	}

	public boolean xslEx(ActionContext context, ReportRequest rr) {
		return true;
	}

}