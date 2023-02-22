package com.example.showmeyourability.order.infrastructure.dto.DeleteOrderDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class DeleteOrderRequestDto implements CheckValidity {
    private Long orderId;

    @Override
    public void check() {
        if (orderId == null) {
            throw new RuntimeException("orderId is empty");
        }
    }
}
