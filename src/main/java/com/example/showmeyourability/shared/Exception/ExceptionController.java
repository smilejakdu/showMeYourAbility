package com.example.showmeyourability.shared.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ExceptionResponse> handleHttpException(HttpException e) {
        String message = e.getMessage();
        HttpStatus httpStatus = e.getHttpStatus();
        return ResponseEntity.status(e.getHttpStatus()).body(
                new ExceptionResponse(
                        httpStatus.value(),
                        message
                ));
    }
}
