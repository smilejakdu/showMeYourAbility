package com.example.showmeyourability.order.infrastructure.dto.FindOrderDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FindOrderResponseDto {

    int lastPage;
    List<OrderDto> orderDtoList;

    @Builder
    public FindOrderResponseDto(
            int lastPage,
            List<OrderDto> orderDtoList
    ) {
        this.lastPage = lastPage;
        this.orderDtoList = orderDtoList;
    }
}

