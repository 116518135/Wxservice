package com.wxservice.framework.web.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ClientSession implements Serializable {
	Integer userid;
	String usercode;
	String username;

	Integer cmpid;
	Integer parentid;
	String cmpcode;
	String cmpname;
	Integer cmpkind; // 性质
	String cmpmark = null; // 组织标识,用于单据编号

	String layerno;
	Integer layerindex;

	boolean isAdmin = false;
	int amtRound = 2;
	int priceRound = 2;
	int discountRound = 3;
	int qtyRound = 0;
	Integer storeid;
	String storecode;
	String storename;
	String storewhere;
	String deptmark = null;
	String clientno = "";


	String sessionid = null;
	Map<String, String> authMap = new HashMap<String, String>(); // 权限Map
	Map<String, String> titleMap = new HashMap<String, String>(); // 标题Map
	String dstype;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getCmpid() {
		return cmpid;
	}
	public void setCmpid(Integer cmpid) {
		this.cmpid = cmpid;
	}
	public String getCmpcode() {
		return cmpcode;
	}
	public void setCmpcode(String cmpcode) {
		this.cmpcode = cmpcode;
	}
	public String getCmpname() {
		return cmpname;
	}
	public void setCmpname(String cmpname) {
		this.cmpname = cmpname;
	}
	public Integer getCmpkind() {
		return cmpkind;
	}
	public void setCmpkind(Integer cmpkind) {
		this.cmpkind = cmpkind;
	}
	public String getCmpmark() {
		return cmpmark;
	}
	public void setCmpmark(String cmpmark) {
		this.cmpmark = cmpmark;
	}
	public String getLayerno() {
		return layerno;
	}
	public void setLayerno(String layerno) {
		this.layerno = layerno;
	}
	public Integer getLayerindex() {
		return layerindex;
	}
	public void setLayerindex(Integer layerindex) {
		this.layerindex = layerindex;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public String getStorecode() {
		return storecode;
	}
	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getStorewhere() {
		return storewhere;
	}
	public void setStorewhere(String storewhere) {
		this.storewhere = storewhere;
	}
	public String getDeptmark() {
		return deptmark;
	}
	public void setDeptmark(String deptmark) {
		this.deptmark = deptmark;
	}
	public String getClientno() {
		return clientno;
	}
	public void setClientno(String clientno) {
		this.clientno = clientno;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public Map<String, String> getAuthMap() {
		return authMap;
	}
	public void setAuthMap(Map<String, String> authMap) {
		this.authMap = authMap;
	}
	public Map<String, String> getTitleMap() {
		return titleMap;
	}
	public void setTitleMap(Map<String, String> titleMap) {
		this.titleMap = titleMap;
	}
	public String getDstype() {
		return dstype;
	}
	public void setDstype(String dstype) {
		this.dstype = dstype;
	}
	public int getAmtRound() {
		return amtRound;
	}
	public void setAmtRound(int amtRound) {
		this.amtRound = amtRound;
	}
	public int getPriceRound() {
		return priceRound;
	}
	public void setPriceRound(int priceRound) {
		this.priceRound = priceRound;
	}
	public int getDiscountRound() {
		return discountRound;
	}
	public void setDiscountRound(int discountRound) {
		this.discountRound = discountRound;
	}
	public int getQtyRound() {
		return qtyRound;
	}
	public void setQtyRound(int qtyRound) {
		this.qtyRound = qtyRound;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	
	
}
