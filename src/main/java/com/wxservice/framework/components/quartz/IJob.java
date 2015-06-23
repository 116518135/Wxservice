package com.wxservice.framework.components.quartz;

import com.wxservice.database.po.system.Tsysjobplan;

public interface IJob {
	public void execute(Tsysjobplan plan);
}
