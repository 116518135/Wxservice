package com.wxservice.framework.web.action;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.wxservice.framework.util.SystemLogger;

public abstract class AbstractStrutsForward implements IStrutsForward {
	final public static String CONTENTTYPE_TEXT = "text/plain;charset=UTF-8";

	final public static String CONTENTTYPE_XML = "text/xml;charset=UTF-8";

	final public static String CONTENTTYPE_HTML = "text/html;charset=UTF-8";

	final public static String CONTENTTYPE_JSON = "text/x-json;charset=UTF-8";

	final public static String CONTENTTYPE_PDF = "application/pdf";

	final public static String CONTENTTYPE_EXCEL = "application/vnd.ms-excel";
	final public static String CONTENTTYPE_download = "application/x-download";
	protected Object forward = null;
	/**
	 * 构造函数
	 * 
	 * @param forward
	 */
	public AbstractStrutsForward(Object forward) {
		this.forward = forward;
	}

	/**
	 * 以什么方式返回
	 * @param response
	 * @param text
	 * @param contentType
	 */
	protected void render(HttpServletResponse response, String text,
			String contentType) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			SystemLogger.error(e.getMessage(),e);
		}
	}
    /**
     * 以文本返回
     * @param response
     * @param text
     */
	protected void renderText(HttpServletResponse response, String text) {
		render(response, text, AbstractStrutsForward.CONTENTTYPE_TEXT);
	}
    /**
     * 以Html返回
     * @param response
     * @param text
     */
	protected void renderHtml(HttpServletResponse response, String text) {
		render(response, text, AbstractStrutsForward.CONTENTTYPE_HTML);
	}
    /**
     * 以Xml返回
     * @param response
     * @param text
     */
	protected void renderXML(HttpServletResponse response, String text) {
		render(response, text, AbstractStrutsForward.CONTENTTYPE_XML);
	}
    /**
     * 以Pdf返回
     * @param response
     * @param bytes
     */
	protected void readerPdf(HttpServletResponse response, byte[] bytes) {
		try {
			response.setContentType(AbstractStrutsForward.CONTENTTYPE_PDF);
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
    /**
     * 以Excel返回
     * @param response
     * @param bytes
     */
	protected void readerExcel(HttpServletResponse response, byte[] bytes) {
		try {
			response.setContentType(AbstractStrutsForward.CONTENTTYPE_EXCEL);
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	/**
	 * 以下载的方式返回
	 * @param response
	 * @param bytes
	 */
	protected void renderDownload(HttpServletResponse response, byte[] bytes) {
		try {
			response.setContentType(AbstractStrutsForward.CONTENTTYPE_download);
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

    /**
     * 以Json返回
     * @param response
     * @param text
     */
	protected void renderJson(HttpServletResponse response, String text) {
		render(response, text, AbstractStrutsForward.CONTENTTYPE_JSON);
	}
   /**
    * 返回一个forward
    * @return
    */
	public Object getForward() {
		return forward;
	}
    /**
     * 设置一个forward
     * @param forward
     */
	public void setForward(Object forward) {
		this.forward = forward;
	}

}
