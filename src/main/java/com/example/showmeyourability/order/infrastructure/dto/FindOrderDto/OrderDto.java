package com.example.showmeyourability.order.infrastructure.dto.FindOrderDto;

import com.example.showmeyourability.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderDto {
    private Long orderId;

    private OrderStatus orderStatus;

    private Long userId;

    private Long teacherId;

    private String teacherSkill;

    private String teacherEmail;

    @Builder
    public OrderDto(
            Long orderId,
            OrderStatus orderStatus,
            Long userId,
            Long teacherId,
            String teacherEmail,
            String teacherSkill
    ) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.teacherId = teacherId;
        this.teacherEmail = teacherEmail;
        this.teacherSkill = teacherSkill;
    }
}
