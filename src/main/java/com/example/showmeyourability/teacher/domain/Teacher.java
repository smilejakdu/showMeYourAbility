package com.example.showmeyourability.teacher.domain;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.order.domain.Order;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.users.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "teachers", indexes = {
        @Index(name = "teacher_index", columnList = "id")
})
public class Teacher extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "career")
    private String career;

    @Column(nullable = false, length = 100, name = "skill")
    private String skill;

    @OneToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private List<Comments> comments = Collections.emptyList();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private List<Order> orders = Collections.emptyList();

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
