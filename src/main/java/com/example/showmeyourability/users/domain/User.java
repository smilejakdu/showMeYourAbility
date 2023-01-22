package com.example.showmeyourability.users.domain;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import jakarta.persistence.*;
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
    private Enum<GenderType> gender;

    @Column(nullable = false)
    private int age;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Comments> comments = new ArrayList<>();
}
