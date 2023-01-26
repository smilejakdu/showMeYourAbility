package com.example.showmeyourability.teacher.domain;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.users.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "teachers")
public class Teacher extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "career")
    private String career;

    @Column(nullable = false, length = 100, name = "skill")
    private String skill;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private List<Comments> comments = Collections.emptyList();
}
