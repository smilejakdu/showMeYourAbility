package com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto;

import com.example.showmeyourability.order.domain.OrderStatus;
import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class CreateOrderRequestDto implements CheckValidity {
    private OrderStatus orderStatus;

    private Long teacherId;

    @Override
    public void check() {
        if (orderStatus == null) {
            throw new RuntimeException("orderStatus is empty");
        }

        if (teacherId == null) {
            throw new RuntimeException("teacherId is empty");
        }
    }
}
