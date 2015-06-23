package com.wxservice.web.form.regedit;

import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述： Struts Form 创建人：Gererator
 */

public class TreguserForm extends BaseForm

{
	private static final long serialVersionUID = -1L;

	public TreguserForm() {
		this.setService("treguserManager");
		this.setModuleTitle("注册用户");
		this.setAnonymous("1");
	}

	Integer treguserid;
	String code;
	String othercode;
	String passwd;
	Integer passwdflag = 0;
	String email;
	String mobilephone;
	Integer qqflag = 0;
	Integer sinaflag = 0;
	Integer stopflag = 0;
	String memo;
	Integer emailflag = 0;
	String phonetypename;

	Integer sex;// 性别
	String address;// 地址

	Integer cmpmsflag;// cmpmsflag=１:接收商家消息
	Integer locationflag;// locationflag=1:允许定位

	Integer regplatform;// regplatform=0:悠卡，1：赛琪
	String regplatformname; //平台名称 regplatform=0:悠卡，1：赛琪 

	Integer seetabflag;
	
	String name;
	
	
	String roleids;
	String rolenames;
	
	
	
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

	public String getPhonetypename() {
		return phonetypename;
	}

	public void setPhonetypename(String phonetypename) {
		this.phonetypename = phonetypename;
	}

	public Integer getEmailflag() {
		return emailflag;
	}

	public void setEmailflag(Integer emailflag) {
		this.emailflag = emailflag;
	}

	public Integer getTreguserid() {
		return treguserid;
	}

	public void setTreguserid(Integer treguserid) {
		this.treguserid = treguserid;
	}

	/**
	 * @return 返回 帐号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            要设置的 帐号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 返回 密码
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            要设置的 密码
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return 返回 email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            要设置的 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return 返回 手机
	 */
	public String getMobilephone() {
		return mobilephone;
	}

	/**
	 * @param mobilephone
	 *            要设置的 手机
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	/**
	 * @return 返回 qq登录标志
	 */
	public Integer getQqflag() {
		return qqflag;
	}

	/**
	 * @param qqflag
	 *            要设置的 qq登录标志
	 */
	public void setQqflag(Integer qqflag) {
		this.qqflag = qqflag;
	}

	/**
	 * @return 返回 sina登录标志
	 */
	public Integer getSinaflag() {
		return sinaflag;
	}

	/**
	 * @param sinaflag
	 *            要设置的 sina登录标志
	 */
	public void setSinaflag(Integer sinaflag) {
		this.sinaflag = sinaflag;
	}

	/**
	 * @return 返回 停用标志
	 */
	public Integer getStopflag() {
		return stopflag;
	}

	/**
	 * @param stopflag
	 *            要设置的 停用标志
	 */
	public void setStopflag(Integer stopflag) {
		this.stopflag = stopflag;
	}

	/**
	 * @return 返回 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            要设置的 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getPasswdflag() {
		return passwdflag;
	}

	public void setPasswdflag(Integer passwdflag) {
		this.passwdflag = passwdflag;
	}

	public Integer getRegplatform() {
		return regplatform;
	}

	public void setRegplatform(Integer regplatform) {
		this.regplatform = regplatform;
	}

	
	public String getRegplatformname() {
		return regplatformname;
	}

	public void setRegplatformname(String regplatformname) {
		this.regplatformname = regplatformname;
	}

	public Integer getSeetabflag() {
		return seetabflag;
	}

	public void setSeetabflag(Integer seetabflag) {
		this.seetabflag = seetabflag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
