package com.wxservice.service.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.wxservice.database.po.system.Tsysexcel;
import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDLineOperatorImpl;
import com.wxservice.framework.excel.ExcelContext;
import com.wxservice.framework.excel.ExcelForm;
import com.wxservice.framework.excel.ExcelUtil;
import com.wxservice.framework.excel.ExportExcel;
import com.wxservice.framework.excel.ImportExcel;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.action.support.DownloadForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.system.TsysexcelForm;

/**
 * 
 * 描述�?Services 创建人：Gererator  
 */
public class TsysexcelManager extends CRUDLineOperatorImpl

{
	private static final long serialVersionUID = -1L;
	ExportExcel exportExcel = null;
	ImportExcel importExcel = null;

	public ExportExcel getExportExcel() {
		return exportExcel;
	}

	public void setExportExcel(ExportExcel exportExcel) {
		this.exportExcel = exportExcel;
	}

	public TsysexcelManager() {

	}

	public Class getEntityClass() {
		return Tsysexcel.class;
	}

	public String getTableKeyField() {
		return "tsysexcelid";
	}

	public Class getLineEntityClass() throws BusinessException {
		return Tsysexceldtl.class;
	}

	/**
	 * 得到明细表的主键名称
	 * 
	 * @return
	 */
	public String getLineTableKeyField() {
		return "tsysexceldtlid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tsysexcel po = (Tsysexcel) object;
		TsysexcelForm form = (TsysexcelForm) baseform;
		form.setTsysexcelid(po.getTsysexcelid());
		form.setCode(po.getCode());
		form.setName(po.getName());
		form.setProcessclass(po.getProcessclass());
		form.setTablename(po.getTablename());
		form.setTablekey(po.getTablekey());
		form.setCheckflag(po.getCheckflag());
		form.setSpringname(po.getSpringname());

	}

	public void bindEntity(BaseForm baseform, Object object) {

		Tsysexcel po = (Tsysexcel) object;
		TsysexcelForm form = (TsysexcelForm) baseform;
		po.setTsysexcelid(form.getTsysexcelid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		po.setProcessclass(form.getProcessclass());
		po.setTablename(form.getTablename());
		po.setTablekey(form.getTablekey());
		po.setCheckflag(form.getCheckflag());
		po.setSpringname(form.getSpringname());

	}

	public void bindLineForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tsysexceldtl po = (Tsysexceldtl) object;
		TsysexcelForm form = (TsysexcelForm) baseform;
		form.setTsysexceldtlid(po.getTsysexceldtlid());
		form.setTsysexcelid(po.getTsysexcelid());
		form.setFieldname(po.getFieldname());
		form.setFieldtype(po.getFieldtype());
		form.setFieldtitle(po.getFieldtitle());
		form.setFieldprocess(po.getFieldprocess());
		form.setColindex(po.getColindex());
		form.setColwidth(po.getColwidth());
		form.setFieldvalue(po.getFieldvalue());
	}

	public void bindLineEntity(BaseForm baseform, Object object) {
		Tsysexceldtl po = (Tsysexceldtl) object;
		TsysexcelForm form = (TsysexcelForm) baseform;
		po.setTsysexceldtlid(form.getTsysexceldtlid());
		po.setTsysexcelid(form.getTsysexcelid());
		po.setFieldname(form.getFieldname());
		po.setFieldtype(form.getFieldtype());
		po.setFieldtitle(form.getFieldtitle());
		po.setFieldprocess(form.getFieldprocess());
		po.setColindex(form.getColindex());
		po.setColwidth(form.getColwidth());
		po.setFieldvalue(form.getFieldvalue());
	}

	public boolean isUniques(Object entity) throws Exception {
		Tsysexcel po = (Tsysexcel) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysexcel as a where a.code='");
		hql.append(po.getCode());
		hql.append("'");
		Object o = dao.get(hql.toString());
		if (o == null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void updateSaveEx(ActionContext context, Object entity, Operators os) {
		Tsysexcel po = (Tsysexcel) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysexcel as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.tsysexcelid!=");
		hql.append(po.getTsysexcelid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}

	}

	@Override
	public void initFormCombo(ActionContext context)  {
		List processclasslist = ExcelUtil.getExcelProcessOptions();
		TsysexcelForm form = (TsysexcelForm) context.getForm();
		form.setProcessclasslist(processclasslist);
	}

	@Override
	public void initFormComboLine(ActionContext context) {
		TsysexcelForm form = (TsysexcelForm) context.getForm();
		List fieldtypelist = ExcelUtil.getDatatypeOptions();
		List fieldprocesslist = ExcelUtil.getFieldProcessOptions();
		form.setFieldprocesslist(fieldprocesslist);
		form.setFieldtypelist(fieldtypelist);
	}

	@Override
	public void listEx(ActionContext context, Map model, Object entity)
			throws Exception {
		// TODO Auto-generated method stub
		SysUtil.copyProperties(model, entity);
		Tsysexcel po = (Tsysexcel) entity;
		model.put("excelprocessname", ExcelUtil.getExcelProcessName(po
				.getProcessclass()));
	}

	@Override
	public void viewEx(ActionContext context, Object entity)  {
		// TODO Auto-generated method stub
		this.initFormCombo(context);
	}

	@Override
	public void viewLineEx(ActionContext context, Object entity) {
		// TODO Auto-generated method stub
		this.initFormComboLine(context);
	}

	@Override
	public void listLineEx(ActionContext context, Map model, Object entity)
			throws Exception {
		// TODO Auto-generated method stub
		SysUtil.copyProperties(model, entity);
		Tsysexceldtl po = (Tsysexceldtl) entity;
		model.put("fieldtypename", ExcelUtil.getDatatypeName(po.getFieldtype()));
		model.put("fieldprocessname", ExcelUtil.getFieldProcessName(po
				.getFieldprocess()));
	}

	// 继续增加
	public IStrutsForward addSaveagain(ActionContext context) {
		TsysexcelForm form = (TsysexcelForm) context.getForm();
		String pk = null;
		this.addSaveLine(context);
		form.setFieldname(null);
		form.setTsysexceldtlid(null);
		form.setFieldtype(null);
		form.setFieldtitle(null);
		form.setFieldprocess(null);
		form.setColwidth(null);
		form.setColindex(form.getColindex()+1);
		form.setFieldvalue(null);
		processSuccess(context, true, pk);
		return forwardMethod(ADDLINE_METHOD);
	}

	@Override
	public void addLineEx(ActionContext context) {
		// TODO Auto-generated method stub
		context.getRequest().setAttribute("methodflag", "add");
	}

	public IStrutsForward copy(ActionContext context) {
		try {
			token.saveToken(context.getRequest());// 保存token
			Object entity = getEntity(context);
			context.getForm().setMethod("copySave");
			this.bindForm(context.getForm(), entity);
			TsysexcelForm form = (TsysexcelForm) context.getForm();
			form.setCode("");
			initFormCombo(context);
			initFormName(context, entity);
			updateEx(context, entity);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(VIEW_METHOD);
		}
		processSuccess(context, false);
		return forwardJsp(EDIT_PAGE);

	}

	public IStrutsForward copySave(ActionContext context) {
		BaseForm form = (BaseForm) context.getForm();
		String pk = null;
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				// 生成实例
				Object entity = createEntity();
				bindEntity(context.getForm(), entity);
				// 判断主键是否重复
				boolean isUnique = isUniques(entity);
				if (!isUnique) {
					BusinessException exception = new BusinessException("编码重复");
					throw exception;
				} else {
					Operators os = new Operators();
					os.addAddObject(entity);
					addSaveEx(context, entity, os);
					dao.execute(os);
					pk = getIdValue(entity);
					addSaveTsysexceldtl(context, pk);
					BeanUtils.setProperty(context.getForm(), this
							.getTableKeyField(), pk);
				}
			}
		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod(ADD_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardMethod(VIEW_METHOD);
	}

	// 增加copy的明�?
	private void addSaveTsysexceldtl(ActionContext context, String pk) {
		TsysexcelForm form = (TsysexcelForm) context.getForm();
		StringBuffer sql = new StringBuffer();
		sql.append("from Tsysexceldtl where tsysexcelid=");
		sql.append(form.getTsysexcelid());
		List<Tsysexceldtl> tsysexcels = (List<Tsysexceldtl>) dao.iterate(sql
				.toString());
		Operators os = new Operators();
		for (Tsysexceldtl tsysexcel : tsysexcels) {
			tsysexcel.setTsysexcelid(Integer.valueOf(pk));
			os.addAddObject(tsysexcel);
		}
		dao.execute(os);
	}

	// 下载模板
	public IStrutsForward xsl(ActionContext context) {
		String code = null;
		try {
			TsysexcelForm form = (TsysexcelForm) context.getForm();
			ExcelContext ctx = ExcelUtil.createExcelContext(context);
			ctx.getForm().setCode(form.getCode());
			byte[] bytes = (byte[]) exportExcel.execute(ctx);
			DownloadForward forward = new DownloadForward(bytes);
			forward.setDownloadFile("_" + ctx.getMaster().getName() + ".xls",
					context);
			return forward;

		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("生成模板发生错误[");
			msg.append(code);
			msg.append("]");
			SystemLogger.error(msg.toString(), e);
		}
		return this.forwardMethod("view");
	}

	public IStrutsForward main(ActionContext context) {
		return this.forwardJsp("main");
	}

	public IStrutsForward uploadSave(ActionContext context) {
		String code = null;
		try {
			ExcelForm form = (ExcelForm) context.getForm();
			ExcelContext ctx = ExcelUtil.createExcelContext(context);
			List errorList = (List) importExcel.execute(ctx);
			if (errorList != null && errorList.size() > 0) {
				context.getRequest().setAttribute(SysFinal.WEB_MESSAGE,
						"Excel数据导入失败!");
				context.getRequest().setAttribute("errorlist", errorList);
			} else {
				context.getRequest().setAttribute(SysFinal.WEB_MESSAGE,
						"Excel数据导入成功"+ctx.getResults().size()+"条数据！");
			}
			return this.forwardMethod("main");

		} catch (Exception e) {
			context.getRequest().setAttribute(SysFinal.WEB_MESSAGE,
					"Excel数据导入失败!");
			StringBuffer msg = new StringBuffer();
			msg.append("导入Excel数据发生错误[");
			msg.append(code);
			msg.append("]");
			SystemLogger.error(msg.toString(), e);
		}
		return this.forwardMethod("main");
	}

	public ImportExcel getImportExcel() {
		return importExcel;
	}

	public void setImportExcel(ImportExcel importExcel) {
		this.importExcel = importExcel;
	}

	@Override
	public void createLineListHqlWhere(StringBuffer hql, ActionContext context) {
		hql.append(" order by colindex");
	}

}
