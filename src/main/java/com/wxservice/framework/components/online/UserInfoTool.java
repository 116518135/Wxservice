package com.wxservice.framework.components.online;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.session.ClientSession;

public class UserInfoTool {
	public static final String KEY_ONLINEUSER = "KEY_ONLINEUSER";
	public static final Map context = new HashMap();

	public static Map getContext() {
		return context;
	}

	/**
	 * 得到在线用户列表
	 * 
	 * @return
	 */
	public static List getOnlineList() {
		List list = (List) context.get(KEY_ONLINEUSER);
		if (list == null) {
			list = new ArrayList();
			context.put(KEY_ONLINEUSER, list);
		}
		return list;
	}

	/**
	 * 得到在线用户的个数
	 * 
	 * @return
	 */
	public static Integer getOnlineCount() {
		List list = (List) context.get(KEY_ONLINEUSER);
		if (list == null) {
			list = new ArrayList();
			context.put(KEY_ONLINEUSER, list);
		}
		return list.size();
	}

	/**
	 * 增加一个在线用户
	 * 
	 * @param state
	 */
	public static void addOnlineUser(UserInfo state) {
		List list = getOnlineList();
		list.add(state);
		context.put(KEY_ONLINEUSER, list);
		print();
	}

	public static void addOnlineUser(ClientSession client) {
		if (client != null) {
			removeOnlineUser(client.getSessionid());
			UserInfo state = new UserInfo();
			state.setUserid(client.getUserid());
			state.setUsername(client.getUsername());
			state.setCmpname(client.getCmpname());
			state.setSessionid(client.getSessionid());
			state.setClientno(client.getClientno());
			addOnlineUser(state);
		}

	}

	/**
	 * 删除一个在线用户
	 * 
	 * @param state
	 */
	public static void removeOnlineUser(UserInfo state) {
		List list = getOnlineList();
		for (int i = 0; i < list.size(); i++) {
			UserInfo state1 = (UserInfo) list.get(i);
			if (state.getSessionid().equals(state1.getSessionid())) {
				list.remove(i);
				break;
			}
		}
		context.put(KEY_ONLINEUSER, list);
		print();
	}

	/**
	 * 删除一个在线用户
	 * 
	 * @param sessionid
	 */
	public static void removeOnlineUser(String sessionid) {
		List list = getOnlineList();
		for (int i = 0; i < list.size(); i++) {
			UserInfo state = (UserInfo) list.get(i);
			if (sessionid.equals(state.getSessionid())) {
				list.remove(i);
				break;
			}
		}
		context.put(KEY_ONLINEUSER, list);
		print();
	}

	// 删除一个用户
	public static void removeOnlineUser(Integer tbsuserid) {
		List list = getOnlineList();
		for (int i = 0; i < list.size(); i++) {
			UserInfo state = (UserInfo) list.get(i);
			if (tbsuserid.equals(state.getUserid())) {
				list.remove(i);
				break;
			}
		}
		context.put(KEY_ONLINEUSER, list);
		print();
	}

	/**
	 * 删除一个指定机器的用户
	 * 
	 * @param clientno
	 */
	public static void removeOnlineComputer(String clientno) {
		List list2 = new ArrayList();
		List list = getOnlineList();
		for (int i = 0; i < list.size(); i++) {
			UserInfo state = (UserInfo) list.get(i);
			if (clientno.equals(state.getClientno())) {
				list2.add(state);
			}
		}
		for (Object o : list2) {
			list.remove(o);
		}
		context.put(KEY_ONLINEUSER, list);
		print();
	}

	/**
	 * 打印当前的在线用户名
	 */
	public static void print() {
		Integer count = getOnlineCount();
		StringBuffer msg = new StringBuffer();
		msg.append("在线用户个数为：");
		msg.append(count);
		SystemLogger.debug(msg.toString());
	}

	/**
	 * 判断一个在线用户是否在线
	 * 
	 * @param client
	 * @return
	 */
	public static boolean IsUserOnlineByUserid(ClientSession client) {
		if (client == null)
			return false;
		
		return IsUserOnlineByUserid(client.getUserid());
	}
	
	public static boolean IsUserOnlineByUserid(Integer tbsuserid) {
		
		List list = getOnlineList();
		for (int i = 0; i < list.size(); i++) {
			UserInfo state = (UserInfo) list.get(i);
			if (state.getUserid().equals(tbsuserid)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 通过sessionid判断一个在线用户
	 * 
	 * @param client
	 * @return
	 */
	public static boolean IsUserOnlineBySessionid(ClientSession client) {
		if (client == null)
			return false;
		List list = getOnlineList();
		for (int i = 0; i < list.size(); i++) {
			UserInfo state = (UserInfo) list.get(i);
			if (state.getSessionid().equals(client.getSessionid())) {
				return true;
			}
		}
		return false;
	}

	

}
