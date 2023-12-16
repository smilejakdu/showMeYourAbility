package com.example.showmeyourability.shared.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private Boolean ok;
    private String message;
    private int statusCode;
}
