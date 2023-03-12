package com.example.showmeyourability.shared.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private int code;
    private String message;
}


