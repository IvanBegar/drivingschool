package com.begar.demo.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String massage;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String massege, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.massage = massege;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMassage() {
        return massage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
