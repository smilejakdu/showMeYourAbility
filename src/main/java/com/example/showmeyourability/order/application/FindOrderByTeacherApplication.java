package com.example.showmeyourability.order.application;

import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.FindOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.OrderDto;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindOrderByTeacherApplication {
    private final JPAQueryFactory queryFactory;

    @Transactional
    public FindOrderResponseDto execute(Long teacherId) {
        QTeacher qTeacher = QTeacher.teacher;

        Teacher teacher = queryFactory.selectFrom(qTeacher)
                .where(qTeacher.id.eq(teacherId))
                .leftJoin(qTeacher.orders).fetchJoin()
                .fetchOne();

        List<OrderDto> orderDtoList = Optional.ofNullable(teacher)
                .map(Teacher::getOrders)
                .map(orders -> orders.stream().map(this::convertToOrderDto).collect(Collectors.toList()))
                .orElseGet(ArrayList::new);

        return FindOrderResponseDto.builder()
                .orderDtoList(orderDtoList)
                .build();
    }

    private OrderDto convertToOrderDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .teacherId(order.getTeacher().getId())
                .teacherEmail(order.getTeacher().getUser().getEmail())
                .teacherSkill(order.getTeacher().getSkill())
                .build();
    }
}
