package com.wxservice.web.form.report;

import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.framework.web.session.ClientSession;
public class ReportForm extends BaseForm  {
	public ReportForm() {
		this.setService("reportManager");
		this.setModuleTitle("");
		this.setAnonymous("0");
	}
    ClientSession client=new ClientSession();
	String  server_url;
	String condition;
	String reportid;
	String htmlContent;
	String helpContent;
	String name;
	Integer trpdcubetplid = 0;
	String showsubflag;
	String reportidtemp;
	
	public String getReportidtemp() {
		return reportidtemp;
	}
	public void setReportidtemp(String reportidtemp) {
		this.reportidtemp = reportidtemp;
	}
	public Integer getTrpdcubetplid() {
		return trpdcubetplid;
	}
	public void setTrpdcubetplid(Integer trpdcubetplid) {
		this.trpdcubetplid = trpdcubetplid;
	}
	public String getShowsubflag() {
		return showsubflag;
	}
	public void setShowsubflag(String showsubflag) {
		this.showsubflag = showsubflag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getServer_url() {
		return server_url;
	}
	public void setServer_url(String server_url) {
		this.server_url = server_url;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getHtmlContent() {
		return htmlContent;
	}
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	public String getHelpContent() {
		return helpContent;
	}
	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}
	public int getAmtRound() {
		return client.getAmtRound();
	}
	public String getClientno() {
		return client.getClientno();
	}
	public String getCmpcode() {
		return client.getCmpcode();
	}
	public Integer getCmpid() {
		return client.getCmpid();
	}
	public Integer getCmpkind() {
		return client.getCmpkind();
	}
	public String getCmpmark() {
		return client.getCmpmark();
	}
	public String getCmpname() {
		return client.getCmpname();
	}
	
	public String getDeptmark() {
		return client.getDeptmark();
	}
	
	public int getDiscountRound() {
		return client.getDiscountRound();
	}
	public Integer getLayerindex() {
		return client.getLayerindex();
	}
	public String getLayerno() {
		return client.getLayerno();
	}
	public int getPriceRound() {
		return client.getPriceRound();
	}
	public int getQtyRound() {
		return client.getQtyRound();
	}
	public String getSessionid() {
		return client.getSessionid();
	}
	public String getStorecode() {
		return client.getStorecode();
	}
	public Integer getStoreid() {
		return client.getStoreid();
	}
	public String getStorename() {
		return client.getStorename();
	}
	public String getUsercode() {
		return client.getUsercode();
	}
	public Integer getUserid() {
		return client.getUserid();
	}
	public String getUsername() {
		return client.getUsername();
	}
	public int hashCode() {
		return client.hashCode();
	}
	public boolean isAdmin() {
		return client.isAdmin();
	}
	public void setAmtRound(int amtRound) {
		client.setAmtRound(amtRound);
	}
	
	public void setClientno(String clientno) {
		client.setClientno(clientno);
	}
	public void setCmpcode(String cmpcode) {
		client.setCmpcode(cmpcode);
	}
	public void setCmpid(Integer cmpid) {
		client.setCmpid(cmpid);
	}
	public void setCmpkind(Integer cmpkind) {
		client.setCmpkind(cmpkind);
	}
	public void setCmpmark(String cmpmark) {
		client.setCmpmark(cmpmark);
	}
	public void setCmpname(String cmpname) {
		client.setCmpname(cmpname);
	}
	
	public void setDeptmark(String deptmark) {
		client.setDeptmark(deptmark);
	}
	
	public void setDiscountRound(int discountRound) {
		client.setDiscountRound(discountRound);
	}
	public void setIsAdmin(boolean isAdmin) {
		client.setIsAdmin(isAdmin);
	}
	public void setLayerindex(Integer layerindex) {
		client.setLayerindex(layerindex);
	}
	public void setLayerno(String layerno) {
		client.setLayerno(layerno);
	}
	public void setPriceRound(int priceRound) {
		client.setPriceRound(priceRound);
	}
	public void setQtyRound(int qtyRound) {
		client.setQtyRound(qtyRound);
	}
	public void setSessionid(String sessionid) {
		client.setSessionid(sessionid);
	}
	public void setStorecode(String storecode) {
		client.setStorecode(storecode);
	}
	public void setStoreid(Integer storeid) {
		client.setStoreid(storeid);
	}
	public void setStorename(String storename) {
		client.setStorename(storename);
	}
	public void setUsercode(String usercode) {
		client.setUsercode(usercode);
	}
	public void setUserid(Integer userid) {
		client.setUserid(userid);
	}
	public void setUsername(String username) {
		client.setUsername(username);
	}
	public ClientSession getClient() {
		return client;
	}
	public void setClient(ClientSession client) {
		this.client = client;
	}


}
