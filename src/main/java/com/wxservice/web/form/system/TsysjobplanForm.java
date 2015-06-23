package com.wxservice.web.form.system;
import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述：   Struts Form
 * 创建人：Gererator
 */

public class TsysjobplanForm extends BaseForm

{
	private static final long serialVersionUID = -1L;
	public TsysjobplanForm(){
		this.setService("tsysjobplanManager");
		this.setModuleTitle("调度计划表");
		this.setAnonymous("0");
	}
	
    Integer  tsysjobplanid;
    String  code;
    String  name;
    Integer  fnflag=0;
    Integer  dayfnflag=0;
    Integer  onlyonehour;
    Integer  onlyonemin;
    Integer  monthflag=0;
    Integer  manybeginhour;
    Integer  manybeginmin;
    Integer  manyendhour;
    Integer  manyendmin;
    Integer  hourminindex;
    Integer  hourminflag=0;
    Integer  monday;
    Integer  tuesday;
    Integer  wednesday;
    Integer  thursday;
    Integer  friday;
    Integer  saturday;
    Integer  sunday;
    Integer  dayindex;
    Integer  weekindex;
    Integer  weekweekindex;
    String  memo;
    String  createusername;
    java.util.Date  createdate;
    Integer  createtbscmpid;
    String  editusername;
    java.util.Date  editdate;
    Integer  tsysjobid;
    String jobname;
    Integer  indexno=1;
    Integer  stopflag=0;
    
    String add1;
    String add2;
    String add3;
    String add4;
    String add5;

    public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getAdd3() {
		return add3;
	}
	public void setAdd3(String add3) {
		this.add3 = add3;
	}
	public String getAdd4() {
		return add4;
	}
	public void setAdd4(String add4) {
		this.add4 = add4;
	}
	public String getAdd5() {
		return add5;
	}
	public void setAdd5(String add5) {
		this.add5 = add5;
	}
	public Integer getTsysjobplanid () {
       return tsysjobplanid;
    }
    public void setTsysjobplanid(Integer tsysjobplanid) {
       this.tsysjobplanid = tsysjobplanid;
    }
   /**
    * @return 返回 计划编号
    */
    public String getCode () {
       return code;
    }
	
   /**
    * @param code 要设置的 计划编号
    */
    public void setCode(String code) {
      this.code = code;
    }
   /**
    * @return 返回 计划名称
    */
    public String getName () {
       return name;
    }
	
   /**
    * @param name 要设置的 计划名称
    */
    public void setName(String name) {
      this.name = name;
    }
   /**
    * @return 返回 发生频率
    */
    public Integer getFnflag () {
       return fnflag;
    }
	
   /**
    * @param fnflag 要设置的 发生频率
    */
    public void setFnflag(Integer fnflag) {
      this.fnflag = fnflag;
    }
   /**
    * @return 返回 每天发生频率
    */
    public Integer getDayfnflag () {
       return dayfnflag;
    }
	
   /**
    * @param dayfnflag 要设置的 每天发生频率
    */
    public void setDayfnflag(Integer dayfnflag) {
      this.dayfnflag = dayfnflag;
    }
   /**
    * @return 返回 小时
    */
    public Integer getOnlyonehour () {
       return onlyonehour;
    }
	
   /**
    * @param onlyonehour 要设置的 小时
    */
    public void setOnlyonehour(Integer onlyonehour) {
      this.onlyonehour = onlyonehour;
    }
   /**
    * @return 返回 分
    */
    public Integer getOnlyonemin () {
       return onlyonemin;
    }
	
   /**
    * @param onlyonemin 要设置的 分
    */
    public void setOnlyonemin(Integer onlyonemin) {
      this.onlyonemin = onlyonemin;
    }

   /**
    * @return 返回 发生多次的开始小时
    */
    public Integer getManybeginhour () {
       return manybeginhour;
    }
	
   /**
    * @param manybeginhour 要设置的 发生多次的开始小时
    */
    public void setManybeginhour(Integer manybeginhour) {
      this.manybeginhour = manybeginhour;
    }
   /**
    * @return 返回 发生多次的开始分钟
    */
    public Integer getManybeginmin () {
       return manybeginmin;
    }
	
   /**
    * @param manybeginmin 要设置的 发生多次的开始分钟
    */
    public void setManybeginmin(Integer manybeginmin) {
      this.manybeginmin = manybeginmin;
    }
   /**
    * @return 返回 发生多次的结束小时
    */
    public Integer getManyendhour () {
       return manyendhour;
    }
	
   /**
    * @param manyendhour 要设置的 发生多次的结束小时
    */
    public void setManyendhour(Integer manyendhour) {
      this.manyendhour = manyendhour;
    }
   /**
    * @return 返回 发生多次的结束分钟
    */
    public Integer getManyendmin () {
       return manyendmin;
    }
	
   /**
    * @param manyendmin 要设置的 发生多次的结束分钟
    */
    public void setManyendmin(Integer manyendmin) {
      this.manyendmin = manyendmin;
    }
   /**
    * @return 返回 第X(小时,分钟)执行一次
    */
    public Integer getHourminindex () {
       return hourminindex;
    }
	
   /**
    * @param hourminindex 要设置的 第X(小时,分钟)执行一次
    */
    public void setHourminindex(Integer hourminindex) {
      this.hourminindex = hourminindex;
    }
   /**
    * @return 返回 小时，分钟标志
    */
    public Integer getHourminflag () {
       return hourminflag;
    }
	
   /**
    * @param hourminflag 要设置的 小时，分钟标志
    */
    public void setHourminflag(Integer hourminflag) {
      this.hourminflag = hourminflag;
    }
   /**
    * @return 返回 周一
    */
    public Integer getMonday () {
       return monday;
    }
	
   /**
    * @param monday 要设置的 周一
    */
    public void setMonday(Integer monday) {
      this.monday = monday;
    }
   /**
    * @return 返回 周二
    */
    public Integer getTuesday () {
       return tuesday;
    }
	
   /**
    * @param tuesday 要设置的 周二
    */
    public void setTuesday(Integer tuesday) {
      this.tuesday = tuesday;
    }
   /**
    * @return 返回 周三
    */
    public Integer getWednesday () {
       return wednesday;
    }
	
   /**
    * @param wednesday 要设置的 周三
    */
    public void setWednesday(Integer wednesday) {
      this.wednesday = wednesday;
    }
   /**
    * @return 返回 周四
    */
    public Integer getThursday () {
       return thursday;
    }
	
   /**
    * @param thursday 要设置的 周四
    */
    public void setThursday(Integer thursday) {
      this.thursday = thursday;
    }
   /**
    * @return 返回 周五
    */
    public Integer getFriday () {
       return friday;
    }
	
   /**
    * @param friday 要设置的 周五
    */
    public void setFriday(Integer friday) {
      this.friday = friday;
    }
   /**
    * @return 返回 周六
    */
    public Integer getSaturday () {
       return saturday;
    }
	
   /**
    * @param saturday 要设置的 周六
    */
    public void setSaturday(Integer saturday) {
      this.saturday = saturday;
    }
   /**
    * @return 返回 周日
    */
    public Integer getSunday () {
       return sunday;
    }
	
   /**
    * @param sunday 要设置的 周日
    */
    public void setSunday(Integer sunday) {
      this.sunday = sunday;
    }
   /**
    * @return 返回 每月第X天
    */
    public Integer getDayindex () {
       return dayindex;
    }
	
   /**
    * @param dayindex 要设置的 每月第X天
    */
    public void setDayindex(Integer dayindex) {
      this.dayindex = dayindex;
    }
   /**
    * @return 返回 每月第X周
    */
    public Integer getWeekindex () {
       return weekindex;
    }
	
   /**
    * @param weekindex 要设置的 每月第X周
    */
    public void setWeekindex(Integer weekindex) {
      this.weekindex = weekindex;
    }
   /**
    * @return 返回 每月第X周星期Y
    */
    public Integer getWeekweekindex () {
       return weekweekindex;
    }
	
   /**
    * @param weekweekindex 要设置的 每月第X周星期Y
    */
    public void setWeekweekindex(Integer weekweekindex) {
      this.weekweekindex = weekweekindex;
    }
   /**
    * @return 返回 备注
    */
    public String getMemo () {
       return memo;
    }
	
   /**
    * @param memo 要设置的 备注
    */
    public void setMemo(String memo) {
      this.memo = memo;
    }
   /**
    * @return 返回 创建人
    */
    public String getCreateusername () {
       return createusername;
    }
	
   /**
    * @param createusername 要设置的 创建人
    */
    public void setCreateusername(String createusername) {
      this.createusername = createusername;
    }
   /**
    * @return 返回 创建日期
    */
    public java.util.Date getCreatedate () {
       return createdate;
    }
	
   /**
    * @param createdate 要设置的 创建日期
    */
    public void setCreatedate(java.util.Date createdate) {
      this.createdate = createdate;
    }
   /**
    * @return 返回 创建人所属组织
    */
    public Integer getCreatetbscmpid () {
       return createtbscmpid;
    }
	
   /**
    * @param createtbscmpid 要设置的 创建人所属组织
    */
    public void setCreatetbscmpid(Integer createtbscmpid) {
      this.createtbscmpid = createtbscmpid;
    }
   /**
    * @return 返回 修改人
    */
    public String getEditusername () {
       return editusername;
    }
	
   /**
    * @param editusername 要设置的 修改人
    */
    public void setEditusername(String editusername) {
      this.editusername = editusername;
    }
   /**
    * @return 返回 修改日期
    */
    public java.util.Date getEditdate () {
       return editdate;
    }
	
   /**
    * @param editdate 要设置的 修改日期
    */
    public void setEditdate(java.util.Date editdate) {
      this.editdate = editdate;
    }
   /**
    * @return 返回 调度对象ID
    */
    public Integer getTsysjobid () {
       return tsysjobid;
    }
	
   /**
    * @param tsysjobid 要设置的 调度对象ID
    */
    public void setTsysjobid(Integer tsysjobid) {
      this.tsysjobid = tsysjobid;
    }
   /**
    * @return 返回 优先级
    */
    public Integer getIndexno () {
       return indexno;
    }
	
   /**
    * @param indexno 要设置的 优先级
    */
    public void setIndexno(Integer indexno) {
      this.indexno = indexno;
    }
   /**
    * @return 返回 暂停标志
    */
    public Integer getStopflag () {
       return stopflag;
    }
	
   /**
    * @param stopflag 要设置的 暂停标志
    */
    public void setStopflag(Integer stopflag) {
      this.stopflag = stopflag;
    }
public String getJobname() {
	return jobname;
}
public void setJobname(String jobname) {
	this.jobname = jobname;
}
public Integer getMonthflag() {
	return monthflag;
}
public void setMonthflag(Integer monthflag) {
	this.monthflag = monthflag;
}
}


