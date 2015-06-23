package com.wxservice.framework.components.quartz;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxservice.database.po.system.Tsysjob;
import com.wxservice.database.po.system.Tsysjobplan;
import com.wxservice.framework.engine.BaseEngine;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;

public class JobExecutor extends BaseEngine {

	public static Map<String, String> status = new HashMap<String, String>();

	public void execute() {
		if (serviceConfig.getIntConfig("ucard.job.flag") == 1) {
			SystemLogger.debug("开始JOB调用");
			List<Tsysjobplan> list = this.getJobplanList();
			for (Tsysjobplan plan : list) {
				String key = getKey(plan);
				int flag = serviceConfig.getIntConfig(key);// ucard.properties里面配置了不需要执行此计划
				if (flag == 0) {
					if (status.get(plan.getCode()) == null) {
						status.put(plan.getCode(), "1");
						Calendar cal = Calendar.getInstance();
						boolean weekflag = true;
						boolean monthflag = true;
						if (MathUtil.equal(plan.getFnflag(), 1)) {// 每周
							weekflag = checkWeek(plan, cal);
						} else if (MathUtil.equal(plan.getFnflag(), 2)) {// 每月
							monthflag = checkMonth(plan, cal);
						}
						if (weekflag && monthflag) {
							boolean dayflag = this.checkDay(plan, cal);
							if (dayflag) {
								Tsysjob job = (Tsysjob) dao.get(Tsysjob.class,
										plan.getTsysjobid());
								IJob executor = (IJob) WebUtil.getBean(job
										.getSpringname());

								SystemLogger.debug("开始执行:" + job.getName());
								executor.execute(plan);

							}
						}
						status.remove(plan.getCode());
					}
				}
			}
		}
	}

	private String getKey(Tsysjobplan job) {
		StringBuffer key = new StringBuffer("esk.job.");
		key.append(job.getCode());
		return key.toString();
	}

	private boolean checkWeek(Tsysjobplan p, Calendar cal) {
		boolean isWeek = false;
		int week = cal.get(Calendar.DAY_OF_WEEK);
		if (week == p.getMonday()) {
			isWeek = true;
		} else if (week == p.getTuesday()) {
			isWeek = true;
		} else if (week == p.getWednesday()) {
			isWeek = true;
		} else if (week == p.getThursday()) {
			isWeek = true;
		} else if (week == p.getFriday()) {
			isWeek = true;
		} else if (week == p.getSaturday()) {
			isWeek = true;
		} else if (week == p.getSunday()) {
			isWeek = true;
		}
		return isWeek;
	}

	private boolean checkMonth(Tsysjobplan p, Calendar cal) {
		boolean result = false;
		if (MathUtil.equal(p.getMonthflag(), 0)) {// 按照每日来
			int day = cal.get(Calendar.DATE);
			if (MathUtil.equal(day, p.getDayindex())) {
				result = true;
			}
		} else if (MathUtil.equal(p.getMonthflag(), 1)) {
			int week = cal.get(Calendar.WEEK_OF_MONTH);
			if (MathUtil.equal(week, p.getWeekindex())) {
				int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
				if (MathUtil.equal(dayofweek, p.getWeekweekindex())) {
					result = true;
				}
			}
		}
		return result;

	}

	private boolean checkDay(Tsysjobplan p, Calendar cal) {
		boolean result = false;
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		if (MathUtil.equal(p.getDayfnflag(), 0)) {// 一次发生
			if (MathUtil.equal(hour, p.getOnlyonehour())
					&& MathUtil.equal(minute, p.getOnlyonemin())) {
				result = true;
			}
		} else if (MathUtil.equal(p.getDayfnflag(), 1)) {// 多次发生
			int begin = p.getManybeginhour() * 60 + p.getManybeginmin();
			int end = p.getManyendhour() * 60 + p.getManyendmin();
			int curr = hour * 60 + minute;
			if (curr >= begin && curr <= end) {
				if (MathUtil.notEqual(p.getHourminindex(), 0)) {
					if (MathUtil.equal(p.getHourminflag(), 0)) {// 每X分钟执行一次
						result = minute % p.getHourminindex() == 0;
					} else if (MathUtil.equal(p.getHourminflag(), 1)) {// 每X小时执行一次
						int second = cal.get(Calendar.SECOND);
						result = hour % p.getHourminindex() == 0 && minute == 0
								&& second == 0;
					}
				}
			}
		}
		return result;
	}
	private List<Tsysjobplan> getJobplanList() {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysjobplan as a where ifnull(a.stopflag,0)=0  order by a.indexno");
		List<Tsysjobplan> list = dao.iterate(hql.toString());
		return list;
	}
}
