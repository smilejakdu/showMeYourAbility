package com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateOrderResponseDto {
    private Long orderId;

    private String orderStatus;

    private Long teacherId;

    private Long userId;

    @Builder
    public CreateOrderResponseDto(
            Long orderId,
            String orderStatus,
            Long teacherId,
            Long userId
    ) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.teacherId = teacherId;
        this.userId = userId;
    }
}
