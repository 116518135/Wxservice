package com.wxservice.service.system;
import com.wxservice.database.po.system.Tsysjob;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.system.TsysjobForm;

import java.util.List;
/**  
 * 
 * 描述：   Services
 * 创建人：Gererator
 */
public class TsysjobManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;
	public TsysjobManager(){

	}
	public Class getEntityClass() {
		return Tsysjob.class;
	}
	public String getTableKeyField() {
		return "tsysjobid";
	}
	

	public void bindForm(BaseForm baseform, Object object) {
		if(object==null) return;
		Tsysjob po= (Tsysjob)object;
		TsysjobForm    form=(TsysjobForm)baseform;
		form.setTsysjobid(po.getTsysjobid());
				   form.setCode(po.getCode());
				   form.setName(po.getName());
				   form.setSpringname(po.getSpringname());
				   form.setMemo(po.getMemo());
				
	}
	
	public void bindEntity(BaseForm baseform, Object object) {
	
		Tsysjob po= (Tsysjob)object;
		TsysjobForm    form=(TsysjobForm)baseform;
		po.setTsysjobid(form.getTsysjobid());
				   po.setCode(form.getCode());
				   po.setName(form.getName());
				   po.setSpringname(form.getSpringname());
				   po.setMemo(form.getMemo());
				
	}
	
	public boolean isUniques(Object entity) throws Exception{
		Tsysjob po=(Tsysjob)entity;
		StringBuffer hql=new StringBuffer();
		hql.append(" from Tsysjob as a where a.code='");
		hql.append(po.getCode());
		hql.append("'");
		Object o=dao.get(hql.toString());
		if(o==null){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public void updateSaveEx(ActionContext context, Object entity,
			Operators os) {
		Tsysjob po=(Tsysjob)entity;
		StringBuffer hql=new StringBuffer();
		hql.append(" from Tsysjob as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.tsysjobid!=");
		hql.append(po.getTsysjobid());
		List list=dao.iterate(hql.toString());
		if(list.size()>0){
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}	
	
}


