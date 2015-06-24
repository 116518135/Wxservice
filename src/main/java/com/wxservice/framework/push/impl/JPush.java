package com.wxservice.framework.push.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

import com.wxservice.database.po.regedit.Treguser;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.push.SimplePush;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;

public class JPush extends SimplePush {
//	JPushClient jpush = null;
	Map params = new HashMap();
	private static final Integer TAG_NUMBER = 10;// tag：10 alias：1000

	public JPush() {
		params.put("@", "");
		params.put(".", "");
		params.put("-", "");
	}

	public JPushClient getClient(Map<String, String> params) {
//		if (jpush == null) {
//			String appKey = params.get("aKey");
//			String masterSecret = params.get("secret");
//			String timeto =  params.get("ttl");
//			long timetolive = new Long(timeto);
//			jpush = new JPushClient(masterSecret, appKey, timetolive);
//
//		}
//		return jpush;
		JPushClient jpush = null;
		String appKey = params.get("aKey");
		String masterSecret = params.get("secret");
		String timeto =  params.get("ttl");
		long timetolive = new Long(timeto);
		jpush = new JPushClient(masterSecret, appKey, timetolive);
		return jpush;
	}

	public String filter(String tag) {
		String newtag = SysUtil.doVarible(tag, params);
		return newtag;

	}

	@Override
	public String push(Map<String, String> params) {
		JPushClient client = getClient(params);
		StringBuffer msg = new StringBuffer();
		String title = params.get("title");
		String imagename = params.get("imagename");
		String content = params.get("content");
		String tag = params.get("tag");
		String result = "";

		Integer sendno = MathUtil.parsetInt(params.get("sendno"), 0);
		Integer type = MathUtil.parsetInt(params.get("type"), 0);
		Map<String, Object> extra = new HashMap<String, Object>();
		extra.put("imagename", imagename);
		// extra.put("content", content);
		extra.put("type", type);

		setGroupForSendInfo(tag, client, sendno, title, content, extra, msg, 2);
		if (StringUtil.isNotBlank(msg.toString())
				|| StringUtil.isNotEmpty(msg.toString())) {
			result = "小部分消息未推送成功。"+msg.toString();
		}

		return result;
	}

	// get usercode group, per number
	public void setGroupForSendInfo(String user, JPushClient client,
			Integer sendno, String title, String content,
			Map<String, Object> extra, StringBuffer msg, Integer flag) {

		StringBuffer sb = new StringBuffer();

		List list = StringUtil.string2List(user);

		// system info
		if (MathUtil.equal(flag, 1)) {
			list = getUsercodeByFlag(list);
		}

		int usercount = list.size();
		if (list != null && usercount != 0) {

			for (int i = 1; i <= usercount; i++) {
				if (list.get(i-1) == null || list.get(i-1) == "") {
					continue;
				}
				sb.append(list.get(i - 1)).append(",");
				
				if (i % TAG_NUMBER == 0 || MathUtil.equal(i, usercount)) {
					sb = sb.delete(sb.length() - 1, sb.length());
					String usercode = filter(sb.toString());
					sendMethod(client, sendno, usercode, title, content, extra,
							msg);
					sb.setLength(0);
				} 
			}
		}
	}

	public static List<String> subList(List<String> l, Integer num) {
		return l.subList(0, num);
	}

	// send info
	public void sendMethod(JPushClient client, Integer sendno,
			String usercodelist, String title, String content,
			Map<String, Object> extra, StringBuffer msg) {

		MessageResult msgResult = null;

		try {
			msgResult = client.sendNotificationWithTag(sendno, usercodelist,
					title, content, 0, extra);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null != msgResult) {
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
			} else {
				msg.append(" 错误代码=");
				msg.append(msgResult.getErrcode());
				msg.append(", 错误消息=");
				msg.append(msgResult.getErrmsg());
				SystemLogger.debug(msg.toString());
			}
		}
	}

	// system push info
	@Override
	public String systempush(Map<String, String> params) {
		JPushClient client = getClient(params);
		StringBuffer msg = new StringBuffer();
		String title = params.get("title");
		String imagename = params.get("imagename");
		String content = params.get("content");
		String tag = params.get("tag");
		String numlen = params.get("num");
		String result = "";
		byte[] alenth = content.getBytes();
		int len = alenth.length;
		Integer sendno = MathUtil.parsetInt(params.get("sendno"), 0);
		Integer type = MathUtil.parsetInt(params.get("type"), 0);
		Map<String, Object> extra = new HashMap<String, Object>();
		extra.put("imagename", imagename);
		// extra.put("content", content);
		extra.put("type", type);

		setGroupForSendInfo(tag, client, sendno, title, content, extra, msg, 1);
		if (StringUtil.isNotBlank(msg.toString())
				|| StringUtil.isNotEmpty(msg.toString())) {
			result = "小部分消息未推送成功。"+msg.toString();
		}

		return result;
	}

	// select flag info
	public List<String> getUsercodeByFlag(List<String> userlist) {
		StringBuffer sb = new StringBuffer();
		IDao dao = WebUtil.getDao();

		for (String stemp : userlist) {
			sb.append("'").append(stemp).append("'").append(",");
		}
		sb = sb.delete(sb.length() - 1, sb.length());

		StringBuffer hql = new StringBuffer();
		hql.append(" from Treguser where code in( ").append(sb).append(" )");
		List<Treguser> list = dao.iterate(hql.toString());

		List<String> usercodelist = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Treguser user = list.get(i);
			if (MathUtil.equal(user.getCmpmsflag(), 1)) {
				usercodelist.add(user.getCode());
			}
		}
		return usercodelist;
	}
}
