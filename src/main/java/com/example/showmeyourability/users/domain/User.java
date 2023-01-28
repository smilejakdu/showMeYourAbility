package com.example.showmeyourability.users.domain;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Column(nullable = false)
    private int age;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Comments> comments = new ArrayList<>();

    @Builder
    public User(String email, String password, GenderType genderType, int age) {
        this.email = email;
        this.password = password;
        this.genderType = genderType;
        this.age = age;
    }
}
