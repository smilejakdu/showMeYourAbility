package com.example.showmeyourability.order.infrastructure.dto.DeleteOrderDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteOrderRequestDto {
    @Schema(description = "주문 번호", example = "1")
    private Long orderId;
}
