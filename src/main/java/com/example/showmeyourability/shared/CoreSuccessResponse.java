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
            String message,
            int httpStatus
    ) {
        return CoreSuccessResponse.builder()
                .ok(ok)
                .httpStatus(httpStatus)
                .message(message)
                .data(data)
                .build();
    }

    private boolean ok;
    private int httpStatus;
    private String message;
    private Object data;
}

