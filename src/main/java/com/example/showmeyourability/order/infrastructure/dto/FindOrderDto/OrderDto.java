package com.example.showmeyourability.order.infrastructure.dto.FindOrderDto;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderDto {
    private Long orderId;

    private String orderStatus;

    private Long teacherId;

    private String teacherSkill;

    private String teacherEmail;

    @Builder
    public OrderDto(
            Long orderId,
            String orderStatus,
            Long teacherId,
            String teacherEmail,
            String teacherSkill
    ) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.teacherId = teacherId;
        this.teacherEmail = teacherEmail;
        this.teacherSkill = teacherSkill;
    }
}
