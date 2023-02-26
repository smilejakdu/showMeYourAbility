package com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto;

import com.example.showmeyourability.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateOrderResponseDto {
    private Long orderId;

    private OrderStatus orderStatus;

    private Long teacherId;

    private Long userId;

    @Builder
    public CreateOrderResponseDto(
            Long orderId,
            OrderStatus orderStatus,
            Long teacherId,
            Long userId
    ) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.teacherId = teacherId;
        this.userId = userId;
    }
}
