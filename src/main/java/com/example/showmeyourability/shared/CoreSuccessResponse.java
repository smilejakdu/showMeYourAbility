package com.example.showmeyourability.shared;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoreSuccessResponse {
    private boolean ok;
    private String message;
    private Object data;
}

