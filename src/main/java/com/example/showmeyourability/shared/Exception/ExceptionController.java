package com.example.showmeyourability.shared.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(HttpExceptionCustom.class)
    public ResponseEntity<ExceptionResponse> handleHttpException(HttpExceptionCustom e) {
        int httpStatus = e.getHttpStatus().value();
        System.out.println("httpStatus test : " + httpStatus);
        String message = e.getMessage();
        return ResponseEntity.status(e.getHttpStatus()).body(
                new ExceptionResponse(
                        false,
                        message,
                        httpStatus
                ));
    }
}
