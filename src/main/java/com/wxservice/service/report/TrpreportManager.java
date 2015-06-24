package com.wxservice.service.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;

import com.wxservice.database.po.report.Trpdcubetpl;
import com.wxservice.database.po.report.Trpreport;
import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportdatasource;
import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.database.po.report.Trpreportplugin;
import com.wxservice.database.po.system.Tsysmenu;
import com.wxservice.framework.components.right.BaseRightService;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.dao.support.Page;
import com.wxservice.framework.engine.impl.CRUDLineOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.TreeUtil;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.action.support.DownloadForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.report.TrpreportForm;

/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class TrpreportManager extends CRUDLineOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TrpreportManager() {

	}

	public Class getEntityClass() {
		return Trpreport.class;
	}

	public String getTableKeyField() {
		return "trpreportid";
	}

	public Class getLineEntityClass() throws BusinessException {
		return Trpreportdtl.class;
	}

	/**
	 * 得到明细表的主键名称
	 * 
	 * @return
	 */
	public String getLineTableKeyField() {
		return "trpreportdtlid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Trpreport po = (Trpreport) object;
		TrpreportForm form = (TrpreportForm) baseform;
		form.setTrpreportid(po.getTrpreportid());
		form.setCode(po.getCode());
		form.setName(po.getName());
		form.setProcessclass(po.getProcessclass());
		form.setListenclass(po.getListenclass());
		form.setServerurl(po.getServerurl());
		form.setParentid(po.getParentid());
		form.setDispprocessor(po.getDispprocessor());

	}

	public void bindEntity(BaseForm baseform, Object object) {

		Trpreport po = (Trpreport) object;
		TrpreportForm form = (TrpreportForm) baseform;
		po.setTrpreportid(form.getTrpreportid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		po.setProcessclass(form.getProcessclass());
		po.setListenclass(form.getListenclass());
		po.setServerurl(form.getServerurl());
		po.setParentid(form.getParentid());
		po.setDispprocessor(form.getDispprocessor());

	}

	public void bindLineForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Trpreportdtl po = (Trpreportdtl) object;
		TrpreportForm form = (TrpreportForm) baseform;
		form.setTrpreportdtlid(po.getTrpreportdtlid());
		form.setTrpreportid(po.getTrpreportid());
		form.setTitle(po.getTitle());
		form.setField(po.getField());
		form.setFieldtype(po.getFieldtype());
		form.setRowindex(po.getRowindex());
		form.setColindex(po.getColindex());
		form.setWidth(po.getWidth());
		form.setAlign(po.getAlign());
		form.setLinkflag(po.getLinkflag());
		form.setLinkurl(po.getLinkurl());
		form.setJavacontent(po.getJavacontent());
		form.setValuemin(po.getValuemin());
		form.setValuemax(po.getValuemax());
		form.setColor(po.getColor());
		form.setTrpreportpluginid(po.getTrpreportpluginid());
		form.setReportarea(po.getReportarea());
		form.setDimvalueflag(po.getDimvalueflag());
	}

	public void bindLineEntity(BaseForm baseform, Object object) {
		Trpreportdtl po = (Trpreportdtl) object;
		TrpreportForm form = (TrpreportForm) baseform;
		po.setTrpreportdtlid(form.getTrpreportdtlid());
		po.setTrpreportid(form.getTrpreportid());
		po.setTitle(form.getTitle());
		po.setField(form.getField());
		po.setFieldtype(form.getFieldtype());
		po.setRowindex(form.getRowindex());
		po.setColindex(form.getColindex());
		po.setWidth(form.getWidth());
		po.setAlign(form.getAlign());
		po.setLinkflag(form.getLinkflag());
		po.setLinkurl(form.getLinkurl());
		po.setJavacontent(form.getJavacontent());
		po.setValuemax(form.getValuemax());
		po.setValuemin(form.getValuemin());
		po.setColor(form.getColor());
		po.setTrpreportpluginid(form.getTrpreportpluginid());
		po.setReportarea(form.getReportarea());
		po.setDimvalueflag(form.getDimvalueflag());
	}

	public boolean isUniques(Object entity) throws Exception {
		Trpreport po = (Trpreport) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreport as a where a.code='");
		hql.append(po.getCode());
		hql.append("'");
		Object o = dao.get(hql.toString());
		if (o == null) {
			return true;
		} else {
			return false;
		}

	}

	public void updateSaveEx(ActionContext context, Object entity, Operators os) {
		Trpreport po = (Trpreport) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreport as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.trpreportid!=");
		hql.append(po.getTrpreportid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}

	}

	@Override
	public IStrutsForward view(ActionContext context) {
		// TODO Auto-generated method stub
		BaseForm form = (BaseForm) context.getForm();
		String pk = null;
		try {
			token.saveToken(context.getRequest());
			Object entity = getEntity(context);
			if (entity == null) {
				BusinessException exception = new BusinessException(
						NOEXISTRECORD);
				throw exception;
			} else {
				pk = getIdValue(entity);
				bindForm(context.getForm(), entity);
				viewEx(context, entity);
				context.getRequest().setAttribute(
						BaseRightService.RIGHT_OBJECT, entity);
			}
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(LIST_METHOD);
		}
		processSuccess(context, false);
		return forwardJsp(VIEW_PAGE);
	}

	@Override
	public void addSaveLineEx(ActionContext context, Object entity, Operators os)
			{
		// TODO Auto-generated method stub
		Trpreportdtl po = (Trpreportdtl) entity;

		if (!isUniquesLine(po))
			throw new BusinessException("同组内不能存在两个相同标题！");
	}

	public void updateSaveLineEx(ActionContext context, Object entity,
			Operators os) {
		Trpreportdtl po = (Trpreportdtl) entity;
		if (isNameTheSame(po.getTrpreportdtlid(), po.getTrpreportid(), po
				.getTitle(), po.getReportarea()))
			throw new BusinessException("同组内不能存在两个相同标题！");
	}

	public boolean isUniquesLine(Object entity)  {
		Trpreportdtl po = (Trpreportdtl) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreportdtl as a where a.trpreportid=");
		hql.append(po.getTrpreportid());
		hql.append(" and a.reportarea=");
		hql.append(po.getReportarea());
		hql.append(" and a.title='");
		hql.append(po.getTitle());
		hql.append("'");
		Object o = dao.get(hql.toString());
		if (o == null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断已经有同名（同ID）标题
	 * 
	 * @param tbscolorid
	 * @return
	 */
	private boolean isNameTheSame(int trpreportdtlid, int trpreportid,
			String label, int reportarea) {
		StringBuffer shql = new StringBuffer();
		shql.append(" from Trpreportdtl where trpreportdtlid!=");
		shql.append(trpreportdtlid);
		shql.append(" and trpreportid=");
		shql.append(trpreportid);
		shql.append(" and title='");
		shql.append(label);
		shql.append("'");
		shql.append(" and reportarea=");
		shql.append(reportarea);

		if (dao.get(shql.toString()) == null)
			return false;
		return true;
	}

	public IStrutsForward main(ActionContext context) {
		return forwardJsp(MAIN_PAGE);
	}

	public IStrutsForward tree(ActionContext context) {
		return forwardJsp(TREE_PAGE);
	}

	public IStrutsForward json_old(ActionContext context) {
		try {
			Map map = new HashMap();
			TrpreportForm form = (TrpreportForm) context.getForm();
			List nodes = new ArrayList();
			if (form.getId() == null) {
				Map node = new HashMap();
				node.put("id", "0");
				node.put("text", "功能菜单");
				node.put("parentId", "0");
				node.put("isfolder", true);
				Map attrs = new HashMap();
				attrs.put("url", getUrl("0", "list", context.getRequest()));
				attrs.put("target", "rightFrame");
				node.put("attrs", attrs);
				nodes.add(node);
			} else {

				StringBuffer hql = new StringBuffer(
						" from Tsysmenu as a where a.parentid=");
				hql.append(form.getId());
				hql.append(" order by a.indexno");
				List list = dao.iterate(hql.toString());
				for (Object o : list) {
					Tsysmenu po = (Tsysmenu) o;
					Map node = new HashMap();
					node.put("id", po.getTsysmenuid());
					node.put("text", po.getName());
					node.put("parentId", po.getParentid().toString());
					if (isHasChild(po.getTsysmenuid())) {
						node.put("isfolder", true);
						Map attrs = new HashMap();
						attrs.put("url", getUrl(po.getTsysmenuid().toString(),
								"list", context.getRequest()));
						attrs.put("target", "rightFrame");
						node.put("attrs", attrs);
						nodes.add(node);
					}

				}

			}
			map.put("nodes", nodes);
			processSuccess(context, false);
			return this.forwardJson(map);
		} catch (Exception e) {
			processFailure(context, e, false);
			return this.forwardJsp(MAIN_PAGE);
		}

	}

	public String getUrl(String id, String method, HttpServletRequest request) {
		StringBuffer url = new StringBuffer();
		url.append("/trpreport.do?method=");
		url.append(method);
		url.append("&parentid=");
		url.append(id);
		String absurl = SysUtil.getAbsUrl(url.toString(), request);
		return absurl;
	}

	private boolean isHasChild(Integer id) {
		Boolean result = false;
		StringBuffer hql = new StringBuffer();
		hql.append("from Tsysmenu as a where a.parentid=").append(id);
		List l = dao.list(hql.toString());
		if (l.size() > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public void listLineEx(ActionContext context, Map model, Object entity)
			throws Exception {
		// TODO Auto-generated method stub
		SysUtil.copyProperties(model, entity);
		Trpreportdtl trpreportdtl = (Trpreportdtl) entity;
		model.put("fieldtype", ReportUtil.getReportFieldTypeName(trpreportdtl
				.getFieldtype()));
		model.put("align", ReportUtil.getReportAlignName(trpreportdtl
				.getAlign()));
		model.put("trpreportpluginid", this.nameManager.getName(
				Trpreportplugin.class, trpreportdtl.getTrpreportpluginid(),
				"name"));
	}

	@Override
	public void initFormCombo(ActionContext context) {
		// TODO Auto-generated method stub
		TrpreportForm form = (TrpreportForm) context.getForm();
		List processclasslist = ReportUtil.getReportProcessclassOptions();
		form.setProcessclasslist(processclasslist);
		//
		List fieldtypelist = ReportUtil.getReportFieldTypeOption();
		form.setFieldtypelist(fieldtypelist);
		List alignlist = ReportUtil.getReportAlignOption();
		form.setAlignlist(alignlist);

		StringBuffer hql = new StringBuffer(
				" from Trpreportplugin a where a.trpreportid=");
		hql.append(form.getTrpreportid());
		SystemLogger.debug(hql.toString());
		List<LabelValue> trpreportpluginidlist = nameManager.getOptions(hql
				.toString(), "name", "trpreportpluginid");
		/*
		 * List trpreportpluginidlist = this.nameManager.getOptions(
		 * Trpreportplugin.class, "name", "trpreportpluginid");
		 */
		form.setTrpreportpluginidlist(trpreportpluginidlist);

		form.setDispprocessoroptions(ReportUtil.getReportDispProcessOptions());
	}

	@Override
	public void createListHqlWhere(StringBuffer hql, ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		hql.append(" and entity.parentid=");
		hql.append(form.getParentid());
		hql.append(" order by code");
	}

	@Override
	public void viewEx(ActionContext context, Object entity)
		 {
		qureytrpreportdatasourceList(context, null);// 查询数据源列表
		qureytrpreportpluginList(context, null);// 查询插件列表
		qureyconditionList(context, null);// 查询条件列表
		qureyviewline(context, null, 1);// 查询明细列表
		qureyviewline(context, null, 0);// 查询表头
		qureyviewline(context, null, 2);// 查询表尾
		try {
			this.initFormCombo(context);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void addLineEx(ActionContext context) {
		this.initFormCombo(context);
		TrpreportForm form = (TrpreportForm) context.getForm();
		form.setWidth(10);
		form.setFieldtype(1);
		form.setRowindex(1);
		int trpreportid = form.getTrpreportid();
		int reportarea = form.getReportarea();
		if (reportarea == 1) {
			StringBuffer hql = new StringBuffer(
					"select max(trpreportdtl.colindex) from Trpreportdtl trpreportdtl");
			hql.append(" where trpreportdtl.trpreportid = ");
			hql.append(trpreportid);
			hql.append("and trpreportdtl.reportarea = ");
			hql.append(reportarea);
			Object coloindex = dao.get(hql.toString());
			int maxColindex = 0;
			if (coloindex != null)
				maxColindex = Integer.valueOf(coloindex.toString());
			form.setColindex(maxColindex + 1);
		} else if (reportarea == 2) {
			StringBuffer hql = new StringBuffer(
					"select max(trpreportdtl.colindex) from Trpreportdtl trpreportdtl");
			hql.append(" where trpreportdtl.trpreportid = ");
			hql.append(trpreportid);
			hql.append(" and trpreportdtl.reportarea = ");
			hql.append(reportarea);
			Object coloindex = dao.get(hql.toString());
			int maxColindex = 0;
			if (coloindex != null)
				maxColindex = Integer.valueOf(coloindex.toString());
			form.setColindex(maxColindex + 1);
		} else {
			form.setColindex(1);
		}
	}

	@Override
	public void updateLineEx(ActionContext context, Object entity) {
		// TODO Auto-generated method stub
		try {
			this.initFormCombo(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void viewLineEx(ActionContext context, Object entity) {
		try {
			this.initFormCombo(context);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void qureyviewline(ActionContext context, Object object,
			int reportarea) {
		TrpreportForm trpreportForm = (TrpreportForm) context.getForm();
		try {
			StringBuffer hql = new StringBuffer(
					" from Trpreportdtl a where a.trpreportid=");
			hql.append(trpreportForm.getTrpreportid());
			hql.append("and reportarea=");
			hql.append(reportarea);
			hql.append(" order by a.rowindex,a.colindex");
			SystemLogger.debug(hql.toString());

			List list = (List) dao.iterate(hql.toString());
			List<Map> webList = new ArrayList<Map>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map model = new HashMap();
					Object entity = list.get(i);
					listqueryviewEx(context, model, entity);
					webList.add(model);
				}

			}
			if (reportarea == 1)
				context.getRequest().setAttribute("trpreportline", webList);
			else if (reportarea == 0)
				context.getRequest().setAttribute("trpreporthead", webList);
			else if (reportarea == 2)
				context.getRequest().setAttribute("trpreporttail", webList);
		} catch (Exception e) {
			processFailure(context, e, false);
			e.printStackTrace();
		}

	}

	private void listqueryviewEx(ActionContext context, Map model, Object entity) {
		SysUtil.copyProperties(model, entity);
		Trpreportdtl po = (Trpreportdtl) entity;
		if (po.getFieldtype() != null) {
			model.put("fieldtype", ReportUtil.getReportFieldTypeName(po
					.getFieldtype()));
		}

	}

	private void qureyconditionList(ActionContext context, Object object) {

		TrpreportForm trpreportForm = (TrpreportForm) context.getForm();
		try {
			StringBuffer hql = new StringBuffer(
					" from Trpreportcondition a where a.trpreportid=");
			hql.append(trpreportForm.getTrpreportid());
			hql.append(" order by a.rowindex, a.colindex");
			SystemLogger.debug(hql.toString());

			List list = (List) dao.iterate(hql.toString());
			List<Map> webList = new ArrayList<Map>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map model = new HashMap();
					Object entity = list.get(i);
					listConditionEx(context, model, entity);
					webList.add(model);
				}

			}
			context.getRequest().setAttribute("trpreportcondition", webList);
		} catch (Exception e) {
			processFailure(context, e, false);
			e.printStackTrace();
		}
	}

	private void qureytrpreportpluginList(ActionContext context, Object object) {
		TrpreportForm trpreportForm = (TrpreportForm) context.getForm();
		try {
			StringBuffer hql = new StringBuffer(
					" from Trpreportplugin a where a.trpreportid=");
			hql.append(trpreportForm.getTrpreportid());
			SystemLogger.debug(hql.toString());

			List list = (List) dao.iterate(hql.toString());
			List<Map> webList = new ArrayList<Map>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map model = new HashMap();
					Object entity = list.get(i);
					listpluginEx(context, model, entity);
					webList.add(model);
				}

			}
			context.getRequest().setAttribute("trpreportplugin", webList);
		} catch (Exception e) {
			processFailure(context, e, false);
			e.printStackTrace();
		}

	}

	@Override
	public void listEx(ActionContext context, Map model, Object entity)
			throws Exception {

		SysUtil.copyProperties(model, entity);
		Trpreport po = (Trpreport) entity;
		model.put("processclass", ReportUtil.getReportProcessclassName(po
				.getProcessclass()));

	}

	private void listpluginEx(ActionContext context, Map model, Object entity) {

		SysUtil.copyProperties(model, entity);

	}

	private void listConditionEx(ActionContext context, Map model, Object entity) {

		SysUtil.copyProperties(model, entity);

	}

	private void qureytrpreportdatasourceList(ActionContext context,
			Object object) {
		TrpreportForm trpreportForm = (TrpreportForm) context.getForm();
		try {
			StringBuffer hql = new StringBuffer(
					" from Trpreportdatasource a where a.trpreportid=");
			hql.append(trpreportForm.getTrpreportid());
			SystemLogger.debug(hql.toString());
			List list = (List) dao.iterate(hql.toString());
			List<Map> webList = new ArrayList<Map>();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map model = new HashMap();
					Object entity = list.get(i);
					listdatasoutceEx(context, model, entity);
					webList.add(model);
				}

			}
			context.getRequest().setAttribute("trpreportdatasource", webList);
		} catch (Exception e) {
			processFailure(context, e, false);
			e.printStackTrace();
		}

	}

	private void listdatasoutceEx(ActionContext context, Map model,
			Object entity) {

		SysUtil.copyProperties(model, entity);
		Trpreportdatasource trpreportdatasource = (Trpreportdatasource) entity;

		model.put("datasourcetype", ReportUtil
				.getReportDatasourcetypeName(trpreportdatasource
						.getDatasourcetype()));
	}

	// 调整
	public IStrutsForward improve(ActionContext context) {
		TrpreportForm trpreportForm = (TrpreportForm) context.getForm();
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("from Trpreportdtl as a where a.reportarea=");
			hql.append(trpreportForm.getReportarea());
			hql.append(" and a.trpreportid=");
			hql.append(trpreportForm.getTrpreportid());
			hql.append("order by a.rowindex, a.colindex");
			SystemLogger.debug(hql.toString());
			/* List list = dao.iterate(hql.toString()); */
			int pageNumber = trpreportForm.getEc_p();
			int limit = trpreportForm.getEc_crd();
			Page page = dao.iterate(hql.toString(), pageNumber, limit);
			trpreportForm.setPage(page);
			List list = (List) page.getResult();
			token.saveToken(context.getRequest());
			context.getRequest().setAttribute("LISTS", list);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardJsp(LIST_PAGE);
		}
		processSuccess(context, false);
		return forwardJsp("improve");
	}

	public IStrutsForward updateall(ActionContext context) throws Exception {
		String index[] = context.getRequest().getParameterValues("rowindex");
		String colum[] = context.getRequest().getParameterValues("colindex");
		String width[] = context.getRequest().getParameterValues("width");
		String trpreportdtlid[] = context.getRequest().getParameterValues(
				"trpreportdtlid");
		Operators os = new Operators();
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				// 修改保存
				for (int i = 0; i < index.length; i++) {
					Trpreportdtl entity = (Trpreportdtl) dao.get(
							Trpreportdtl.class, Integer
									.valueOf(trpreportdtlid[i]));
					if (entity == null) {
						BusinessException exception = new BusinessException(
								NOEXISTRECORD);
						throw exception;
					} else {
						entity.setRowindex(Integer.valueOf(index[i]));
						entity.setColindex(Integer.valueOf(colum[i]));
						entity.setWidth(Integer.valueOf(width[i]));
						os.addUpdateObject(entity);

					}
				}
				dao.execute(os);
			}
		} catch (Exception e) {
			processFailure(context, e, true);
			return forwardMethod("improve");
		}

		processSuccess(context, true);
		return forwardMethod("improve");
	}

	// 从其他表复制
	public IStrutsForward copyFromothertrpreport(ActionContext context) {
		TrpreportForm trpreportForm = (TrpreportForm) context.getForm();
		return forwardJsp("copyFromothertrpreport");
	}

	public IStrutsForward copySave(ActionContext context) {
		TrpreportForm trpreportForm = (TrpreportForm) context.getForm();
		String[] check = context.getRequest().getParameterValues("check");
		StringBuffer sql = new StringBuffer();
		sql.append("select trpreportid from Trpreport where code = '");
		sql.append(context.getRequest().getParameter("codeflag"));
		sql.append("'");
		Integer trpreportid = Integer.valueOf(dao.get(sql.toString())
				.toString());
		Integer owntrpreportid = trpreportForm.getTrpreportid();
		StringBuffer deletesql = new StringBuffer();
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				if (check != null)
					for (String str : check) {
						if (str.equals("datasource")) {
							deletebeforecopy("Trpreportdatasource", deletesql,
									trpreportForm.getTrpreportid(), -1);
							StringBuffer hsql = new StringBuffer();
							hsql
									.append("from Trpreportdatasource a where a.trpreportid=");
							hsql.append(trpreportid);
							dao.iterate(hsql.toString());
							List list = dao.iterate(hsql.toString());
							Operators os = new Operators();
							for (int i = 0; i < list.size(); i++) {
								Trpreportdatasource entity = (Trpreportdatasource) list
										.get(i);
								entity.setTrpreportdatasourceid(null);
								entity.setTrpreportid(owntrpreportid);
								os.addAddObject(entity);
							}
							dao.execute(os);
						}
						if (str.equals("condition")) {
							deletebeforecopy("Trpreportcondition", deletesql,
									trpreportForm.getTrpreportid(), -1);
							StringBuffer hsql = new StringBuffer();
							hsql
									.append("from Trpreportcondition a where a.trpreportid=");
							hsql.append(trpreportid);
							dao.iterate(hsql.toString());
							List list = dao.iterate(hsql.toString());
							Operators os = new Operators();
							for (int i = 0; i < list.size(); i++) {
								Trpreportcondition entity = (Trpreportcondition) list
										.get(i);
								entity.setTrpreportconditionid(null);
								entity.setTrpreportid(owntrpreportid);
								os.addAddObject(entity);
							}
							dao.execute(os);
						}
						if (str.equals("plugin")) {
							deletebeforecopy("Trpreportplugin", deletesql,
									trpreportForm.getTrpreportid(), -1);

							StringBuffer hsql = new StringBuffer();
							hsql
									.append("from Trpreportplugin a where a.trpreportid=");
							hsql.append(trpreportid);
							dao.iterate(hsql.toString());
							List list = dao.iterate(hsql.toString());
							Operators os = new Operators();
							for (int i = 0; i < list.size(); i++) {
								Trpreportplugin entity = (Trpreportplugin) list
										.get(i);
								entity.setTrpreportpluginid(null);
								entity.setTrpreportid(owntrpreportid);
								os.addAddObject(entity);
							}
							dao.execute(os);
						}
						if (str.equals("trpreporthead")) {
							deletebeforecopy("Trpreportdtl", deletesql,
									trpreportForm.getTrpreportid(), 0);

							StringBuffer hsql = new StringBuffer();
							hsql
									.append("from Trpreportdtl a where a.reportarea=0 and a.trpreportid=");
							hsql.append(trpreportid);
							dao.iterate(hsql.toString());
							List list = dao.iterate(hsql.toString());
							Operators os = new Operators();
							for (int i = 0; i < list.size(); i++) {
								Trpreportdtl entity = (Trpreportdtl) list
										.get(i);
								entity.setTrpreportdtlid(null);
								entity.setTrpreportid(owntrpreportid);
								os.addAddObject(entity);
							}
							dao.execute(os);
						}
						if (str.equals("trpreportline")) {
							deletebeforecopy("Trpreportdtl", deletesql,
									trpreportForm.getTrpreportid(), 1);

							StringBuffer hsql = new StringBuffer();
							hsql
									.append("from Trpreportdtl a where a.reportarea=1 and a.trpreportid=");
							hsql.append(trpreportid);
							dao.iterate(hsql.toString());
							List list = dao.iterate(hsql.toString());
							Operators os = new Operators();
							for (int i = 0; i < list.size(); i++) {
								Trpreportdtl entity = (Trpreportdtl) list
										.get(i);
								entity.setTrpreportdtlid(null);
								entity.setTrpreportid(owntrpreportid);
								os.addAddObject(entity);
							}
							dao.execute(os);
						}
						if (str.equals("trpreporttail")) {
							deletebeforecopy("trpreportdtl", deletesql,
									trpreportForm.getTrpreportid(), 2);

							StringBuffer hsql = new StringBuffer();
							hsql
									.append("from Trpreportdtl a where a.reportarea=2 and a.trpreportid=");
							hsql.append(trpreportid);
							dao.iterate(hsql.toString());
							List list = dao.iterate(hsql.toString());
							Operators os = new Operators();
							for (int i = 0; i < list.size(); i++) {
								Trpreportdtl entity = (Trpreportdtl) list
										.get(i);
								entity.setTrpreportdtlid(null);
								entity.setTrpreportid(owntrpreportid);
								os.addAddObject(entity);
							}
							dao.execute(os);
						}
					}
			}
		} catch (Exception e) {
			processFailure(context, e, true);
			return forwardJsp("copyFromothertrpreport");
		}
		processSuccess(context, true);
		return forwardJsp("copyFromothertrpreport");
	}

	public IStrutsForward deploy(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		String pk = null;
		try {
			Trpreport rp = ReportUtil.getReport(dao, form.getTrpreportid());
			ReportConfig rc = ReportUtil.createReportConfig(dao, rp);
			ReportUtil.saveReportConfig(rc, form);

		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod("view");
		}
		processSuccess(context, true, pk);
		return forwardMethod("view");
	}

	public IStrutsForward menu(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		String pk = null;
		try {
			Trpreport rp = ReportUtil.getReport(dao, form.getTrpreportid());
			Operators os = new Operators();
			ReportUtil.createMenu(dao, rp, os, form);
			dao.execute(os);
		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod("view");
		}
		processSuccess(context, true, pk);
		return forwardMethod("view");
	}

	public IStrutsForward download(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		String pk = null;
		try {
			Trpreport rp = ReportUtil.getReport(dao, form.getTrpreportid());
			ReportConfig rc = ReportUtil.createReportConfig(dao, rp);
			ReportUtil.saveReportConfig(rc, form);
			String file = ReportUtil.getReportFilePath(rc, form);
			byte[] bytes = ReportUtil.readFileToByte(file);
			DownloadForward forward = new DownloadForward(bytes);
			forward.setDownloadFile(rc.getReport().getCode()
					+ ReportUtil._reportExtName, context);
			return forward;
		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod("view");
		}

	}

	public IStrutsForward copyDetailToTail(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("from Trpreportdtl where trpreportid =");
			sql.append(form.getTrpreportid());
			sql.append(" and reportarea = 1");
			List<Trpreportdtl> trpreportdtls = (List<Trpreportdtl>) dao
					.iterate(sql.toString());
			Operators os = new Operators();
			deleteTail(os, form.getTrpreportid());
			for (Trpreportdtl trpreportdtl : trpreportdtls) {
				trpreportdtl.setReportarea(2);
				os.addAddObject(trpreportdtl);
			}
			dao.execute(os);
		} catch (Exception e) {
			processFailure(context, e, true);
			return forwardMethod("view");
		}
		processSuccess(context, true);
		return forwardMethod("view");
	}

	public void deleteTail(Operators os, int trpreportid) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from Trpreportdtl where trpreportid =");
		sql.append(trpreportid);
		sql.append(" and reportarea = 2");
		os.addBatchScriptObject(sql.toString());
	}

	@Override
	public void deleteEx(ActionContext context, Object entity, Operators os) {
		// TODO Auto-generated method stub
		TrpreportForm form = (TrpreportForm) context.getForm();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from trpreportcondition where trpreportid =");
		sql.append(form.getTrpreportid());
		os.addScriptObject(sql);
		sql.setLength(0);
		sql.append("delete from trpreportdatasource where trpreportid =");
		sql.append(form.getTrpreportid());
		os.addScriptObject(sql);
		sql.setLength(0);
		sql.append("delete from trpreportdtl where trpreportid =");
		sql.append(form.getTrpreportid());
		os.addScriptObject(sql);
		sql.setLength(0);
		sql.append("delete from trpreportplugin where trpreportid =");
		sql.append(form.getTrpreportid());
		os.addScriptObject(sql);
	}

	@Override
	public void addEx(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		List<LabelValue> options = new ArrayList<LabelValue>();
		myGetOptions(options, 0, "--");
		form.setParentlist(options);

	}

	/**
	 * 
	 * @param parentid
	 *            递归方法
	 * @return
	 */
	private void myGetOptions(List options, int parentid, String str) {

		StringBuffer sb = new StringBuffer();
		sb.append(" from Tsysmenu where accessurl='' and parentid=");
		sb.append(parentid);
		sb.append(" order by indexno");

		try {
			List<Tsysmenu> listSub = dao.iterate(sb.toString());
			if (listSub.size() == 0)
				return;
			for (Tsysmenu menu : listSub) {
				options.add(new LabelValue(str + menu.getName(), menu
						.getTsysmenuid()));
				myGetOptions(options, menu.getTsysmenuid(), "--" + str);
			}

		} catch (Exception e) {
			SystemLogger.error("BeanUtil取属性发生错误", e);
		}

	}

	@Override
	public void updateEx(ActionContext context, Object entity) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		List<LabelValue> options = new ArrayList<LabelValue>();
		myGetOptions(options, 0, "--");
		form.setParentlist(options);

	}

	public void deletebeforecopy(String table, StringBuffer deletesql,
			Integer trpreportid, Integer reportarea) {
		Operators os = new Operators();
		deletesql.setLength(0);
		deletesql.append(" delete from ");
		deletesql.append(table);
		deletesql.append(" where trpreportid=");
		deletesql.append(trpreportid);
		if (reportarea != -1) {
			deletesql.append(" and reportarea=");
			deletesql.append(reportarea);
		}
		os.addBatchScriptObject(deletesql.toString());
		dao.execute(os);

	}

	// 以下是报表拖拉部分
	public IStrutsForward saveTemplate(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		try {
			if (token.isTokenValidLine(context.getRequest(), true)) {
				Operators os = new Operators();
				Trpdcubetpl po = (Trpdcubetpl) dao.get(Trpdcubetpl.class, form
						.getTrpdcubetplid());
				processTemplate(po, context, form);
				os.addUpdateObject(po);
				dao.execute(os);
				form.setTrpdcubetplid(po.getTrpdcubetplid());
			}
		} catch (Exception e) {
			processFailure(context, e, true);
			return forwardMethod("setTemplate");
		}
		processSuccess(context, true);
		return forwardMethod("setTemplate");
	}

	public IStrutsForward addsaveTemplate(ActionContext context)
			throws Exception {
		TrpreportForm form = (TrpreportForm) context.getForm();
		try {
			if (token.isTokenValidLine(context.getRequest(), true)) {
				Operators os = new Operators();
				Trpdcubetpl obj = getTrpdcubetpl(context, form);
				if (obj != null) {
					BusinessException exception = new BusinessException("名称重复");
					throw exception;
				}
				Trpdcubetpl po = new Trpdcubetpl();
				processTemplate(po, context, form);
				os.addAddObject(po);
				dao.execute(os);
				form.setTrpdcubetplid(po.getTrpdcubetplid());
			}
		} catch (Exception e) {
			processFailure(context, e, true);
			return forwardMethod("setTemplate");
		}
		processSuccess(context, true);
		return forwardMethod("setTemplate");

	}

	public Trpdcubetpl getTrpdcubetpl(ActionContext context, TrpreportForm form) {
		StringBuffer sql = new StringBuffer();
		sql.append("from Trpdcubetpl where name='");
		sql.append(form.getName());
		sql.append("' and reportid = ");
		sql.append(form.getTrpreportid());
		sql.append(" and tbsuserid = ");
		sql.append(WebUtil.getClientSession(context.getRequest()).getCmpid());
		Trpdcubetpl obj = (Trpdcubetpl) dao.get(sql.toString());
		return obj;
	}

	public Trpdcubetpl processTemplate(Trpdcubetpl po, ActionContext context,
			TrpreportForm form) {
		Trpreport trpreport = (Trpreport) dao.get(Trpreport.class, form
				.getTrpreportid());
		po.setColband(form.getColbands());
		po.setDataband(form.getDatabands());
		po.setHiddenband(form.getHiddenbands());
		po.setName(form.getName());
		po.setPageband(form.getPagebands());
		po.setRowband(form.getRowbands());
		po.setTbsuserid(WebUtil.getClientSession(context.getRequest())
				.getCmpid());
		po.setShowsubflag(form.getShowsubflag());
		po.setReportid(trpreport.getTrpreportid());
		po.setSubband(form.getSubbands());
		form.setCode(trpreport.getCode());
		return po;
	}

	public Trpreport getTrpreport(TrpreportForm form) {
		StringBuffer sb = new StringBuffer();
		sb.append("from Trpreport where code ='");
		sb.append(form.getCode());
		sb.append("'");
		Trpreport trpreport = (Trpreport) dao.get(sb.toString());
		return trpreport;
	}

	@SuppressWarnings("unchecked")
	public IStrutsForward loadTemplate(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		Map map = new LinkedMap();
		map.put("result", "0");
		try {
			Trpdcubetpl po = (Trpdcubetpl) dao.get(Trpdcubetpl.class, form
					.getTrpdcubetplid());
			map.put("result", "1");
			map.put("colstr", po.getColband());
			map.put("pagestr", po.getPageband());
			map.put("hiddenstr", po.getHiddenband());
			map.put("rowstr", po.getRowband());
			map.put("substr", po.getSubband());
			if (po.getShowsubflag() == 1) {
				map.put("showsubflag", "true");
			} else {
				map.put("showsubflag", "false");
			}

			map.put("name", po.getName());
			map.put("trpdcubetplid", po.getTrpdcubetplid());
			return this.forwardJson(map);
		} catch (Exception e) {
			processFailure(context, e, false);
		}
		return getSystemErrorForward();
	}

	@SuppressWarnings("unchecked")
	public IStrutsForward getSystemErrorForward() {
		Map map = new LinkedMap();
		map.put("result", "0");
		return this.forwardJson(map);
	}

	/**
	 * 维度模板设置
	 * 
	 * @param context
	 * @return
	 */
	public IStrutsForward setTemplate(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		try {
			token.saveTokenLine(context.getRequest());
			Trpreport trpreport = getTrpreport(form);
			Trpdcubetpl po = (Trpdcubetpl) dao.get(Trpdcubetpl.class, form
					.getTrpdcubetplid());
			if (po != null) {
				if (po.getTrpdcubetplid() != null) {
					form.setTrpdcubetplid(po.getTrpdcubetplid());
				}
				if (po.getName() != null) {
					form.setName(po.getName());
				}
			}
			form.setRowbandlist(new ArrayList());
			form.setColbandlist(new ArrayList());
			form.setHiddenbandlist(new ArrayList());
			form.setPagebandlist(new ArrayList());
			form.setDatabandlist(new ArrayList());
			form.setSubbandlist(new ArrayList());
			form.setTotalbandlist(new ArrayList());
			form.setMethod("saveTemplate");
			form.setTrpreportid(trpreport.getTrpreportid());
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardJsp("error");
		}
		processSuccess(context, false);
		return forwardJsp("saveTemplate");
	}

	public List<Trpreportdtl> getTrpreportdtls(Trpreport trpreport) {
		StringBuffer sql = new StringBuffer();
		sql.append("from Trpreportdtl where reportarea=1 and trpreportid=");
		sql.append(trpreport.getTrpreportid());
		List<Trpreportdtl> list = dao.iterate(sql.toString());
		return list;
	}

	/**
	 * 汇总模板设置
	 * 
	 * @param context
	 * @return
	 */
	public IStrutsForward setCollect(ActionContext context) {
		TrpreportForm form = (TrpreportForm) context.getForm();
		try {
			Trpreport trpreport = getTrpreport(form);
			token.saveTokenLine(context.getRequest());
			List<LabelValue> databandlist = new ArrayList<LabelValue>();
			List<LabelValue> totalbandlist = new ArrayList<LabelValue>();
			StringBuffer sql = new StringBuffer();
			sql.append("from Trpreportcondition where trpreportid=");
			sql.append(trpreport.getTrpreportid());
			sql.append(" and jsname='collectCotent'");
			Trpreportcondition po = (Trpreportcondition) dao
					.get(sql.toString());
			if (po != null && StringUtil.isNotBlank(po.getHtmlcontent())) {
				String[] cotentArr = po.getHtmlcontent().split(";;");
				String[] arr = cotentArr[0].split(";");
				for (String str : arr) {// 组合
					String select = str.split("=")[0];
					String name = str.split("=")[1];
					LabelValue label = new LabelValue(name, select);
					databandlist.add(label);
				}
				if (cotentArr.length > 1) {// 单例
					String[] arr2 = cotentArr[1].split(";");
					for (String str : arr2) {
						String select = str.split("=")[0];
						String name = str.split("=")[1];
						LabelValue label = new LabelValue(name, select);
						totalbandlist.add(label);
					}
				}
			}
			form.setDatabandlist(databandlist);
			form.setSubbandlist(new ArrayList());
			form.setTotalbandlist(totalbandlist);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardJsp("error");
		}
		processSuccess(context, false);
		return forwardJsp("setCollect");
	}
	public IStrutsForward json(ActionContext context) {
		return jsondata(context, "list");
	}
	public String getTreeId(Tsysmenu po) {
		StringBuffer id = new StringBuffer();
		//id.append("tree");
		id.append(po.getTsysmenuid());
		return id.toString();
	}
	public String getTreeId(String idstr) {
		String id=idstr;
		return id.toString();
	}
	private IStrutsForward jsondata(ActionContext context, String method) {
		try {
			StringBuffer json = new StringBuffer("");
			HttpServletRequest request = context.getRequest();
			TrpreportForm form = (TrpreportForm) context.getForm();
			String id=this.getTreeId(form.getId());
			json.append("[");
			String str = this.doJsondata(context, id, request,method);
			json.append(str);
			json.append("]");

			return this.forwardJson(json.toString());
		} catch (Exception e) {
			SystemLogger.error("加载树发生错误", e);
		}
		return forwardJsp("main");
	}
	private String getTreeSql(String parentid, String where) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("from Tsysmenu  a where a.parentid=");
		hsql.append(parentid);
		if (StringUtil.isNotBlank(where)) {
			hsql.append(" and ");
			hsql.append(where);
		}
		hsql.append(" order by a.indexno asc");
		return hsql.toString();
	}
	private String doJsondata(ActionContext context, String parentid,
			HttpServletRequest request,String method) throws Exception {
		StringBuffer json = new StringBuffer();
		String hsql = getTreeSql(parentid, "");
		List list = dao.iterate(hsql);
		if (list != null && list.size() > 0) {
			int loopflag = 0;
			for (int loop = 0; loop < list.size(); loop++) {
				Object o = list.get(loop);
                Tsysmenu po=(Tsysmenu)o;
                if (isEnd(po.getTsysmenuid())) {
					if (loopflag > 0) {
						json.append(",");
					}
					loopflag++;
					doLeaf(context, request, json, po,method);

				} else {
					if (loopflag > 0) {
						json.append(",");
					}
					loopflag++;
					json.append("{");
					TreeUtil.doStringJson("href", createLink2(context, po,method), json);
					json.append(",");
					TreeUtil.doStringJson("hrefTarget", "rightFrame", json);
					json.append(",");
					TreeUtil.doBooleanJson("singleClickExpand", "true", json);
					json.append(",");
					TreeUtil.doStringJson("id", this.getTreeId(po), json);
					json.append(",");
					TreeUtil.doStringJson("text", po.getName(), json);
					json.append("}");
				}
			}
    	}
		return json.toString();
	}
	
	public boolean isEnd(Integer parentid) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tsysmenu  a where a.parentid=").append(parentid);
		Object obj = dao.get(hsql.toString());
		return obj == null;
	}
	public String getUrl(Integer parentid, String method,
			HttpServletRequest request) {
		StringBuffer url = new StringBuffer();
		url.append("/trpreport.do?method=");
		url.append(method);
		url.append("&parentid=");
		url.append(parentid);
		String absurl = SysUtil.getAbsUrl(url.toString(), request);
		return absurl;
	}
	public String createLink2(ActionContext context, Tsysmenu menu,String method) {
		String url=this.getUrl(menu.getTsysmenuid(), method, context.getRequest());
		return url;
	}
	private void doLeaf(ActionContext context, HttpServletRequest request,
			StringBuffer json, Tsysmenu po,String method) {
		json.append("{");
		TreeUtil.doStringJson("href", createLink2(context, po,method), json);
		json.append(",");
		TreeUtil.doStringJson("hrefTarget", "rightFrame", json);
		json.append(",");
		TreeUtil.doStringJson("text", po.getName(), json);
		json.append(",");
		TreeUtil.doStringJson("id", this.getTreeId(po), json);
		json.append(",");
		TreeUtil.doBooleanJson("leaf", "true", json);
		json.append(",");
		TreeUtil.doStringJson("iconCls", "icon-cls", json);
		json.append("}");
	}
}
