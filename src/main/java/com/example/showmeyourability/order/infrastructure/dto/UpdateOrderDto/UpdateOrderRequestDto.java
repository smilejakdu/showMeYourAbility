package com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto;

import com.example.showmeyourability.order.domain.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderRequestDto {
    private OrderStatus orderStatus;

    private Long teacherId;
}
