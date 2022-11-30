package com.common;
/*
 * [1] @ExceptionHandler를 이용하는 방법
 * [2] @ControllerAdvice를 이용하는 방법
 * [3] @ResponseStatus를 이용해서 HTTP상태코드 처리하는 방법
 * 
 * */

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	@ExceptionHandler(NumberFormatException.class)
	public String cmmNumberException(Exception e) {
		log.error(e);
		return "/login/errorAlert";
	}
	
	
}
