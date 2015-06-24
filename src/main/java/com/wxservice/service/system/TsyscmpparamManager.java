package com.wxservice.service.system;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxservice.database.po.system.Tsyscmpparam;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.RequestUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.session.ClientSession;

/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class TsyscmpparamManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TsyscmpparamManager() {
		LabelValue p=null;
		p = new LabelValue("cmp_neardistance", "500");
		defaultparams.add(p);
		p = new LabelValue("taskcode","01");
		defaultparams.add(p);
		p = new LabelValue("ifpass", "60");
		defaultparams.add(p);
		
	}

	
	
	public Class getEntityClass() {
		return Tsyscmpparam.class;
	}

	public String getTableKeyField() {
		return "tsyscmpparamid";
	}

	List<LabelValue> defaultparams = new ArrayList<LabelValue>();

	public IStrutsForward edit(ActionContext context) {
		try {
			token.saveToken(context.getRequest());
			initFormCombo(context);
			Map form = new HashMap();
			for (LabelValue v : defaultparams) {
				String value = nameManager.getStrCmpparam(v.getLabel(),"");
				if(StringUtil.isNotBlank(value)) {
					form.put(v.getLabel(), value);
				} else {
					form.put(v.getLabel(), v.getValue());
				}
			}
			context.getRequest().setAttribute("form", form);
		} catch (Exception e) {
			processFailure(context, e, false);
			return forwardJsp(EDIT_PAGE);
		}
		processSuccess(context, false);
		return forwardJsp(EDIT_PAGE);
	}

	public IStrutsForward editSave(ActionContext context) {
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				ClientSession client=WebUtil.getClientSession(context.getRequest());
				Operators os=new Operators();
				for (LabelValue v : defaultparams) {
					Tsyscmpparam po=nameManager.getTsyscmpparam(v.getLabel());
					if(po==null){
						po=new Tsyscmpparam();
						po.setParamname(v.getLabel());
						os.addAddObject(po);
					}else{
						os.addUpdateObject(po);
					}
					po.setEditdate(new Date());
					String value =RequestUtil.getParameter(context.getRequest(), v.getLabel());
					if(StringUtil.isNotBlank(value)) {
						po.setParamvalue(value);
					}else{
						po.setParamvalue(v.getValue());
					}
				}
				dao.execute(os);
			}
			
		} catch (Exception e) {
			processFailure(context, e, true, "保存参数");
			return forwardMethod("edit");
		}
		processSuccess(context, true, "保存参数");
		return forwardMethod("edit");
	}

}
