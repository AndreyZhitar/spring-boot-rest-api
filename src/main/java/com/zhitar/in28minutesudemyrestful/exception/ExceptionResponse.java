package com.zhitar.in28minutesudemyrestful.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public final class ExceptionResponse {

    private ExceptionResponse(Exception e, WebRequest request) {
        this(e.getMessage(), request.getDescription(false));
    }

    public ExceptionResponse(String message, String details) {
        this.dateTime = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }

    private final LocalDateTime dateTime;
    private final String message;
    private final String details;

    public static ExceptionResponse fromNow(Exception e, WebRequest request) {
        return new ExceptionResponse(e, request);
    }

    public static ExceptionResponse fromNow(String message, String details) {
        return new ExceptionResponse(message, details);
    }

}
