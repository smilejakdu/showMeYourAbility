package com.example.showmeyourability.reply.domain;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.users.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
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

    @Column(nullable = false, length = 1000, name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
