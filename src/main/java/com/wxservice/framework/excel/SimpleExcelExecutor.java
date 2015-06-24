package com.wxservice.framework.excel;

import java.util.List;

import com.wxservice.database.po.system.Tsysexcel;
import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.dao.IDao;
import com.wxservice.service.NameManager;

public abstract class SimpleExcelExecutor {
	IDao dao = null;
	NameManager nameManager = null;
	public abstract Object execute(ExcelContext context) throws Exception ;
	
	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public NameManager getNameManager() {
		return nameManager;
	}

	public void setNameManager(NameManager nameManager) {
		this.nameManager = nameManager;
	}

	

	public Tsysexcel getMaster(String code) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysexcel as a where a.code='");
		hql.append(code);
		hql.append("'");
		return (Tsysexcel) dao.get(hql.toString());
	}

	public List<Tsysexceldtl> getDtllist(Integer tsysexcelid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysexceldtl as a where a.tsysexcelid=");
		hql.append(tsysexcelid);
		hql.append(" order by colindex");
		return dao.iterate(hql.toString());
	}

	public int getBeingRow() {
		return ExcelUtil.BEGINROW;
	}

	
}
