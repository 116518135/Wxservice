package com.wxservice.service.report;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.report.Trpreport;
import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportdatasource;
import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.database.po.report.Trpreportplugin;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.BaseEngine;
import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.FileUtil;
import com.wxservice.framework.util.MapUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WxserviceGradeup;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.web.form.report.ReportToolForm;

public class ReportToolManager extends BaseEngine {
	public static int SQLLEN = 3600;
	public static int ContentFieldCount = 20;
	public static String ContentField = "content";

	public ReportToolManager() {
		super();
	}

	// 进入工具页面
	public IStrutsForward list(ActionContext context) {
		String   deployServer=(String)serviceConfig.getProps().get("sws.reporttool.deployServer");
		token.saveToken(context.getRequest());// 生成令牌
		ReportToolForm form = (ReportToolForm) context.getForm();
		form.setOverBuild("1");
		form.setOverDeploy("1");
		form.setOverLoad("1");
		form.setDeployServer(deployServer);
		return this.forwardJsp("list");
	}

	/**
	 * 发布报表
	 * 
	 * @param context
	 * @return
	 */
	public IStrutsForward upload(ActionContext context) {
		try {
			ReportToolForm form = (ReportToolForm) context.getForm();
			Object o = FileUtil.loadObject(form.getReport().getInputStream());
			Operators os = new Operators();
			if (o instanceof ReportConfig) {
				ReportConfig rc = (ReportConfig) o;
				processReportConfig(form, rc, os);
			}else if( o instanceof ArrayList){
				List list=(List)o;
				for(Object po:list){
					os.addAddObject(po);
				}
			}

			dao.execute(os);
		} catch (Exception e) {
			SystemLogger.error("上传文件发布报表错误", e);
			processFailure(context, e, true);
			return this.forwardJsp("list");
		}
		processSuccess(context, true);
		return this.forwardJsp("list");
	}

	private void processReportConfig(ReportToolForm form, ReportConfig rc,
			Operators os) throws Exception {

		if ("1".equals(form.getOverDeploy())) {// 在数据库中删除原来的报表
			deleteReport(os, rc.getReport().getCode());
		}
		// 导进数据库
				config2Db(rc, os);
		// 生成文件
		ReportUtil.saveReportConfig(rc, form);
		

	}

	private void config2Db(ReportConfig rc, Operators os) throws Exception {
		Integer reportid = (Integer) dao.addSave(rc.getReport());
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Object o : rc.getPlugin()) {
			Trpreportplugin po = (Trpreportplugin) o;
			po.setTrpreportid(reportid);
			String key = po.getTrpreportpluginid().toString();
			Integer trpreportpluginid = (Integer)dao.addSave(po);
			// os.addAddObject(po);
			map.put(key, trpreportpluginid);
		}
		for (Object o : rc.getCondition()) {
			Trpreportcondition po = (Trpreportcondition) o;
			po.setTrpreportid(reportid);
			Integer temp = map.get(po.getTrpreportpluginid().toString());
			if (temp != null) {
				po.setTrpreportpluginid(temp);
			}
			os.addAddObject(po);
		}
		for (Object o : rc.getDatasource()) {
			Trpreportdatasource po = (Trpreportdatasource) o;
			po.setTrpreportid(reportid);

			os.addAddObject(po);
		}
		for (Object o : rc.getHeader()) {
			Trpreportdtl po = (Trpreportdtl) o;
			po.setTrpreportid(reportid);
			Integer temp = map.get(po.getTrpreportpluginid().toString());
			if (temp != null) {
				po.setTrpreportpluginid(temp);
			}
			os.addAddObject(po);
		}
		for (Object o : rc.getDetail()) {
			Trpreportdtl po = (Trpreportdtl) o;
			po.setTrpreportid(reportid);
			Integer temp = map.get(po.getTrpreportpluginid().toString());
			if (temp != null) {
				po.setTrpreportpluginid(temp);
			}
			os.addAddObject(po);
		}
		for (Object o : rc.getFooter()) {
			Trpreportdtl po = (Trpreportdtl) o;
			po.setTrpreportid(reportid);
			Integer temp = map.get(po.getTrpreportpluginid().toString());
			if (temp != null) {
				po.setTrpreportpluginid(temp);
			}
			os.addAddObject(po);
		}
	}

	// delete from Trpreport
	// delete from Trpreportdatasource
	// delete from Trpreportcondition
	// delete from Trpreportplugin
	// delete from Trpreportdtl
	private void deleteReport(Operators os, String code) {
		String sql = " from Trpreport as a where a.code='" + code + "'";
		List list = dao.iterate(sql);
		for (Object o : list) {
			Trpreport po = (Trpreport) o;
			os.addDeleteObject(po);
			
			StringBuffer hql = new StringBuffer();
			hql.append(" delete from Trpreportdatasource where trpreportid=");
			hql.append(po.getTrpreportid());
			os.addScriptObject(hql.toString());

			StringBuffer hql1 = new StringBuffer();
			hql1.append(" delete from Trpreportcondition where trpreportid=");
			hql1.append(po.getTrpreportid());
			os.addScriptObject(hql1.toString());

			StringBuffer hql2 = new StringBuffer();
			hql2.append(" delete from Trpreportplugin where trpreportid=");
			hql2.append(po.getTrpreportid());
			os.addScriptObject(hql2.toString());

			StringBuffer hql3 = new StringBuffer();
			hql3.append(" delete from Trpreportdtl where trpreportid=");
			hql3.append(po.getTrpreportid());
			os.addScriptObject(hql3.toString());
		}
	}

	private void deleteReport(String code) {
		Operators os = new Operators();
		this.deleteReport(os, code);
		dao.execute(os);
	}

	/**
	 * 编译报表:从数据库到文件
	 */
	public IStrutsForward deploy(ActionContext context) {
		try {
			ReportToolForm form = (ReportToolForm) context.getForm();
			String hql = " from Trpreport as a";
			List list = dao.iterate(hql);
			for (Object o : list) {
				Trpreport rp = (Trpreport) o;
				if ("0".equals(form.getOverBuild())) { // 不需要overwrite
					// 判断当前编译的文件是否存在
					if (ReportUtil.existsReportConfig(rp.getCode(), form)) {
						continue;
					}
				}
				//
				ReportConfig rc = ReportUtil.createReportConfig(dao, rp);
				ReportUtil.saveReportConfig(rc, form);
			}

		} catch (Exception e) {
			SystemLogger.error("上传文件发布报表错误", e);
			processFailure(context, e, true);
			return this.forwardJsp("list");
		}
		processSuccess(context, true);
		return this.forwardJsp("list");
	}

	public IStrutsForward remoteDeploy(ActionContext context) {
		try {
			ReportToolForm form = (ReportToolForm) context.getForm();
			Trpreport report = ReportUtil.getReport(dao, form
					.getDeployReportcode());
			if (report != null) {
				ReportConfig rc = ReportUtil.createReportConfig(dao, report);
				ReportUtil.saveReportConfig(rc, form);
				String file = ReportUtil.getReportFilePath(rc, form);
				// 得到报表文件
				if (StringUtils.isNotBlank(form.getDeployServer())) {
					String[] str = form.getDeployServer().split(",");
					for (String line : str) {
						WxserviceGradeup.reportDeploy(file, line);
					}
				}
			}
		} catch (Exception e) {
			SystemLogger.error("远程发布报表错误", e);
			processFailure(context, e, true);
			return this.forwardJsp("list");
		}
		processSuccess(context, true);
		return this.forwardJsp("list");
	}

	/**
	 * 重新加载报表:从文件到数据库
	 */
	public IStrutsForward load(ActionContext context) {
		try {
			ReportToolForm form = (ReportToolForm) context.getForm();
			Collection col = ReportUtil.getFileCollection(form);
			Iterator lt = col.iterator();
			Operators os = new Operators();
			while (lt.hasNext()) {
				File file = (File) lt.next();
				ReportConfig rc = ReportUtil.loader(new FileInputStream(file));
				if ("1".equals(form.getOverLoad())) {// 在数据库中删除原来的报表
					deleteReport(os, rc.getReport().getCode());
				} else {
					continue;
				}
				config2Db(rc, os);
			}
			dao.execute(os);

		} catch (Exception e) {
			SystemLogger.error("重新加载报表", e);
			processFailure(context, e, true);
			return this.forwardJsp("list");
		}
		processSuccess(context, true);
		return this.forwardJsp("list");
	}
	/**
	 * 批量修改报表条件
	 */
	public IStrutsForward updatereport(ActionContext context) {
		try {
			ReportToolForm form = (ReportToolForm) context.getForm();
			String reportids=form.getReportids();
			String[] oldjsname=form.getOldjsname().split(",");//原js名称,数组长度大于0时表示新增条件并且以这个条件为模板，不然只是修改这个条件
			String[] jsname=form.getNewjsname().split(",");//0为条件名称，1为js名称
			StringBuffer hql = new StringBuffer();
			hql.append(" from Trpreport as a where a.trpreportid in (");
			hql.append(reportids);
			hql.append(")");
			List list = dao.iterate(hql.toString());
			for (Object o : list) {
				Trpreport rp = (Trpreport) o;
				if(oldjsname.length==1){//表示修改报表条件
					//修改报表相关条件
					StringBuffer hql1 = new StringBuffer();
					hql1.append(" from Trpreportcondition as a where a.trpreportid=");
					hql1.append(rp.getTrpreportid());
					hql1.append(" and a.jsname='");
					hql1.append(oldjsname[0]);
					hql1.append("'");
					Trpreportcondition trpreportcondition=(Trpreportcondition)dao.get(hql1.toString());
					if(trpreportcondition!=null){
						trpreportcondition.setHtmlcontent(trpreportcondition.getHtmlcontent().replace(oldjsname[0], jsname[1]).replace(trpreportcondition.getName(), jsname[0]));
						trpreportcondition.setName(jsname[0]);
						trpreportcondition.setJsname(jsname[1]);
						trpreportcondition.setWheres(trpreportcondition.getWheres().replace(oldjsname[0], jsname[1]));
						dao.updateSave(trpreportcondition);
					}
					//修改报表相关数据源
					StringBuffer hql2 = new StringBuffer();
					hql2.append(" from Trpreportdatasource as a where a.trpreportid=");
					hql2.append(rp.getTrpreportid());				
					List<Trpreportdatasource> list1=dao.iterate(hql2.toString());
					if(list1!=null){
						for(Trpreportdatasource trpreportdatasource:list1){
							trpreportdatasource.setContent(trpreportdatasource.getContent().replace(oldjsname[0], jsname[1]));
							dao.updateSave(trpreportdatasource);
						}
						
					}
				}else{//表示新增报表条件
					//找到新增条件的模版
					StringBuffer hql1 = new StringBuffer();
					hql1.append(" from Trpreportcondition as a where a.trpreportid=");
					hql1.append(rp.getTrpreportid());
					hql1.append(" and a.jsname='");
					hql1.append(oldjsname[0]);
					hql1.append("'");
					Trpreportcondition trpreportcondition=(Trpreportcondition)dao.get(hql1.toString());
					//取最大行列
					StringBuffer hql2 = new StringBuffer();
					hql2.append(" select max(rowindex) as rowindex,max(colindex) as colindex from Trpreportcondition  a where a.trpreportid=");
					hql2.append(rp.getTrpreportid());
					hql2.append(" and a.rowindex=(select max(rowindex) from  Trpreportcondition  a where a.trpreportid=");
					hql2.append(rp.getTrpreportid());
					hql2.append(")");
					List list2=dao.getJdbcManager().queryForList(hql2.toString());
					Map map=(Map)list2.get(0);
					Integer rowindex =MapUtil.getInteger(map, "rowindex", false);
					Integer colindex =MapUtil.getInteger(map, "colindex", false);
					if(colindex==2){
						rowindex=rowindex+1;
						colindex=1;
					}else if(colindex==1){
						colindex=2;
					}
					//新建报表条件
					Trpreportcondition condition=new Trpreportcondition();
					condition.setHtmlcontent(trpreportcondition.getHtmlcontent().replace(oldjsname[0], jsname[1]).replace(trpreportcondition.getName(), jsname[0]));
					condition.setTrpreportid(trpreportcondition.getTrpreportid());
					condition.setName(jsname[0]);
					condition.setJsname(jsname[1]);
					condition.setColindex(colindex);
					condition.setRowindex(rowindex);
					condition.setWheres(trpreportcondition.getWheres().replace(oldjsname[0], jsname[1]));
					condition.setWidth(1);
					condition.setProcessclass(trpreportcondition.getProcessclass());
					condition.setTrpreportpluginid(trpreportcondition.getTrpreportpluginid());
					dao.addSave(condition);
					
					//修改报表相关数据源
					StringBuffer hql3 = new StringBuffer();
					hql3.append(" from Trpreportdatasource as a where a.trpreportid=");
					hql3.append(rp.getTrpreportid());				
					List<Trpreportdatasource> list1=dao.iterate(hql3.toString());
					if(list1!=null){
						for(Trpreportdatasource trpreportdatasource:list1){
							String str="where $s_"+jsname[1]+" and ";
							trpreportdatasource.setContent(trpreportdatasource.getContent().replace("where", str));
							dao.updateSave(trpreportdatasource);
						}
						
					}
				}
			
			}

		} catch (Exception e) {
			SystemLogger.error("批量修改报表错误", e);
			processFailure(context, e, true);
			return this.forwardJsp("list");
		}
		processSuccess(context, true);
		return this.forwardJsp("list");
	}
}