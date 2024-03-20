package com.example.showmeyourability.order.application;

import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.FindOrderByUserIdApplication;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.FindOrderResponseDto;
import com.example.showmeyourability.order.infrastructure.dto.FindOrderDto.OrderDto;
import com.example.showmeyourability.order.infrastructure.repository.OrderRepository;
import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOrderByUserApplication {
    private final OrderRepository orderRepository;

    @Transactional
    public FindOrderResponseDto execute(
            User user,
            FindOrderByUserIdApplication findOrderByUserIdApplication
    ) {
        int page = findOrderByUserIdApplication.getPage();
        int size = findOrderByUserIdApplication.getSize();
        List<Order> orders = orderRepository.findAllByUser(user, PageRequest.of(page, size));

        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            orderDtoList.add(convertToOrderDto(order));
        }

        int lastPage = orderRepository.findAllByUser(user, PageRequest.of(page, size)).size();
        return convertToFindOrderResponseDto(lastPage, orderDtoList);
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

    private FindOrderResponseDto convertToFindOrderResponseDto(
            int lastPage,
            List<OrderDto> orderDtoList
    ) {
        return FindOrderResponseDto.builder()
                .orderDtoList(orderDtoList)
                .lastPage(lastPage)
                .build();
    }
}
