package com.example.showmeyourability.shared.Exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpExceptionCustom extends RuntimeException {
    private boolean ok;
    private String message;
    private HttpStatus httpStatus;
}
