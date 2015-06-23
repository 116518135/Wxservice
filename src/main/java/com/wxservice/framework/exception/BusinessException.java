package com.wxservice.framework.exception;

import org.hibernate.exception.NestableRuntimeException;

public class BusinessException extends NestableRuntimeException {

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}  

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause.getMessage(),cause);
	}
}
