package com.api.consumer.exception;

import org.springframework.http.HttpStatus;

public class CampanhaSocioTorcedorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	private Object obj;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Object getObj() {
		return obj;
	}

	public CampanhaSocioTorcedorException(String msg) {
		super(msg);
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public CampanhaSocioTorcedorException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}
	
	public CampanhaSocioTorcedorException(String msg, Throwable ex, HttpStatus status) {
		super(msg, ex);
		this.httpStatus = status;
	}

	public CampanhaSocioTorcedorException(Object o, String msg) {
		super(msg);
		this.httpStatus = HttpStatus.BAD_REQUEST;
		this.obj = o;
	}

	public CampanhaSocioTorcedorException(Object o, String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
		this.obj = o;
	}

	public CampanhaSocioTorcedorException(Object o, String msg, Throwable ex, HttpStatus status) {
		super(msg, ex);
		this.httpStatus = status;
		this.obj = o;
	}

}
