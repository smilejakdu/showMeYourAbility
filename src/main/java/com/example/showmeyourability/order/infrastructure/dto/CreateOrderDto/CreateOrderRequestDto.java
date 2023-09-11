package com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto;

import com.example.showmeyourability.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDto {
    private OrderStatus orderStatus;
    private Long teacherId;
}
