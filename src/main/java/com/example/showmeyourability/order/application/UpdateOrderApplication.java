package com.example.showmeyourability.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOrderApplication {
    private final FindOrderByUserApplication findOrderApplication;

    @Transactional
    public void updateOrder(Long orderId, String orderName) {
    }
}
