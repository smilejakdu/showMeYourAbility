package com.example.showmeyourability.order.infrastructure.dto.FindOrderDto;

import lombok.Data;

@Data
public class FindOrderByUserIdApplication {
    private Long userId;
    int page;

    int size;
}
