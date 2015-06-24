package com.wxservice.service.regedit;
import com.wxservice.database.po.regedit.Treguser;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.Md5;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.regedit.TreguserForm;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class TreguserManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TreguserManager() {

	}

	public Class getEntityClass() {
		return Treguser.class;
	}

	public String getTableKeyField() {
		return "treguserid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Treguser po = (Treguser) object;
		TreguserForm form = (TreguserForm) baseform;
		form.setTreguserid(po.getTreguserid());
		form.setCode(po.getCode());
		form.setPasswd(po.getPasswd());
		form.setEmail(po.getEmail());
		form.setMobilephone(po.getMobilephone());
		form.setQqflag(po.getQqflag());
		form.setSinaflag(po.getSinaflag());
		form.setStopflag(po.getStopflag());
		form.setMemo(po.getMemo());
		form.setEmailflag(po.getEmailflag());
        form.setRegplatform(po.getRegplatform());
        form.setSeetabflag(po.getSeetabflag());
        form.setName(po.getName());
        form.setRoleids(po.getRoleids());
        form.setRolenames(po.getRolenames());
	}

	public void bindEntity(BaseForm baseform, Object object) {
		Treguser po = (Treguser) object;
		TreguserForm form = (TreguserForm) baseform;
		po.setTreguserid(form.getTreguserid());
		po.setCode(form.getCode());
		if (StringUtil.isNotBlank(form.getPasswd())) {
			if (MathUtil.equal(form.getPasswdflag(), 1)) {
	               po.setPasswd(Md5.md5(form.getPasswd()));
				}
		}else{
			po.setPasswd("");
		}
		po.setEmail(form.getEmail());
		po.setMobilephone(form.getMobilephone());
		po.setQqflag(form.getQqflag());
		po.setSinaflag(form.getSinaflag());
		po.setStopflag(form.getStopflag());
		po.setMemo(form.getMemo());
		po.setEmailflag(form.getEmailflag());
        po.setRegplatform(form.getRegplatform());
        po.setSeetabflag(form.getSeetabflag());
        po.setName(form.getName());
        po.setRoleids(form.getRoleids());
        po.setRolenames(form.getRolenames());
	}

	public boolean isUniques(Object entity) throws Exception {
		Treguser po = (Treguser) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Treguser as a where a.code='");
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
		Treguser po = (Treguser) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Treguser as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.treguserid!=");
		hql.append(po.getTreguserid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}
	
	@Override
	public void addSaveEx(ActionContext context, Object entity, Operators os) {
		Treguser po = (Treguser) entity;
		po.setCreatetime(new Date());
		
	}
	
	@Override
	public void viewEx(ActionContext context, Object entity) {

		try{
			Treguser po = (Treguser) entity;
			TreguserForm form = (TreguserForm) context.getForm();
			if(MathUtil.equal(po.getPhonetype(), 1)) {
				form.setPhonetypename("Android");
			}else if(MathUtil.equal(po.getPhonetype(), 2))  {
				form.setPhonetypename("Ios");
			}else {
				form.setPhonetypename("");
			}
			form.setRegplatformname("");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void listEx(ActionContext context, Map model, Object entity)
			throws Exception {
    
		Treguser po = (Treguser) entity;
		model.put("treguserid", po.getTreguserid());
		model.put("name", po.getName());
	    model.put("code", po.getCode());
		model.put("email", po.getEmail());
		model.put("mobilephone", po.getMobilephone());
		model.put("qqflag", po.getQqflag());
		model.put("sinaflag", po.getSinaflag());
		model.put("memo", po.getMemo());
		model.put("emailflag", po.getEmailflag());
		if(MathUtil.equal(po.getPhonetype(), 1)) {
			model.put("phonetypename", "Android");
		}else if(MathUtil.equal(po.getPhonetype(), 2))  {
			model.put("phonetypename", "Ios");
		}else {
			model.put("phonetypename", "");
		}
		
		model.put("regplatformname", "");
	}

	/** 
     * 处理找回密码 
     * @param usercode 帐号 
     * @param newpasswd 新密码
     */  
    public IStrutsForward findPasswd(ActionContext context){  
       String usercode=context.getRequest().getParameter("a");
       String newpasswd=context.getRequest().getParameter("b");
       String timestamp=context.getRequest().getParameter("c");
       Long sendtime = Long.valueOf(Md5.JM(timestamp));
       Long conftime = System.currentTimeMillis();
       if(conftime-sendtime>24*60*60){
    	   context.getRequest().setAttribute("errorMessage", "该链接已过期,请重新找回密码！");
    	   return forwardJsp("findpasswd_failure");
       }
       StringBuffer hql=new StringBuffer();
       hql.append(" from Treguser where code='").append(usercode).append("'");
       Treguser user=(Treguser)dao.get(hql.toString());
       if(user==null){
    	   context.getRequest().setAttribute("errorMessage", "该用户不存在！");
    	   return forwardJsp("findpasswd_failure");
       }      
       user.setPasswd(newpasswd);
       dao.updateSave(user);      
       return forwardJsp("findpasswd_success");
      
    }
}
