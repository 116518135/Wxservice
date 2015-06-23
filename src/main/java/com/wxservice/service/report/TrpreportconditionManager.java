package com.wxservice.service.report;
import java.util.List;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.report.TrpreportconditionForm;
/**
 * 
 * 描述：   Services
 * 项目名称：七匹狼运动信息化平台   
 * 创建人：Gererator
 */
public class TrpreportconditionManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;
	public TrpreportconditionManager(){

	}
	public Class getEntityClass() {
		return Trpreportcondition.class;
	}
	public String getTableKeyField() {
		return "trpreportconditionid";
	}
	

	public void bindForm(BaseForm baseform, Object object) {
		if(object==null) return;
		Trpreportcondition po= (Trpreportcondition)object;
		TrpreportconditionForm    form=(TrpreportconditionForm)baseform;
		form.setTrpreportconditionid(po.getTrpreportconditionid());
				   form.setTrpreportid(po.getTrpreportid());
				   form.setName(po.getName());
				   form.setJsname(po.getJsname());
				   form.setWheres(po.getWheres());
				   form.setHtmlcontent(po.getHtmlcontent());
				   form.setRowindex(po.getRowindex());
				   form.setColindex(po.getColindex());
				   form.setWidth(po.getWidth());
				   form.setProcessclass(po.getProcessclass());
				   form.setTrpreportpluginid(po.getTrpreportpluginid());
				
	}
	
	public void bindEntity(BaseForm baseform, Object object) {
	
		Trpreportcondition po= (Trpreportcondition)object;
		TrpreportconditionForm    form=(TrpreportconditionForm)baseform;
		po.setTrpreportconditionid(form.getTrpreportconditionid());
				   po.setTrpreportid(form.getTrpreportid());
				   po.setName(form.getName());
				   po.setJsname(form.getJsname());
				   po.setWheres(form.getWheres());
				   po.setHtmlcontent(form.getHtmlcontent());
				   po.setRowindex(form.getRowindex());
				   po.setColindex(form.getColindex());
				   po.setWidth(form.getWidth());
				   po.setProcessclass(form.getProcessclass());
				   po.setTrpreportpluginid(form.getTrpreportpluginid());
				
	}
	public boolean isUniques(Object entity) throws Exception {
		Trpreportcondition po = (Trpreportcondition) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreportcondition as a where a.name='");
		hql.append(po.getName());
		hql.append("'");
		hql.append(" and a.trpreportid=");
		hql.append(po.getTrpreportid());
		Object o = dao.get(hql.toString());
		if (o == null) {
			return true;
		} else {
			return false;
		}

	}
	
	
	public void updateSaveEx(ActionContext context, Object entity,
			Operators os) {
		Trpreportcondition po=(Trpreportcondition)entity;
		StringBuffer hql=new StringBuffer();
		hql.append(" from Trpreportcondition as a where a.name='");
		hql.append(po.getName());
		hql.append("' and a.trpreportid=");
		hql.append(po.getTrpreportid());
		hql.append(" and a.trpreportconditionid!=");
		hql.append(po.getTrpreportconditionid());
		
		List list=dao.iterate(hql.toString());
		if(list.size()>0){
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}

	}
	public IStrutsForward addSaveagain(ActionContext context) {
		TrpreportconditionForm form = (TrpreportconditionForm) context.getForm();
		String pk = null;
		this.addSave(context);
		form.setName(null);
		form.setHtmlcontent(null);
		form.setWidth(null);
		form.setWheres(null);
		form.setJsname(null);
		form.setProcessclass(null);
		form.setRowindex(null);
		form.setColindex(null);
		form.setTrpreportpluginid(null);
		form.setTrpreportconditionid(null);
//		this.add(context);
		processSuccess(context, true, pk);
		return forwardMethod(ADD_METHOD);
	}
	@Override
	public void initFormCombo(ActionContext context)  {
		// TODO Auto-generated method stub
		TrpreportconditionForm form = (TrpreportconditionForm) context.getForm();
		List processclasslist = ReportUtil.getProcessclassOptions();
		form.setProcessclasslist(processclasslist);
/*		List pluginlist=this.nameManager.getOptions(Trpplugin.class,
				"name", "trppluginid");*/
		
		StringBuffer hql = new StringBuffer(
		" from Trpreportplugin a where a.trpreportid=");
		hql.append(form.getTrpreportid());
		SystemLogger.debug(hql.toString());
		List<LabelValue> pluginlist=nameManager.getOptions(hql.toString(), "name", "trpreportpluginid");
		form.setPluginlist(pluginlist);
		
	}
	@Override
	public void viewEx(ActionContext context, Object entity) {
		// TODO Auto-generated method stub
		this.initFormCombo(context);
	}
	@Override
	public void addEx(ActionContext context) {
		// TODO Auto-generated method stub
		context.getRequest().setAttribute("methodflag", "addSave");
		TrpreportconditionForm form = (TrpreportconditionForm) context.getForm();
		form.setWidth(1);
		form.setProcessclass("commonHtmlProcess");
	}
	@Override
	public void updateEx(ActionContext context, Object entity) {
		// TODO Auto-generated method stub
		context.getRequest().setAttribute("methodflag", "update");
	}
	@Override
	public IStrutsForward delete(ActionContext context) {
		// TODO Auto-generated method stub
		String pk = null;
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				// 得到实体
				Object entity = getEntity(context);
				if (entity == null) {
					BusinessException exception = new BusinessException(
							NOEXISTRECORD);
					throw exception;
				} else {
					pk = getIdValue(entity);
					if (isCanDelete(context, entity,pk)) {
						Operators os = new Operators();
						os.addDeleteObject(entity);
						deleteEx(context, entity, os);
						dao.execute(os);
					}else{
						BusinessException exception = new BusinessException(
						"当前数据正在使用,不能删除.");
				        throw exception;
					}
				}
			}

		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod(VIEW_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardJsp(DELETE_METHOD);
	}
	public IStrutsForward improve(ActionContext context) {
		TrpreportconditionForm form = (TrpreportconditionForm) context.getForm();
		try {
			StringBuffer hql=new StringBuffer();
			hql.append("from Trpreportcondition where trpreportid=");
			hql.append(form.getTrpreportid());
			hql.append("order by rowindex, colindex");
			SystemLogger.debug(hql.toString());
			List list = dao.iterate(hql.toString());
			token.saveToken(context.getRequest());
			context.getRequest().setAttribute("LISTS", list);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardJsp(LIST_PAGE);
		}
		processSuccess(context, false);
		return forwardJsp("improve");
	}

	public IStrutsForward updateall(ActionContext context) throws Exception{
		String index[]=context.getRequest().getParameterValues("rowindex");
		String colum[]=context.getRequest().getParameterValues("colindex");
		String width[]=context.getRequest().getParameterValues("width");
		String trpreportconditionid[]=context.getRequest().getParameterValues("trpreportconditionid");
		Operators os = new Operators();
		try {	
			if (token.isTokenValid(context.getRequest(), true)) {
					// 修改保存
				for(int i=0;i<index.length;i++)
				{
					Trpreportcondition entity =(Trpreportcondition)dao.get(Trpreportcondition.class, Integer.valueOf(trpreportconditionid[i]));
					if (entity == null) {
						BusinessException exception = new BusinessException(
								NOEXISTRECORD);
						throw exception;
					} else {
						entity.setRowindex(Integer.valueOf(index[i]));
						entity.setColindex(Integer.valueOf(colum[i]));
						entity.setWidth(Integer.valueOf(width[i]));
						os.addUpdateObject(entity);
						updateSaveEx(context, entity, os);
						
					}
				}dao.execute(os);
			}
			} catch (Exception e) {
				processFailure(context, e, true);
				return forwardMethod("improve");
			}
	
		processSuccess(context, true);
		return forwardMethod("improve");
	}
}


