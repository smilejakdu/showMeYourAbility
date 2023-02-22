package com.example.showmeyourability.order.application;

import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.order.infrastructure.dto.DeleteOrderDto.DeleteOrderRequestDto;
import com.example.showmeyourability.order.infrastructure.dto.DeleteOrderDto.DeleteOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.repository.OrderRepository;
import com.example.showmeyourability.shared.Exception.HttpException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteOrderApplication {
    private final OrderRepository orderRepository;

    @Transactional
    public DeleteOrderResponseDto execute(
            DeleteOrderRequestDto deleteOrderRequestDto
    ) {
        Order order = orderRepository.findById(deleteOrderRequestDto.getOrderId())
                .orElseThrow(() -> new HttpException("Cannot find order with id: " + deleteOrderRequestDto.getOrderId(),
                        HttpStatus.NOT_FOUND));
        orderRepository.delete(order);
        return new DeleteOrderResponseDto(order.getId());
    }
}
