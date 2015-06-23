
package com.wxservice.database.po.system;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * 
 * 描述：   Hibernate PO
 * 创建人：Gererator
 */
@Entity
@Table(name = "tsysjobplan")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Tsysjobplan implements  Serializable

{
  private static final long serialVersionUID = -1L;	
  /**
   *主键 系统ID
  */
  protected Integer  tsysjobplanid;// //字段名称： 系统ID

  protected String  code;  //字段名称： 计划编号
  protected String  name;  //字段名称： 计划名称
  protected Integer  fnflag;  //字段名称： 发生频率
  protected Integer  dayfnflag;  //字段名称： 每天发生频率
  protected Integer  onlyonehour;  //字段名称： 小时
  protected Integer  onlyonemin;  //字段名称： 分
  protected Integer  monthflag;  //月标志
  protected Integer  manybeginhour;  //字段名称： 发生多次的开始小时
  protected Integer  manybeginmin;  //字段名称： 发生多次的开始分钟
  protected Integer  manyendhour;  //字段名称： 发生多次的结束小时
  protected Integer  manyendmin;  //字段名称： 发生多次的结束分钟
  protected Integer  hourminindex;  //字段名称： 第X(小时,分钟)执行一次
  protected Integer  hourminflag;  //字段名称： 小时，分钟标志
  protected Integer  monday;  //字段名称： 周一
  protected Integer  tuesday;  //字段名称： 周二
  protected Integer  wednesday;  //字段名称： 周三
  protected Integer  thursday;  //字段名称： 周四
  protected Integer  friday;  //字段名称： 周五
  protected Integer  saturday;  //字段名称： 周六
  protected Integer  sunday;  //字段名称： 周日
  protected Integer  dayindex;  //字段名称： 每月第X天
  protected Integer  weekindex;  //字段名称： 每月第X周
  protected Integer  weekweekindex;  //字段名称： 每月第X周星期Y
  protected String  memo;  //字段名称： 备注
  protected String  createusername;  //字段名称： 创建人
  protected java.util.Date  createdate;  //字段名称： 创建日期
  protected Integer  createtbscmpid;  //字段名称： 创建人所属组织
  protected String  editusername;  //字段名称： 修改人
  protected java.util.Date  editdate;  //字段名称： 修改日期
  protected Integer  tsysjobid;  //字段名称： 调度对象ID
  protected Integer  indexno;  //字段名称： 优先级
  protected Integer  stopflag;  //字段名称： 暂停标志
  String add1;
  String add2;
  String add3;
  String add4;
  String add5;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysjobplan")
	@TableGenerator(
	   name = "Tsysjobplan",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysjobplan",
	   allocationSize = 1
	)
  public Integer getTsysjobplanid () {
     return tsysjobplanid;
  }
  public void setTsysjobplanid(Integer tsysjobplanid) {
     this.tsysjobplanid = tsysjobplanid;
  }
 /**
  * @return 返回 code,计划编号
  */
  @Column(length=20)
  public String getCode () {
     return code;
  }
	
 /**
  * @param code 
  */
  public void setCode(String code) {
     this.code = code;
  }
 /**
  * @return 返回 name,计划名称
  */
  @Column(length=50)
  public String getName () {
     return name;
  }
	
 /**
  * @param name 
  */
  public void setName(String name) {
     this.name = name;
  }
 /**
  * @return 返回 fnflag,发生频率
  */
  
  public Integer getFnflag () {
     return fnflag;
  }
	
 /**
  * @param fnflag 
  */
  public void setFnflag(Integer fnflag) {
     this.fnflag = fnflag;
  }
 /**
  * @return 返回 dayfnflag,每天发生频率
  */
  
  public Integer getDayfnflag () {
     return dayfnflag;
  }
	
 /**
  * @param dayfnflag 
  */
  public void setDayfnflag(Integer dayfnflag) {
     this.dayfnflag = dayfnflag;
  }
 /**
  * @return 返回 onlyonehour,小时
  */
  
  public Integer getOnlyonehour () {
     return onlyonehour;
  }
	
 /**
  * @param onlyonehour 
  */
  public void setOnlyonehour(Integer onlyonehour) {
     this.onlyonehour = onlyonehour;
  }
 /**
  * @return 返回 onlyonemin,分
  */
  
  public Integer getOnlyonemin () {
     return onlyonemin;
  }
	
 /**
  * @param onlyonemin 
  */
  public void setOnlyonemin(Integer onlyonemin) {
     this.onlyonemin = onlyonemin;
  }
 
 public Integer getMonthflag() {
	return monthflag;
}
public void setMonthflag(Integer monthflag) {
	this.monthflag = monthflag;
}
/**
  * @return 返回 manybeginhour,发生多次的开始小时
  */
  
  public Integer getManybeginhour () {
     return manybeginhour;
  }
	
 /**
  * @param manybeginhour 
  */
  public void setManybeginhour(Integer manybeginhour) {
     this.manybeginhour = manybeginhour;
  }
 /**
  * @return 返回 manybeginmin,发生多次的开始分钟
  */
  
  public Integer getManybeginmin () {
     return manybeginmin;
  }
	
 /**
  * @param manybeginmin 
  */
  public void setManybeginmin(Integer manybeginmin) {
     this.manybeginmin = manybeginmin;
  }
 /**
  * @return 返回 manyendhour,发生多次的结束小时
  */
  
  public Integer getManyendhour () {
     return manyendhour;
  }
	
 /**
  * @param manyendhour 
  */
  public void setManyendhour(Integer manyendhour) {
     this.manyendhour = manyendhour;
  }
 /**
  * @return 返回 manyendmin,发生多次的结束分钟
  */
  
  public Integer getManyendmin () {
     return manyendmin;
  }
	
 /**
  * @param manyendmin 
  */
  public void setManyendmin(Integer manyendmin) {
     this.manyendmin = manyendmin;
  }
 /**
  * @return 返回 hourminindex,第X(小时,分钟)执行一次
  */
  
  public Integer getHourminindex () {
     return hourminindex;
  }
	
 /**
  * @param hourminindex 
  */
  public void setHourminindex(Integer hourminindex) {
     this.hourminindex = hourminindex;
  }
 /**
  * @return 返回 hourminflag,小时，分钟标志
  */
  
  public Integer getHourminflag () {
     return hourminflag;
  }
	
 /**
  * @param hourminflag 
  */
  public void setHourminflag(Integer hourminflag) {
     this.hourminflag = hourminflag;
  }
 /**
  * @return 返回 monday,周一
  */
  
  public Integer getMonday () {
     return monday;
  }
	
 /**
  * @param monday 
  */
  public void setMonday(Integer monday) {
     this.monday = monday;
  }
 /**
  * @return 返回 tuesday,周二
  */
  
  public Integer getTuesday () {
     return tuesday;
  }
	
 /**
  * @param tuesday 
  */
  public void setTuesday(Integer tuesday) {
     this.tuesday = tuesday;
  }
 /**
  * @return 返回 wednesday,周三
  */
  
  public Integer getWednesday () {
     return wednesday;
  }
	
 /**
  * @param wednesday 
  */
  public void setWednesday(Integer wednesday) {
     this.wednesday = wednesday;
  }
 /**
  * @return 返回 thursday,周四
  */
  
  public Integer getThursday () {
     return thursday;
  }
	
 /**
  * @param thursday 
  */
  public void setThursday(Integer thursday) {
     this.thursday = thursday;
  }
 /**
  * @return 返回 friday,周五
  */
  
  public Integer getFriday () {
     return friday;
  }
	
 /**
  * @param friday 
  */
  public void setFriday(Integer friday) {
     this.friday = friday;
  }
 /**
  * @return 返回 saturday,周六
  */
  
  public Integer getSaturday () {
     return saturday;
  }
	
 /**
  * @param saturday 
  */
  public void setSaturday(Integer saturday) {
     this.saturday = saturday;
  }
 /**
  * @return 返回 sunday,周日
  */
  
  public Integer getSunday () {
     return sunday;
  }
	
 /**
  * @param sunday 
  */
  public void setSunday(Integer sunday) {
     this.sunday = sunday;
  }
 /**
  * @return 返回 dayindex,每月第X天
  */
  
  public Integer getDayindex () {
     return dayindex;
  }
	
 /**
  * @param dayindex 
  */
  public void setDayindex(Integer dayindex) {
     this.dayindex = dayindex;
  }
 /**
  * @return 返回 weekindex,每月第X周
  */
  
  public Integer getWeekindex () {
     return weekindex;
  }
	
 /**
  * @param weekindex 
  */
  public void setWeekindex(Integer weekindex) {
     this.weekindex = weekindex;
  }
 /**
  * @return 返回 weekweekindex,每月第X周星期Y
  */
  
  public Integer getWeekweekindex () {
     return weekweekindex;
  }
	
 /**
  * @param weekweekindex 
  */
  public void setWeekweekindex(Integer weekweekindex) {
     this.weekweekindex = weekweekindex;
  }
 /**
  * @return 返回 memo,备注
  */
  @Column(length=50)
  public String getMemo () {
     return memo;
  }
	
 /**
  * @param memo 
  */
  public void setMemo(String memo) {
     this.memo = memo;
  }
 /**
  * @return 返回 createusername,创建人
  */
  @Column(length=20)
  public String getCreateusername () {
     return createusername;
  }
	
 /**
  * @param createusername 
  */
  public void setCreateusername(String createusername) {
     this.createusername = createusername;
  }
 /**
  * @return 返回 createdate,创建日期
  */
  
  public java.util.Date getCreatedate () {
     return createdate;
  }
	
 /**
  * @param createdate 
  */
  public void setCreatedate(java.util.Date createdate) {
     this.createdate = createdate;
  }
 /**
  * @return 返回 createtbscmpid,创建人所属组织
  */
  
  public Integer getCreatetbscmpid () {
     return createtbscmpid;
  }
	
 /**
  * @param createtbscmpid 
  */
  public void setCreatetbscmpid(Integer createtbscmpid) {
     this.createtbscmpid = createtbscmpid;
  }
 /**
  * @return 返回 editusername,修改人
  */
  @Column(length=20)
  public String getEditusername () {
     return editusername;
  }
	
 /**
  * @param editusername 
  */
  public void setEditusername(String editusername) {
     this.editusername = editusername;
  }
 /**
  * @return 返回 editdate,修改日期
  */
  
  public java.util.Date getEditdate () {
     return editdate;
  }
	
 /**
  * @param editdate 
  */
  public void setEditdate(java.util.Date editdate) {
     this.editdate = editdate;
  }
 /**
  * @return 返回 tsysjobid,调度对象ID
  */
  
  public Integer getTsysjobid () {
     return tsysjobid;
  }
	
 /**
  * @param tsysjobid 
  */
  public void setTsysjobid(Integer tsysjobid) {
     this.tsysjobid = tsysjobid;
  }
 /**
  * @return 返回 indexno,优先级
  */
  
  public Integer getIndexno () {
     return indexno;
  }
	
 /**
  * @param indexno 
  */
  public void setIndexno(Integer indexno) {
     this.indexno = indexno;
  }
 /**
  * @return 返回 stopflag,暂停标志
  */
  
  public Integer getStopflag () {
     return stopflag;
  }
	
 /**
  * @param stopflag 
  */
  public void setStopflag(Integer stopflag) {
     this.stopflag = stopflag;
  }

  public String toString() {
     return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
 			   .append("tsysjobplanid", this.tsysjobplanid)
		       .toString();
  }

  public boolean equals(Object other) {
     if ( !(other instanceof Tsysjobplan) ) return false;
     Tsysjobplan castOther = (Tsysjobplan) other;
     return new EqualsBuilder()
       .append(this.getTsysjobplanid(), castOther.getTsysjobplanid())
       .isEquals();
  }


  public int hashCode() {
        return new HashCodeBuilder()
            .append(this.getTsysjobplanid())
            .toHashCode();
  }
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
 

}


