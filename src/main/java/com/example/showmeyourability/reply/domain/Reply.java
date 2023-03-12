package com.example.showmeyourability.reply.domain;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.users.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "reply")
public class Reply extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "comment_id")
    private Comments comments;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 1000, name = "content")
    private String content;

    @Builder
    public Reply(
            Comments comments,
            User user,
            String content
    ) {
        this.user = user;
        this.comments = comments;
        this.content = content;
    }
}
