package com.wxservice.service.system;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wxservice.database.po.system.Tsysbillnorule;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;
import com.wxservice.web.form.system.TsysbillnoruleForm;

/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class TsysbillnoruleManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TsysbillnoruleManager() {

	}

	public Class getEntityClass() {
		return Tsysbillnorule.class;
	}

	public String getTableKeyField() {
		return "tsysbillnoruleid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tsysbillnorule po = (Tsysbillnorule) object;
		TsysbillnoruleForm form = (TsysbillnoruleForm) baseform;
		form.setTsysbillnoruleid(po.getTsysbillnoruleid());
		form.setCode(po.getCode());
		form.setName(po.getName());
		form.setMark(po.getMark());
		form.setBilltype(po.getBilltype());
		form.setNumlength(po.getNumlength());
		form.setCounttype(po.getCounttype());
		form.setCurcount(po.getCurcount());
		form.setTbscmpid(po.getTbscmpid());
		form.setCreateusername(po.getCreateusername());
		form.setCreatedate(po.getCreatedate());
		form.setCreatetbscmpid(po.getCreatetbscmpid());
		form.setEditusername(po.getEditusername());
		form.setEditdate(po.getEditdate());

	}
	@Override
	public void createListHqlWhere(StringBuffer hql, ActionContext context) {
		hql.append(" order by entity.code ");
	}

	public void bindEntity(BaseForm baseform, Object object) {

		Tsysbillnorule po = (Tsysbillnorule) object;
		TsysbillnoruleForm form = (TsysbillnoruleForm) baseform;
		po.setTsysbillnoruleid(form.getTsysbillnoruleid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		po.setMark(form.getMark());
		po.setBilltype(form.getBilltype());
		po.setNumlength(form.getNumlength());
		po.setCounttype(form.getCounttype());
		po.setCurcount(form.getCurcount());
		po.setEditdate(form.getEditdate());
	}

	public boolean isUniques(Object entity) throws Exception {
		Tsysbillnorule po = (Tsysbillnorule) entity;
		ClientSession session=WebUtil.getClientSession();
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysbillnorule as a where a.code='");
		hql.append(po.getCode());
		hql.append("'  ");
		Object o = dao.get(hql.toString());
		if (o == null) {
			return true;
		} else {
			return false;
		}

	}

	public void updateSaveEx(ActionContext context, Object entity, Operators os) {
		Tsysbillnorule po = (Tsysbillnorule) entity;
		ClientSession session=WebUtil.getClientSession();
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysbillnorule as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and tsysbillnoruleid!=");
		hql.append(po.getTsysbillnoruleid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}
	
	public static final int recounttype0 = 0;// 每天从1天始计数
	public static final int recounttype1 = 1;// 每月从1天始计数
	public static final int recounttype2 = 2;// 每年从1天始计数
	public static final int recounttype3 = 3;// 到达最大号后从1开始计数

	public List<LabelValue> getCounttypeOptions() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		LabelValue t = new LabelValue("每天从1天始计数", recounttype0);
		options.add(t);
		t = new LabelValue("每月从1天始计数",
				recounttype1);
		options.add(t);
		t = new LabelValue("每年从1天始计数",
				recounttype2);
		options.add(t);
		t = new LabelValue("到达最大号后从1开始计数",
				recounttype3);
		options.add(t);
		return options;
	}
	
	public static final int notype0 = 0;// 标识+单据标识+年+月+日+流水号
	public static final int notype1 = 1;// 标识+单据标识+年+月+流水号
	public static final int notype2 = 2;// 标识+单据标识+年+流水号
	public static final int notype3 = 3;// 标识+单据标识+流水号

	public List<LabelValue> getBilltypeOptions() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		LabelValue t = new LabelValue("公司标识+单据标识+年+月+日+流水号", notype0);
		options.add(t);
		t = new LabelValue("公司标识+单据标识+年+月+流水号",
				notype1);
		options.add(t);
		t = new LabelValue("公司标识+单据标识+年+流水号",
				notype2);
		options.add(t);
		t = new LabelValue("公司标识+单据标识+流水号",
				notype3);
		options.add(t);
		return options;
	}
	
	public void addSaveEx(ActionContext context, Object entity, Operators os) {
		Tsysbillnorule po = (Tsysbillnorule) entity;
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		po.setCreateusername(client.getUsername());
		po.setCreatedate(new Date());
		po.setCreatetbscmpid(client.getCmpid());
		po.setEditusername(client.getUsername());
	}
	@Override
	public void viewEx(ActionContext context, Object entity) {
		initFormCombo(context);
		initFormName(context,entity);
	}
	@Override
	public void initFormCombo(ActionContext context) {
		TsysbillnoruleForm form = (TsysbillnoruleForm) context.getForm();
        form.setBilltypeOptions(this.getBilltypeOptions());
        form.setCounttypeOptions(this.getCounttypeOptions());
	}
	
	/*public void copydata(Integer cmpid){//复制数据，并将计数据器置1
		if(cmpid.intValue()==SysFinal.SYSTEM_ZGSID) return;
		String hql="from Tsysbillnorule as a where 1=1 ";//总部的数据
		List list=dao.iterate(hql);
		Operators os=new Operators();
		for(Object o:list){
			Tsysbillnorule ir=(Tsysbillnorule)o;
			StringBuffer checkSql=new StringBuffer();
			checkSql.append("from Tsysbillnorule as a where a.code='");
			checkSql.append(ir.getCode()).append("' ");
			Object m=dao.get(checkSql.toString());
			if(m==null){
				ir.setCreatetbscmpid(cmpid);
				ir.setCurcount(0);
				os.addAddObject(ir);
			}
		}
		dao.execute(os);
	}*/
	
	
}
