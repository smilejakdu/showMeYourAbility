package com.example.showmeyourability.order.domain;

import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.users.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderStatus;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Builder
    public Order (
            String orderStatus,
            User user,
            Teacher teacher
    ) {
        this.orderStatus = orderStatus;
        this.user = user;
        this.teacher = teacher;
    }
}
