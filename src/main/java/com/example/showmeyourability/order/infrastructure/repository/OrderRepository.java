package com.example.showmeyourability.order.infrastructure.repository;

import com.example.showmeyourability.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
