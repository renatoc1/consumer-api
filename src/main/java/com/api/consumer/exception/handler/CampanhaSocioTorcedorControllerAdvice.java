package com.api.consumer.exception.handler;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.consumer.exception.CampanhaSocioTorcedorException;
import com.api.consumer.exception.ExceptionResponse;

@RestControllerAdvice
public class CampanhaSocioTorcedorControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(CampanhaSocioTorcedorException.class)
	public final ResponseEntity<ExceptionResponse> campanhaSocioTorcedorException(CampanhaSocioTorcedorException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, ex.getHttpStatus());
	}
	
}
