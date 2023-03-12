package com.example.showmeyourability.order.application;

import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto.CreateOrderRequestDto;
import com.example.showmeyourability.order.infrastructure.dto.CreateOrderDto.CreateOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.repository.OrderRepository;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderApplication {
    private final OrderRepository orderRepository;

    private final TeacherRepository teacherRepository;

    @Transactional
    public CreateOrderResponseDto execute(
            User user,
            CreateOrderRequestDto createOrderRequestDto
    ) {
        Teacher teacher = teacherRepository.findById(createOrderRequestDto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("해당 선생님을 찾을 수 없습니다."));

        Order newOrder = Order.builder()
                .orderStatus(createOrderRequestDto.getOrderStatus())
                .user(user)
                .teacher(teacher)
                .build();

        Order order = orderRepository.save(newOrder);
        return convertToCreateOrderResponseDto(order);
    }

    private CreateOrderResponseDto convertToCreateOrderResponseDto(Order order) {
        return CreateOrderResponseDto.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .teacherId(order.getTeacher().getId())
                .userId(order.getUser().getId())
                .build();
    }
}
