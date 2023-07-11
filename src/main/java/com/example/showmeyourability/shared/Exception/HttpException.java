package com.example.showmeyourability.shared.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class HttpException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
}
