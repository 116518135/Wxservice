package com.wxservice.service.base;
import com.wxservice.database.po.regedit.Tfeedback;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.base.TfeedbackForm;
/**
 * 
 * 描述：   Services
 * 创建人：Gererator
 */
public class TfeedbackManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;
	public TfeedbackManager(){

	}
	public Class getEntityClass() {
		return Tfeedback.class;
	}
	public String getTableKeyField() {
		return "tfeedbackid";
	}
	

	public void bindForm(BaseForm baseform, Object object) {
		if(object==null) return;
		Tfeedback po= (Tfeedback)object;
		TfeedbackForm    form=(TfeedbackForm)baseform;
		form.setTfeedbackid(po.getTfeedbackid());
				   form.setUsercode(po.getUsercode());
				   form.setContent(po.getContent());
				   form.setTime(po.getTime());
				
	}
	
	public void bindEntity(BaseForm baseform, Object object) {
	
		Tfeedback po= (Tfeedback)object;
		TfeedbackForm    form=(TfeedbackForm)baseform;
		po.setTfeedbackid(form.getTfeedbackid());
				   po.setUsercode(form.getUsercode());
				   po.setContent(form.getContent());
				   po.setTime(form.getTime());
				
	}
	
	
	
}


    