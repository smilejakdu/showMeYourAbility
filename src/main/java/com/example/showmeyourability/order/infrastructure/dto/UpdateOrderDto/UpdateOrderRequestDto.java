package com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto;

import lombok.Data;

@Data
public class UpdateOrderRequestDto {
    private String orderStatus;

    private Long teacherId;
}
