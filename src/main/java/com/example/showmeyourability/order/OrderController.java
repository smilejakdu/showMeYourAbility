package com.example.showmeyourability.order;

import com.example.showmeyourability.order.application.CreateOrderApplication;
import com.example.showmeyourability.order.application.FindOrderByTeacherApplication;
import com.example.showmeyourability.order.application.UpdateOrderApplication;
import com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto.CreateOrderRequestDto;
import com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto.CreateOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.FindOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto.UpdateOrderRequestDto;
import com.example.showmeyourability.order.infrastructure.dto.UpdateOrderDto.UpdateOrderResponseDto;
import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.shared.Service.securityService.SecurityService;
import com.example.showmeyourability.users.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.showmeyourability.shared.CoreSuccessResponse.coreSuccessResponse;

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
    public CoreSuccessResponse createOrder(
            HttpServletRequest httpServletRequest,
            @RequestBody CreateOrderRequestDto createOrderRequestDto
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        User responseUser = securityService.getTokenByCookie(cookies);
        CreateOrderResponseDto createOrderResponseDto = createOrderApplication.execute(responseUser, createOrderRequestDto);
         return coreSuccessResponse(true, createOrderResponseDto, "주문 성공", HttpStatus.CREATED.value());
    }

    @GetMapping("/{teacherId}")
    public CoreSuccessResponse getOrder(
            HttpServletRequest httpServletRequest,
            @PathVariable("teacherId") Long teacherId
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        securityService.getTokenByCookie(cookies);
        FindOrderResponseDto findOrderResponseDto = findOrderByTeacherApplication.execute(teacherId);
        return coreSuccessResponse(true, findOrderResponseDto, "주문 조회 성공", HttpStatus.OK.value());
    }

    @PutMapping("/{orderId}")
    public CoreSuccessResponse updateOrder(
            HttpServletRequest httpServletRequest,
            @PathVariable("orderId") Long orderId,
            @RequestBody UpdateOrderRequestDto request
    ) {
        Cookie[] cookies = httpServletRequest.getCookies();
        User responseUser = securityService.getTokenByCookie(cookies);
        Long userId = responseUser.getId();
        UpdateOrderResponseDto updateOrderResponseDto = updateOrderApplication.execute(
                orderId,
                userId,
                request.getOrderStatus()
        );
        return coreSuccessResponse(true, updateOrderResponseDto, "주문 수정 성공", HttpStatus.OK.value());
    }
}
