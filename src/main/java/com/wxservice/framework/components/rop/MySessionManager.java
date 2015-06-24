package com.wxservice.framework.components.rop;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rop.session.Session;
import com.rop.session.SessionManager;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.util.WebUtil;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class MySessionManager implements SessionManager{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public final Map<String, Session> sessionCache = new ConcurrentHashMap<String, Session>(128, 0.75f, 32);

    @Override
    public void addSession(String sessionId, Session session) {
    	IDao dao = WebUtil.getDao();
    	MySession usession = (MySession) session;
		Tsysloginlog po = new Tsysloginlog();
		po.setSessionid(sessionId);
		po.setDevicecode(usession.getDevicecode());
		po.setLogindate(new Date());
		po.setTreguserid(usession.getTreguserid());
		po.setUsercode(usession.getUsercode());
		dao.addSave(po);

    }

    @Override
    public Session getSession(String sessionId) {
    	MySession usession = null;
		IDao dao = WebUtil.getDao();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tsysloginlog where  sessionid='").append(sessionId)
				.append("'");
		Tsysloginlog po = (Tsysloginlog) dao.get(hsql.toString());
		if (po != null) {
			usession = new MySession();
			usession.setDevicecode(po.getDevicecode());
			usession.setTreguserid(po.getTreguserid());
			usession.setUsercode(po.getUsercode());
		}
		return usession;
    }

    @Override
    public void removeSession(String sessionId) {
    	IDao dao = WebUtil.getDao();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tsysloginlog where  sessionid='").append(sessionId)
				.append("'");
		Tsysloginlog po = (Tsysloginlog) dao.get(hsql.toString());
		if (po != null) {
			dao.delete(po);
		}
    }
    
    public String getSessionid(Integer treguserid){
    	String sessionid = "";

		IDao dao = WebUtil.getDao();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tsysloginlog where  treguserid=").append(treguserid);
		List<Tsysloginlog> polist = (List) dao.iterate(hsql.toString());
		if (polist.size() > 0) {
			sessionid = polist.get(0).getSessionid();
		}
		return sessionid;
    }
    public void detetepreSession(MySession usession) {
		String usercode = usession.getUsercode();
		String devicecode = usession.getDevicecode();
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM Tsysloginlog WHERE usercode='")
				.append(usercode).append("' AND devicecode!='")
				.append(devicecode).append("'");
		IDao dao = WebUtil.getDao();
		Operators os = new Operators();
		os.addScriptObject(sb.toString());
		dao.execute(os);
	}
}

