package com.example.showmeyourability.shared.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class HttpExceptionCustom extends RuntimeException {
    private boolean ok;
    private String message;
    private HttpStatus httpStatus;
}
