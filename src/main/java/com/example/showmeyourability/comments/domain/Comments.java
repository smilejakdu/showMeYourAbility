package com.example.showmeyourability.comments.domain;

import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.users.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comments extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000, name = "content")
    private String content;

    @Column(nullable = false, length = 1000, name = "likes")
    private Long likes;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Builder
    public Comments(
            String content,
            Long likes,
            User user,
            Teacher teacher
    ) {
        this.content = content;
        this.likes = likes;
        this.user = user;
        this.teacher = teacher;
    }
}
