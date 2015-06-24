package com.wxservice.framework.components.online;

public class UserInfo {
	Integer userid = null;
	String username=null;
	String cmpname=null;;
	String sessionid = null;
	String ip = null;
	String clientno=null;

	/**  
	 * 返回 userid 的值   
	 * @return userid  
	 */
	
	public Integer getUserid() {
		return userid;
	}
	/**  
	 * 设置 userid 的值  
	 * @param userid
	 */
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	/**  
	 * 返回 sessionid 的值   
	 * @return sessionid  
	 */
	
	public String getSessionid() {
		return sessionid;
	}
	/**  
	 * 设置 sessionid 的值  
	 * @param sessionid
	 */
	
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	/**  
	 * 返回 ip 的值   
	 * @return ip  
	 */
	
	public String getIp() {
		return ip;
	}
	/**  
	 * 设置 ip 的值  
	 * @param ip
	 */
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**  
	 * 返回 username 的值   
	 * @return username  
	 */
	
	public String getUsername() {
		return username;
	}
	/**  
	 * 设置 username 的值  
	 * @param username
	 */
	
	public void setUsername(String username) {
		this.username = username;
	}
	/**  
	 * 返回 cmpname 的值   
	 * @return cmpname  
	 */
	
	public String getCmpname() {
		return cmpname;
	}
	/**  
	 * 设置 cmpname 的值  
	 * @param cmpname
	 */
	
	public void setCmpname(String cmpname) {
		this.cmpname = cmpname;
	}
	/**  
	 * 返回 clientno 的值   
	 * @return clientno  
	 */
	
	public String getClientno() {
		return clientno;
	}
	/**  
	 * 设置 clientno 的值  
	 * @param clientno
	 */
	
	public void setClientno(String clientno) {
		this.clientno = clientno;
	}
	
}
