package com.wxservice.framework.components.quartz.support;

import com.wxservice.database.po.system.Tsysjobplan;
import com.wxservice.framework.components.quartz.IJob;
import com.wxservice.service.system.AboutManager;

public class BacukupDbJob implements IJob{
	AboutManager aboutManager=null;
	@Override
	public void execute(Tsysjobplan plan) {
		aboutManager.backupdatabase();
	}
	public AboutManager getAboutManager() {
		return aboutManager;
	}
	public void setAboutManager(AboutManager aboutManager) {
		this.aboutManager = aboutManager;
	}

}
