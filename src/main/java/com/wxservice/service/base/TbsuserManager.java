package com.wxservice.service.base;

import java.util.List;
import java.util.Map;

import com.wxservice.database.po.base.Tbsuser;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.Md5;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.web.form.base.TbsuserForm;

/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class TbsuserManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TbsuserManager() {

	}

	public Class getEntityClass() {
		return Tbsuser.class;
	}

	public String getTableKeyField() {
		return "tbsuserid";
	}
	

	@Override
	public void createListHqlWhere(StringBuffer hql, ActionContext context) {
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		TbsuserForm form = (TbsuserForm) context.getForm();
		hql.append(" and tbsuserid!=-1");
		
	}

	@Override
	public void listEx(ActionContext context, Map model, Object entity)
			throws Exception {

		SysUtil.copyProperties(model, entity);
		Tbsuser po = (Tbsuser) entity;
	

	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tbsuser po = (Tbsuser) object;
		TbsuserForm form = (TbsuserForm) baseform;
		form.setTbsuserid(po.getTbsuserid());
		form.setCode(po.getCode());
		form.setName(po.getName());
		form.setPasswd("");
		form.setTsysroleids(po.getTsysroleids());
		form.setRolenames(po.getRolenames());
		form.setStopflag(po.getStopflag());

		


	}

	public void bindEntity(BaseForm baseform, Object object) {

		Tbsuser po = (Tbsuser) object;
		TbsuserForm form = (TbsuserForm) baseform;
		po.setTbsuserid(form.getTbsuserid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		if (MathUtil.equal(form.getPassflag(), 1)) {
			if (StringUtil.isNotBlank(form.getPasswd())) {
				po.setPasswd(Md5.md5(form.getPasswd()));
			} else {
				po.setPasswd("");
			}
		}
		po.setTsysroleids(form.getTsysroleids());
		po.setRolenames(form.getRolenames());
		po.setStopflag(form.getStopflag());



	}

	

	public void updateSaveEx(ActionContext context, Object entity, Operators os) {
		Tbsuser po = (Tbsuser) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tbsuser as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.tbsuserid!=");
		hql.append(po.getTbsuserid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}

	@Override
	public void addSaveEx(ActionContext context, Object entity, Operators os) {
		Tbsuser po = (Tbsuser) entity;
		TbsuserForm form = (TbsuserForm) context.getForm();
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		
		
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tbsuser as a where a.code='");
		hql.append(po.getCode());
		hql.append("' ");
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}

	@Override
	public void addEx(ActionContext context) {
		TbsuserForm form = (TbsuserForm) context.getForm();
		ClientSession client = WebUtil.getClientSession(context.getRequest());

		
	}

	public IStrutsForward updatePassword(ActionContext context) {
		token.saveToken(context.getRequest());// 保存token
		TbsuserForm form = (TbsuserForm) context.getForm();
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		form.setTbsuserid(client.getUserid());
		form.setOldpasswd("");
		form.setPasswd("");
		form.setPasswd1("");
		return forwardJsp("updatePassword");
	}

	public IStrutsForward updateSavePassword(ActionContext context) {
		String pk = null;
		TbsuserForm form = (TbsuserForm) context.getForm();
		try {
			if (token.isTokenValid(context.getRequest(), true)) {
				// 修改保存
				Tbsuser entity = (Tbsuser) getEntity(context);
				if (entity == null) {
					BusinessException exception = new BusinessException(
							NOEXISTRECORD);
					throw exception;
				} else {
					Operators os = new Operators();
					os.addUpdateObject(entity);
					String inputoldpasswd = StringUtil.notNull(form
							.getOldpasswd());
					String oldpasswd = StringUtil.notNull(entity.getPasswd());
					if (StringUtil.isNotBlank(inputoldpasswd)) {
						if (Md5.md5(inputoldpasswd).equals(oldpasswd)) {
							if (StringUtil.isBlank(form.getPasswd())) {
								entity.setPasswd("");
							} else {
								entity.setPasswd(Md5.md5(form.getPasswd()));
							}
						} else {
							BusinessException exception = new BusinessException(
									"原密码输入错误");
							throw exception;
						}
					} else {
						if (inputoldpasswd.equals(oldpasswd)) {
							if (StringUtil.isBlank(form.getPasswd())) {
								entity.setPasswd("");
							} else {
								entity.setPasswd(Md5.md5(form.getPasswd()));
							}
						} else {
							BusinessException exception = new BusinessException(
									"原密码输入错误");
							throw exception;
						}
					}

					dao.execute(os);
				}
			}
		} catch (Exception e) {
			processFailure(context, e, true, "修改密码");
			return forwardMethod("updatePassword");
		}
		processSuccess(context, true, "修改密码");
		return forwardMethod("updatePassword");
	}
}
