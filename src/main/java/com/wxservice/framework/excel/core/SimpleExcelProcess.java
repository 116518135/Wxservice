package com.wxservice.framework.excel.core;

import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.excel.IExcelProcess;
import com.wxservice.service.NameManager;

public abstract class SimpleExcelProcess implements IExcelProcess {
	IDao dao = null;
	NameManager nameManager = null;

	
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


}
