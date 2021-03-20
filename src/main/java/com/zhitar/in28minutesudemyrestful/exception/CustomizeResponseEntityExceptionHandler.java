package com.zhitar.in28minutesudemyrestful.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice(annotations = RestController.class)
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.fromNow(e, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.fromNow(e, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<ExceptionResponse> handleDataAccessException(Exception e, WebRequest request) {
        return handleUserNotFoundException(e, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.fromNow("Validation failed", Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
