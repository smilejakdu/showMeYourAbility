package com.example.showmeyourability.users.domain;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Column()
    private int age;

    @Column()
    private String img;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private List<Comments> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();
}
