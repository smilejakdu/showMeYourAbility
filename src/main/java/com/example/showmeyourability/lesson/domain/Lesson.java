package com.example.showmeyourability.lesson.domain;

import com.example.showmeyourability.Field.domain.Field;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "lessons")
public class Lesson extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000, name = "content")
    private String content;

    @Column(length = 1000, name = "likes")
    private Double likes;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "field_id")
    private Field field;
}
