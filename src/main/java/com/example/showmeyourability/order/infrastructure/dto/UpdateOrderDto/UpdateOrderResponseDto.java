package com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto;

import com.example.showmeyourability.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdateOrderResponseDto {
    private Long orderId;
    private OrderStatus orderStatus;
    private Long teacherId;

    @Builder
    public UpdateOrderResponseDto(
            Long orderId,
            OrderStatus orderStatus,
            Long teacherId
    ) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.teacherId = teacherId;
    }
}
