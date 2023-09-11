package com.example.showmeyourability.order.application;

import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.FindOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.OrderDto;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOrderByTeacherApplication {
    private final TeacherRepository teacherRepository;

    @Transactional
    public FindOrderResponseDto execute(
            User user,
            Long teacherId
    ) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("해당 선생님을 찾을 수 없습니다."));

        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : teacher.getOrders()) {
            orderDtoList.add(OrderDto.builder()
                    .orderId(order.getId())
                    .orderStatus(order.getOrderStatus())
                    .teacherId(order.getTeacher().getId())
                    .teacherEmail(order.getTeacher().getUser().getEmail())
                    .teacherSkill(order.getTeacher().getSkill())
                    .build());
        }

        return FindOrderResponseDto.builder()
                .orderDtoList(orderDtoList)
                .build();
    }
}
