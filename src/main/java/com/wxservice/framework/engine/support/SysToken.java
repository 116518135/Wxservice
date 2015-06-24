package com.wxservice.framework.engine.support;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.upload.MultipartRequestWrapper;

import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.form.BaseForm;


public class SysToken {
	public static String METHOD = "method";
	public static final String TOKEN_LINE = "TOKEN_LINE";
	public boolean isTokenCheck = true;

	private long previous;

	private static SysToken instance = new SysToken();

	public static SysToken getInstance() {
		return instance;
	}

	protected SysToken() {
		super();
	}

	public synchronized boolean isTokenValid(HttpServletRequest request) {
		return this.isTokenValid(request, true);
	}

	public synchronized boolean isTokenValidLine(HttpServletRequest request) {
		return this.isTokenValidLine(request, true);
	}

	public HttpServletRequest getRequest(HttpServletRequest request) {
		HttpServletRequest req = null;
		assert (request == null);
		if (request instanceof MultipartRequestWrapper) {
			MultipartRequestWrapper obj = (MultipartRequestWrapper) request;
			req = obj.getRequest();
		} else {
			req = request;
		}
		return req;
	}

	public synchronized boolean isTokenValidLine(HttpServletRequest req,
			boolean reset) {
		if (!isTokenCheck) {// 如果设置了不需要检查token,这一步主要用于性能测试
			return true;
		}
		HttpServletRequest request = this.getRequest(req);
		// Retrieve the current session for this request
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}

		// Retrieve the transaction token from this session, and
		// reset it if requested
		String saved = (String) session.getAttribute(getTokenLineKey(request));
		if (saved == null) {
			log();
			return false;
		}

		if (reset) {
			this.resetTokenLine(request);
		}
		// Retrieve the transaction token included in this request
		String token = request.getParameter(Constants.TOKEN_KEY);

		if (token == null) {
			log();
			return false;
		}
		boolean result = saved.equals(token);
		if (!result) {
			log();
		}
		return result;
	}

	public synchronized boolean isTokenValid(HttpServletRequest req,
			boolean reset) {
		if (!isTokenCheck) {// 如果设置了不需要检查token,这一步主要用于性能测试
			return true;
		}
		HttpServletRequest request = this.getRequest(req);
		// Retrieve the current session for this request
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}

		// Retrieve the transaction token from this session, and
		// reset it if requested
		String saved = (String) session
				.getAttribute(getTokenKey(request));
		if (saved == null) {
			log();
			return false;
		}

		if (reset) {
			this.resetToken(request);
		}

		// Retrieve the transaction token included in this request
		String token = request.getParameter(Constants.TOKEN_KEY);

		if (token == null) {
			log();
			return false;
		}
		boolean result = saved.equals(token);
		if (!result) {
			log();
		}
		return result;
	}

	public void log() {
		SystemLogger.info("用户重复提交!");

	}

	/**
	 * Reset the saved transaction token in the user's session. This indicates
	 * that transactional token checking will not be needed on the next request
	 * that is submitted.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 */
	public synchronized void resetToken(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(getTokenKey(request));
	}

	public synchronized void resetTokenLine(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(getTokenLineKey(request));
	}

	/**
	 * Save a new transaction token in the user's current session, creating a
	 * new session if necessary.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 */
	public synchronized void saveToken(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String token = generateToken(request);
		if (token != null) {
			session.setAttribute(getTokenKey(request), token);
		}

	}
	public static String getTokenKey(String prefx,String formname){
		StringBuffer key=new StringBuffer();
	    key.append(prefx);
	    key.append(formname);
	    return key.toString();
	}
	public String getTokenKey(HttpServletRequest request){
		BaseForm form=WebUtil.getForm(request);
		return getTokenKey(Globals.TRANSACTION_TOKEN_KEY,form.getFormname());
	}
	public String getTokenLineKey(HttpServletRequest request){
		BaseForm form=WebUtil.getForm(request);
		return getTokenKey(TOKEN_LINE,form.getFormname());
	}

	public synchronized void saveTokenLine(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String token = generateToken(request);
		if (token != null) {
			session.setAttribute(getTokenLineKey(request), token);
		}

	}

	/**
	 * Generate a new transaction token, to be used for enforcing a single
	 * request for a particular transaction.
	 * 
	 * @param request
	 *            The request we are processing
	 */
	public synchronized String generateToken(HttpServletRequest request) {

		HttpSession session = request.getSession();
		try {
			byte id[] = session.getId().getBytes();
			long current = System.currentTimeMillis();
			if (current == previous) {
				current++;
			}
			previous = current;
			byte now[] = new Long(current).toString().getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			return toHex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

	}

	/**
	 * Convert a byte array to a String of hexadecimal digits and return it.
	 * 
	 * @param buffer
	 *            The byte array to be converted
	 */
	private String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}

	public static String getMETHOD() {
		return METHOD;
	}

	public static void setMETHOD(String method) {
		METHOD = method;
	}

	public void setIsTokenCheck(boolean isTokenCheck) {
		this.isTokenCheck = isTokenCheck;
	}

}