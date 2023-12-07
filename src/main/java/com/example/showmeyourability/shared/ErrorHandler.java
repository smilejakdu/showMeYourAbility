package com.example.showmeyourability.shared;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(HttpExceptionCustom.class)
    public ResponseEntity<String> handleException(HttpExceptionCustom e) {
        HttpStatus status = e.getHttpStatus();
        return new ResponseEntity<>("error = : " + e.getMessage(), status);
    }

}