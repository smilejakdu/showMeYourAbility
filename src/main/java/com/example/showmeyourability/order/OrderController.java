package com.example.showmeyourability.order;

import com.example.showmeyourability.order.application.CreateOrderApplication;
import com.example.showmeyourability.order.application.FindOrderByTeacherApplication;
import com.example.showmeyourability.order.application.UpdateOrderApplication;
import com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto.CreateOrderRequestDto;
import com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto.CreateOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.FindOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto.UpdateOrderRequestDto;
import com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto.UpdateOrderResponseDto;
import com.example.showmeyourability.shared.Service.SecurityService;
import com.example.showmeyourability.users.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "order", description = "order API")
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderApplication createOrderApplication;

    private final FindOrderByTeacherApplication findOrderByTeacherApplication;

    private final UpdateOrderApplication updateOrderApplication;

    private final SecurityService securityService;

    @PostMapping()
    public CreateOrderResponseDto createOrder(
            @RequestHeader("access-token") String token,
            @RequestBody CreateOrderRequestDto request
    ) {
        User responseUser = securityService.getSubject(token);
        return createOrderApplication.execute(responseUser, request);
    }

    @GetMapping("/{teacherId}")
    public FindOrderResponseDto getOrder(
            @RequestHeader("access-token") String token,
            @PathVariable("teacherId") Long teacherId
    ) {
        securityService.getSubject(token);
        return findOrderByTeacherApplication.execute(teacherId);
    }

    @PutMapping("/{orderId}")
    public UpdateOrderResponseDto updateOrder(
            @RequestHeader("access-token") String token,
            @PathVariable("orderId") Long orderId,
            @RequestBody UpdateOrderRequestDto request
            ) {
        User responseUser = securityService.getSubject(token);
        Long userId = responseUser.getId();
        return updateOrderApplication.execute(
                orderId,
                userId,
                request.getOrderStatus()
        );
    }
}
