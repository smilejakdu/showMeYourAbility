package com.example.showmeyourability.shared.Exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    @JsonProperty("ok")
    private Boolean ok;
    @JsonProperty("message")
    private String message;
    @JsonProperty("statusCode")
    private int statusCode;
}


