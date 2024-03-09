package com.example.showmeyourability.shared;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoreSuccessResponse {
    public static CoreSuccessResponse coreSuccessResponse(
            boolean ok,
            Object data,
            String message
    ) {
        return CoreSuccessResponse.builder()
                .ok(ok)
                .message(message)
                .data(data)
                .build();
    }

    private boolean ok;
    private String message;
    private Object data;
}

