package com.example.showmeyourability.shared.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private Boolean ok;
    private String message;
    private int statusCode;

    public ExceptionResponse(
            boolean ok,
            String message,
            int statusCode
    ) {
        this.ok = ok;
        this.message = message;
        this.statusCode = statusCode;
    }
}


