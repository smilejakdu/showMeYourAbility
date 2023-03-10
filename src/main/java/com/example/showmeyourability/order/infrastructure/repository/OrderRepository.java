package com.example.showmeyourability.order.infrastructure.repository;

import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.users.domain.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user, PageRequest pageRequest);

    Optional<Order> findByIdAndUserId(Long id, Long userId);
}
