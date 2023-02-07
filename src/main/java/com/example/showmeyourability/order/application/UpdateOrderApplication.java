package com.example.showmeyourability.order.application;

import com.example.showmeyourability.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOrderApplication {
    private final FindOrderByUserApplication findOrderApplication;

    @Transactional
    public void updateOrder(
            User user,
            Long orderId,
            String orderName
    ) {
//      먼저 Order 에 있는 userId 가 같은지 확인을 먼저한다.
//      update 니까 orderStatus 와 teacher 를 변경한다.
    }
}
