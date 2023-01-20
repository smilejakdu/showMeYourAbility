package com.example.showmeyourability.comments.domain;

import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.users.domain.User;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "comments")
public class Comments extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 1000, name = "content")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
