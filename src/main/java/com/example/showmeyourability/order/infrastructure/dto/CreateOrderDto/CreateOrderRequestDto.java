package com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto;

import lombok.Data;

@Data
public class CreateOrderRequestDto {
    private String orderStatus;

    private Long teacherId;
}
