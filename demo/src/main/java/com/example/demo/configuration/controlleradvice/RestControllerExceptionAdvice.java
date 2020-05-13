package com.example.demo.configuration.controlleradvice;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.configuration.response.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@org.springframework.web.bind.annotation.RestControllerAdvice
@Slf4j
public class RestControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handlerRuntimeException(RuntimeException e, HttpServletRequest req){
        log.error("================= Handler RuntimeException =================");
        return new ErrorResponse(
        	HttpStatus.BAD_REQUEST.value(), 
            "RuntimeException : "+e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, 
    		HttpServletRequest req){
        log.error("================= Handler MethodArgumentNotValidException =================");
        return new ErrorResponse(
        	HttpStatus.BAD_REQUEST.value(), 
            "MethodArgumentNotValidException : "+e.getMessage()
		);
    }
}