package com.example.showmeyourability.order.domain;

import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.users.domain.User;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Builder
    public Order (
            OrderStatus orderStatus,
            User user,
            Teacher teacher
    ) {
        this.orderStatus = orderStatus;
        this.user = user;
        this.teacher = teacher;
    }
}
