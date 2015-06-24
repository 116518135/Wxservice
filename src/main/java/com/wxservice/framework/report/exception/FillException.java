package com.wxservice.framework.report.exception;
public class FillException  extends Exception {
	/** 消息内容 */
    String error;
    public FillException(String error) {
        this.error=error;
    }
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
  
}
