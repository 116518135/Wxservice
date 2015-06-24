package com.wxservice.service.system;


import com.wxservice.database.po.system.Tsysjob;
import com.wxservice.database.po.system.Tsysjobplan;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.system.TsysjobplanForm;

import java.util.List;
import java.util.Map;


/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class TsysjobplanManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TsysjobplanManager() {

	}

	public Class getEntityClass() {
		return Tsysjobplan.class;
	}

	public String getTableKeyField() {
		return "tsysjobplanid";
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tsysjobplan po = (Tsysjobplan) object;
		TsysjobplanForm form = (TsysjobplanForm) baseform;
		form.setTsysjobplanid(po.getTsysjobplanid());
		form.setCode(po.getCode());
		form.setName(po.getName());
		form.setFnflag(po.getFnflag());
		form.setDayfnflag(po.getDayfnflag());
		form.setOnlyonehour(po.getOnlyonehour());
		form.setOnlyonemin(po.getOnlyonemin());
		form.setMonthflag(po.getMonthflag());
		form.setManybeginhour(po.getManybeginhour());
		form.setManybeginmin(po.getManybeginmin());
		form.setManyendhour(po.getManyendhour());
		form.setManyendmin(po.getManyendmin());
		form.setHourminindex(po.getHourminindex());
		form.setHourminflag(po.getHourminflag());
		form.setMonday(po.getMonday());
		form.setTuesday(po.getTuesday());
		form.setWednesday(po.getWednesday());
		form.setThursday(po.getThursday());
		form.setFriday(po.getFriday());
		form.setSaturday(po.getSaturday());
		form.setSunday(po.getSunday());
		form.setDayindex(po.getDayindex());
		form.setWeekindex(po.getWeekindex());
		form.setWeekweekindex(po.getWeekweekindex());
		form.setMemo(po.getMemo());
		form.setCreateusername(po.getCreateusername());
		form.setCreatedate(po.getCreatedate());
		form.setCreatetbscmpid(po.getCreatetbscmpid());
		form.setEditusername(po.getEditusername());
		form.setEditdate(po.getEditdate());
		form.setTsysjobid(po.getTsysjobid());
		form.setIndexno(po.getIndexno());
		form.setStopflag(po.getStopflag());
		form.setAdd1(po.getAdd1());
		form.setAdd2(po.getAdd2());
		form.setAdd3(po.getAdd3());
		form.setAdd4(po.getAdd4());
		form.setAdd5(po.getAdd5());

	}

	public void bindEntity(BaseForm baseform, Object object) {

		Tsysjobplan po = (Tsysjobplan) object;
		TsysjobplanForm form = (TsysjobplanForm) baseform;
		po.setTsysjobplanid(form.getTsysjobplanid());
		po.setCode(form.getCode());
		po.setName(form.getName());
		po.setFnflag(form.getFnflag());
		po.setDayfnflag(form.getDayfnflag());
		po.setOnlyonehour(form.getOnlyonehour());
		po.setOnlyonemin(form.getOnlyonemin());
		po.setMonthflag(form.getMonthflag());
		po.setManybeginhour(form.getManybeginhour());
		po.setManybeginmin(form.getManybeginmin());
		po.setManyendhour(form.getManyendhour());
		po.setManyendmin(form.getManyendmin());
		po.setHourminindex(form.getHourminindex());
		po.setHourminflag(form.getHourminflag());
		po.setMonday(form.getMonday());
		po.setTuesday(form.getTuesday());
		po.setWednesday(form.getWednesday());
		po.setThursday(form.getThursday());
		po.setFriday(form.getFriday());
		po.setSaturday(form.getSaturday());
		po.setSunday(form.getSunday());
		po.setDayindex(form.getDayindex());
		po.setWeekindex(form.getWeekindex());
		po.setWeekweekindex(form.getWeekweekindex());
		po.setMemo(form.getMemo());
		po.setCreateusername(form.getCreateusername());
		po.setCreatedate(form.getCreatedate());
		po.setCreatetbscmpid(form.getCreatetbscmpid());
		po.setEditusername(form.getEditusername());
		po.setEditdate(form.getEditdate());
		po.setTsysjobid(form.getTsysjobid());
		po.setIndexno(form.getIndexno());
		po.setStopflag(form.getStopflag());
		
		po.setAdd1(form.getAdd1());
		po.setAdd2(form.getAdd2());
		po.setAdd3(form.getAdd3());
		po.setAdd4(form.getAdd4());
		po.setAdd5(form.getAdd5());

	}

	public boolean isUniques(Object entity) throws Exception {
		Tsysjobplan po = (Tsysjobplan) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysjobplan as a where a.code='");
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
		Tsysjobplan po = (Tsysjobplan) entity;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysjobplan as a where a.code='");
		hql.append(po.getCode());
		hql.append("' and a.tsysjobplanid!=");
		hql.append(po.getTsysjobplanid());
		List list = dao.iterate(hql.toString());
		if (list.size() > 0) {
			BusinessException exception = new BusinessException("编号重复");
			throw exception;
		}
	}

	public void listEx(ActionContext context, Map model, Object entity)
			throws Exception {
		Tsysjobplan po = (Tsysjobplan) entity;
		model.put("tsysjobplanid", po.getTsysjobplanid());
		model.put("code", po.getCode());
		model.put("name", po.getName());
		model.put("memo", po.getMemo());
		model.put("indexno",po.getIndexno());
		model.put("stopflag", po.getStopflag());
		model.put("fnflagname", this.getFnflagName(po.getFnflag()));
		String jobname = nameManager.getName(Tsysjob.class, po.getTsysjobid(),
				"name");
		model.put("jobname", jobname);
	}
	@Override
	public void createListHqlWhere(StringBuffer hql, ActionContext context) {
		hql.append(" order by entity.indexno");
	}
	@Override
	public void initFormName(ActionContext context,Object entity) {
		Tsysjobplan po = (Tsysjobplan) entity;
		String jobname = nameManager.getName(Tsysjob.class, po.getTsysjobid(),
		"name");
		TsysjobplanForm form = (TsysjobplanForm) context.getForm();
		form.setJobname(jobname);
	}
	@Override
	public void viewEx(ActionContext context, Object entity) {
		initFormName(context,entity);
	}
	
	public String getFnflagName(Integer fnflag){
	    if(MathUtil.equal(fnflag, 0)){
	    	return "每天";
	    }else if(MathUtil.equal(fnflag, 1)){
	    	return "每周";
	    }else if(MathUtil.equal(fnflag, 2)){
	    	return "每月";
	    }
	    return "";
	}
}
