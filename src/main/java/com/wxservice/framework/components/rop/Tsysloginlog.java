package com.wxservice.framework.components.rop;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * 
 * 描述： Hibernate PO 创建人：w
 */

@Entity
@Table(name = "tsysloginlog")
@Proxy(lazy=false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tsysloginlog  {

	private static final long serialVersionUID = 1L;

	protected String usercode;// 用戶名
	protected Integer treguserid;// 用戶註冊id
	protected Integer tsysloginlogid;// 登錄id
	protected String devicecode;// 設備號
	protected Date logindate;// 登錄時間
	protected String sessionid;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Tsysloginlog")
	@TableGenerator(
	   name = "Tsysloginlog",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Tsysloginlog",
	   allocationSize = 1
	)
	public Integer getTsysloginlogid() {
		return tsysloginlogid;
	}

	public void setTsysloginlogid(Integer tsysloginlogid) {
		this.tsysloginlogid = tsysloginlogid;
	}
	
	public Integer getTreguserid() {
		return treguserid;
	}

	public void setTreguserid(Integer treguserid) {
		this.treguserid = treguserid;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getDevicecode() {
		return devicecode;
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}

	

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

}
