package com.begar.demo.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = NoDataException.class)
    public ResponseEntity<Object> handleDataException(NoDataException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(), badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(e.getMessage(), notFound, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = ServletException.class)
    public ResponseEntity<Object> handleServletException(ServletException e) {
        HttpStatus methodNotAllowed = HttpStatus.METHOD_NOT_ALLOWED;
        ApiException apiException = new ApiException(e.getMessage(), methodNotAllowed, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, methodNotAllowed);
    }
}
