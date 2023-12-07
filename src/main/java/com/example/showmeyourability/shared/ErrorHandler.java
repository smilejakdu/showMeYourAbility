package com.example.showmeyourability.shared;

import com.example.showmeyourability.shared.Exception.ExceptionResponse;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(HttpExceptionCustom.class)
    public ResponseEntity<ExceptionResponse> handleException(HttpExceptionCustom e) {
        HttpStatus status = e.getHttpStatus();

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                false,
                e.getMessage(),
                status.value()
        );
        return new ResponseEntity<>(exceptionResponse, status);
    }
}