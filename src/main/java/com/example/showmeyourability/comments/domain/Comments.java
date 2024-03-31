package com.example.showmeyourability.comments.domain;

import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.users.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "comments")
public class Comments extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000, name = "content")
    private String content;

    @Column(length = 1000, name = "likes")
    private Double likes;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public static Comments of(
            User user,
            Teacher teacher,
            String content,
            Double likes
    ) {
        return Comments.builder()
                .user(user)
                .teacher(teacher)
                .likes(likes)
                .content(content)
                .build();
    }
}
