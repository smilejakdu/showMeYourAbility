package com.example.showmeyourability.reply.domain;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.users.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "reply")
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000, name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comments parentComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Reply(
            String content,
            Comments comments,
            User user
    ) {
        this.content = content;
        this.user = user;
        this.parentComment = comments;
    }
}
