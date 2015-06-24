package com.wxservice.service.report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wxservice.database.po.report.Trphtmlcate;
import com.wxservice.database.po.report.Trphtmlctrl;
import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDLineOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.report.html.HtmlProcess;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.report.TrphtmlcateForm;

/**
 * 
 * 描述： Services 创建人：Gererator  
 */
public class TrphtmlcateManager extends CRUDLineOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TrphtmlcateManager() {

	}

	public Class getEntityClass() {
		return Trphtmlcate.class;
	}

	public String getTableKeyField() {
		return "trphtmlcateid";
	}

	public Class getLineEntityClass() throws BusinessException {
		return Trphtmlctrl.class;
	}

	/**
	 * 得到明细表的主键名称
	 * 
	 * @return
	 */
	public String getLineTableKeyField() {
		return "trphtmlctrlid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Trphtmlcate po = (Trphtmlcate) object;
		TrphtmlcateForm form = (TrphtmlcateForm) baseform;
		form.setTrphtmlcateid(po.getTrphtmlcateid());
		form.setCode(po.getCode());
		form.setName(po.getName());

	}

	public void bindEntity(BaseForm baseform, Object object) {

		Trphtmlcate po = (Trphtmlcate) object;
		TrphtmlcateForm form = (TrphtmlcateForm) baseform;
		po.setTrphtmlcateid(form.getTrphtmlcateid());
		po.setCode(form.getCode());
		po.setName(form.getName());

	}

	public void bindLineForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Trphtmlctrl po = (Trphtmlctrl) object;
		TrphtmlcateForm form = (TrphtmlcateForm) baseform;
		form.setTrphtmlctrlid(po.getTrphtmlctrlid());
		form.setTrphtmlcateid(po.getTrphtmlcateid());
		form.setName(po.getName());
		form.setJsname(po.getJsname());
		form.setWheres(po.getWheres());
		form.setContent(po.getContent());
		form.setProcessclass(po.getProcessclass());
	}

	public void bindLineEntity(BaseForm baseform, Object object) {
		Trphtmlctrl po = (Trphtmlctrl) object;
		TrphtmlcateForm form = (TrphtmlcateForm) baseform;
		po.setTrphtmlctrlid(form.getTrphtmlctrlid());
		po.setTrphtmlcateid(form.getTrphtmlcateid());
		po.setName(form.getName());
		po.setJsname(form.getJsname());
		po.setWheres(form.getWheres());
		po.setContent(form.getContent());
		po.setProcessclass(form.getProcessclass());
	}

	public boolean isUniques(Object entity) throws Exception {
		Trphtmlcate po = (Trphtmlcate) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trphtmlcate as a where a.code='");
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
	public void addLineEx(ActionContext context) {
		// TODO Auto-generated method stub
		context.getRequest().setAttribute("methodflag", "addSave");
	}

	public void updateSaveEx(ActionContext context, Object entity, Operators os) {
		Trphtmlcate po = (Trphtmlcate) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trphtmlcate as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.trphtmlcateid!=");
		hql.append(po.getTrphtmlcateid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}
	
	@Override
	public void initFormComboLine(ActionContext context) {
         TrphtmlcateForm form=(TrphtmlcateForm)context.getForm();
         List processclasslist=ReportUtil.getProcessclassOptions();
         form.setProcessclasslist(processclasslist);
	}
	
	/**
	 * 复制
	 * 
	 * @param context
	 * @return
	 */
	public IStrutsForward copy(ActionContext context) {

		try {
			token.saveTokenLine(context.getRequest());
		    Object entity = getLineEntity(context);
			context.getForm().setMethod("copySave");
			bindLineForm(context.getForm(), entity);
			TrphtmlcateForm form=(TrphtmlcateForm)context.getForm();
			initFormComboLine(context);
			initFormNameLine(context, entity);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardMethod(VIEWLINE_METHOD);
		}
		processSuccess(context, false);
		return forwardJsp(EDITLINE_PAGE);

	}
	/**
	 * 保存复制
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public IStrutsForward copySave(ActionContext context) throws Exception {
		TrphtmlcateForm form = (TrphtmlcateForm) context.getForm();
		String pk = null;
		try {
			if (token.isTokenValidLine(context.getRequest(), true)) {
				// 生成实例
				Object entity = getLineEntity(context);
				bindLineEntity(context.getForm(), entity);
				// 判断主键是否重复
				if (entity == null) {
					BusinessException exception = new BusinessException(
							NOEXISTRECORD);
					throw exception;
				} else {
					pk = getLineIdValue(entity);
					Operators os = new Operators();
					os.addAddObject(entity);
					addSaveLineEx(context, entity, os);
					dao.execute(os);
					
				}
			}
		} catch (Exception e) {
			processFailure(context, e, true, pk);
			return forwardMethod(ADDLINE_METHOD);
		}
		processSuccess(context, true, pk);
		return forwardMethod(VIEWLINE_METHOD);
	}

	@Override
	public void saveAddEx(ActionContext context) {
		// TODO Auto-generated method stub
		TrphtmlcateForm form = (TrphtmlcateForm) context.getForm();
		form.setContent(null);
		form.setName(null);
		form.setJsname(null);
		form.setProcessclass(null);
		form.setWheres(null);
	}
	public IStrutsForward test(ActionContext context) {
		TrphtmlcateForm form = (TrphtmlcateForm) context.getForm();
		try {
			Trphtmlctrl po=(Trphtmlctrl)dao.get(Trphtmlctrl.class,form.getTrphtmlctrlid());
			
			Trpreportcondition condition = new Trpreportcondition();
			condition.setName(po.getName());
			condition.setJsname(po.getJsname());
			condition.setWheres(po.getWheres());
			condition.setHtmlcontent(po.getContent());
			condition.setProcessclass(po.getProcessclass());
			HtmlProcess hp = (HtmlProcess) WebUtil.getBean(condition.getProcessclass(), context.getRequest());
			String htmlvalue = hp.processHtml(condition, context.getRequest());
					
			form.setContent(htmlvalue);

		} catch (Exception e) {
			SystemLogger.error("产生HTML时出现错误",e);
		}
		return this.forwardJsp("test");
	}
	
	
	public String print(Map map) {
		if (map == null) {
			return "";
		}
		if (map.size() == 0) {
			return "";
		}
		Iterator it = map.keySet().iterator();
		StringBuffer str = new StringBuffer();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = (String) map.get(key);
			str.append(" | ");
			str.append(key);
			str.append(":");
			str.append(value);

		}
		str.append(" | ");
		return str.toString();
	}

	public IStrutsForward testSql(ActionContext context) {
		
		TrphtmlcateForm form = (TrphtmlcateForm) context.getForm();
		try {
            Trphtmlctrl po=(Trphtmlctrl)dao.get(Trphtmlctrl.class,form.getTrphtmlctrlid());
			Trpreportcondition condition = new Trpreportcondition();
			condition.setName(po.getName());
			condition.setJsname(po.getJsname());
			condition.setWheres(po.getWheres());
			condition.setHtmlcontent(po.getContent());
			condition.setProcessclass(po.getProcessclass());
			HtmlProcess hp = (HtmlProcess) WebUtil.getBean(condition.getProcessclass(), context.getRequest());
			Map<String, String> result = hp.processValue(condition, context.getRequest());
			String printStr = this.print(result);
			form.setSql(printStr);

		} catch (Exception e) {
			SystemLogger.error("产生SQL时出现错误",e);
		}
		return this.forwardMethod("test");
	}
	
	//得到
	public IStrutsForward getInfo(ActionContext context) {
		try {
			Map map = new HashMap();
			TrphtmlcateForm form = (TrphtmlcateForm) context.getForm();
			StringBuffer sql = new StringBuffer("from Trphtmlctrl where 1 =1 ");
			if (StringUtil.isNotBlank(form.getTrphtmlctrlid()+"")) {
				sql.append("and trphtmlctrlid = ");
				sql.append(form.getTrphtmlctrlid());
			}
			Trphtmlctrl po= (Trphtmlctrl)dao.get(sql.toString());
			map.put("jsname", po.getJsname());
			map.put("processclass", po.getProcessclass());
			map.put("wheres", po.getWheres());
			map.put("content", po.getContent());
			processSuccess(context, false);
			return this.forwardJson(map);
		} catch (Exception e) {
			processFailure(context, e, false);
			return this.forwardJsp(MAIN_PAGE);
		}
	}
}
