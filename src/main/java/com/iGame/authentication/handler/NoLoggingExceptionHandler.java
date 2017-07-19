package com.iGame.authentication.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iGame.exception.NoLoggingException;

@ControllerAdvice
public class NoLoggingExceptionHandler {

	/*
	 * 偵測到未登入時會拋出NoLoggingException
	 * 將頁面導至登入頁面
	 */
	@ExceptionHandler(NoLoggingException.class)
    public void handleNoLoggingException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendRedirect("/login");
    }
}
