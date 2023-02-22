package com.example.showmeyourability.order.infrastructure.dto.DeleteOrderDto;

import lombok.Builder;
import lombok.Data;

@Data
public class DeleteOrderResponseDto {

    private Long orderId;

    @Builder
    public DeleteOrderResponseDto(Long orderId) {
        this.orderId = orderId;
    }
}
