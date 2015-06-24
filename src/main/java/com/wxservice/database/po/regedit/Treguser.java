package com.wxservice.database.po.regedit;

import java.io.Serializable;
import java.util.Date;

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
 * 描述： Hibernate PO 创建人：Gererator
 */
@Entity
@Table(name = "treguser")
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Treguser implements Serializable

{
	private static final long serialVersionUID = -1L;
	/**
	 * 主键 系统ID
	 */
	protected Integer treguserid;// //字段名称： 系统ID
    protected String othercode;//字段名称：openid或者sina code
	protected String code; // 字段名称： 帐号
	protected String passwd; // 字段名称： 密码
	protected String email; // 字段名称： email
	protected String mobilephone; // 字段名称： 手机
	protected Integer qqflag; // 字段名称： qq登录标志
	protected Integer sinaflag; // 字段名称： sina登录标志
	protected Integer stopflag; // 字段名称： 停用标志
	protected String memo; // 字段名称： 备注
	Date createtime;
	protected Integer emailflag; // 字段名称： email绑定标志
	protected Integer phonetype; // 字段名称：终端类型
	
	protected Integer sex;//性别
	protected String address;//地址
	protected Integer cmpmsflag;//cmpmsflag=１:接收商家消息
	protected Integer locationflag;//locationflag=1:允许定位
	protected Integer regplatform;//regplatform=0:悠卡，1：赛琪
	protected Integer seetabflag;//see table flag
	protected String name;
	protected String roleids;//角色
	protected String rolenames;//角色name
	
	
	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	public String getRolenames() {
		return rolenames;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

	public String getOthercode() {
		return othercode;
	}

	public void setOthercode(String othercode) {
		this.othercode = othercode;
	}

	public Integer getCmpmsflag() {
		return cmpmsflag;
	}

	public void setCmpmsflag(Integer cmpmsflag) {
		this.cmpmsflag = cmpmsflag;
	}

	public Integer getLocationflag() {
		return locationflag;
	}

	public void setLocationflag(Integer locationflag) {
		this.locationflag = locationflag;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhonetype() {
		return phonetype;
	}

	public void setPhonetype(Integer phonetype) {
		this.phonetype = phonetype;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Treguser")
	@TableGenerator(name = "Treguser", table = "tsyscounter", pkColumnName = "tablekey", valueColumnName = "countvalue", pkColumnValue = "Treguser", allocationSize = 1)
	public Integer getTreguserid() {
		return treguserid;
	}

	public void setTreguserid(Integer treguserid) {
		this.treguserid = treguserid;
	}

	public Integer getEmailflag() {
		return emailflag;
	}

	public void setEmailflag(Integer emailflag) {
		this.emailflag = emailflag;
	}

	/**
	 * @return 返回 code,帐号
	 */
	@Column(length = 50)
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 返回 passwd,密码
	 */
	@Column(length = 50)
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return 返回 email,email
	 */
	@Column(length = 50)
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return 返回 mobilephone,手机
	 */
	@Column(length = 20)
	public String getMobilephone() {
		return mobilephone;
	}

	/**
	 * @param mobilephone
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	/**
	 * @return 返回 qqflag,qq登录标志
	 */

	public Integer getQqflag() {
		return qqflag;
	}

	/**
	 * @param qqflag
	 */
	public void setQqflag(Integer qqflag) {
		this.qqflag = qqflag;
	}

	/**
	 * @return 返回 sinaflag,sina登录标志
	 */

	public Integer getSinaflag() {
		return sinaflag;
	}

	/**
	 * @param sinaflag
	 */
	public void setSinaflag(Integer sinaflag) {
		this.sinaflag = sinaflag;
	}

	/**
	 * @return 返回 stopflag,停用标志
	 */

	public Integer getStopflag() {
		return stopflag;
	}

	/**
	 * @param stopflag
	 */
	public void setStopflag(Integer stopflag) {
		this.stopflag = stopflag;
	}

	/**
	 * @return 返回 memo,备注
	 */
	@Column(length = 50)
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("treguserid", this.treguserid).toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Treguser))
			return false;
		Treguser castOther = (Treguser) other;
		return new EqualsBuilder().append(this.getTreguserid(),
				castOther.getTreguserid()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.getTreguserid()).toHashCode();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * @param regplatform   注册平台
	 */
	public Integer getRegplatform() {
		return regplatform;
	}

	public void setRegplatform(Integer regplatform) {
		this.regplatform = regplatform;
	}

	public Integer getSeetabflag() {
		return seetabflag;
	}

	public void setSeetabflag(Integer seetabflag) {
		this.seetabflag = seetabflag;
	}
	
	

}
