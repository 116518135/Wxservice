package com.wxservice.service.system;

import java.util.List;

import com.wxservice.database.po.system.Tsysdatadict;
import com.wxservice.database.po.system.Tsysdatadictdtl;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDLineOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.system.TsysdatadictForm;

/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class TsysdatadictManager extends CRUDLineOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TsysdatadictManager() {

	}

	public Class getEntityClass() {
		return Tsysdatadict.class;
	}

	public String getTableKeyField() {
		return "tsysdatadictid";
	}

	public Class getLineEntityClass() throws BusinessException {
		return Tsysdatadictdtl.class;
	}

	/**
	 * 得到明细表的主键名称
	 * 
	 * @return
	 */
	public String getLineTableKeyField() {
		return "tsysdatadictdtlid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tsysdatadict po = (Tsysdatadict) object;
		TsysdatadictForm form = (TsysdatadictForm) baseform;
		form.setTsysdatadictid(po.getTsysdatadictid());
		form.setCode(po.getCode());
		form.setName(po.getName());
		form.setMemo(po.getMemo());
	

	}

	public void bindEntity(BaseForm baseform, Object object) {

		Tsysdatadict po = (Tsysdatadict) object;
		TsysdatadictForm form = (TsysdatadictForm) baseform;
		po.setTsysdatadictid(form.getTsysdatadictid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		po.setMemo(form.getMemo());
	
	}

	public void bindLineForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tsysdatadictdtl po = (Tsysdatadictdtl) object;
		TsysdatadictForm form = (TsysdatadictForm) baseform;
		form.setTsysdatadictdtlid(po.getTsysdatadictdtlid());
		form.setTsysdatadictid(po.getTsysdatadictid());
		form.setCode(po.getCode());
		form.setName(po.getName());
		form.setMemo(po.getMemo());
	
	}

	public void bindLineEntity(BaseForm baseform, Object object) {
		Tsysdatadictdtl po = (Tsysdatadictdtl) object;
		TsysdatadictForm form = (TsysdatadictForm) baseform;
		po.setTsysdatadictdtlid(form.getTsysdatadictdtlid());
		po.setTsysdatadictid(form.getTsysdatadictid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		po.setMemo(form.getMemo());
		
	}

	public boolean isUniques(Object entity) throws Exception {
		Tsysdatadict po = (Tsysdatadict) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysdatadict as a where a.code='");
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
		Tsysdatadict po = (Tsysdatadict) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysdatadict as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.tsysdatadictid!=");
		hql.append(po.getTsysdatadictid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}

}
