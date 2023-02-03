package com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class UpdateOrderRequestDto {

    private final UpdateOrderRequestDto updateOrderRequestDto;
}
