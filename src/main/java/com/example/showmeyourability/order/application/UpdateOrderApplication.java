package com.example.showmeyourability.order.application;

import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.order.domain.OrderStatus;
import com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto.UpdateOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOrderApplication {
    private final OrderRepository orderRepository;

    @Transactional
    public UpdateOrderResponseDto execute(
            Long orderId,
            Long userId,
            OrderStatus orderStatus
    ) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다."));
        order.setOrderStatus(orderStatus);
        return UpdateOrderResponseDto.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .teacherId(order.getTeacher().getId())
                .build();
    }
}
