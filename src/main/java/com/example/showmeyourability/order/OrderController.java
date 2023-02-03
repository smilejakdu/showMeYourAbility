package com.example.showmeyourability.order;

import com.example.showmeyourability.order.application.CreateOrderApplication;
import com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto.CreateOrderRequestDto;
import com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto.CreateOrderResponseDto;
import com.example.showmeyourability.shared.SecurityService;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderApplication createOrderApplication;

    private final SecurityService securityService;

    @PostMapping()
    public CreateOrderResponseDto createOrder(
            @RequestHeader("access-token") String token,
            @RequestBody CreateOrderRequestDto request
    ) {
        User responseUser = securityService.getSubject(token);
        return createOrderApplication.execute(responseUser, request);
    }
}