package com.example.showmeyourability.shared.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class HttpExceptionCustom extends RuntimeException {
    private boolean ok;
    private String message;
    private HttpStatus httpStatus;

    public HttpExceptionCustom(boolean ok, String message, HttpStatus httpStatus) {
        super(message);
        this.ok = ok;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}