package com.example.showmeyourability.Field.entity;

import com.example.showmeyourability.lesson.domain.Lesson;
import com.example.showmeyourability.shared.BaseTimeEntitiy;
import com.example.showmeyourability.shared.Enum.FieldType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "fields")
public class Field extends BaseTimeEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field_type")
    @Enumerated(EnumType.STRING)
    private FieldType fieldType;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "field")
    private List<Lesson> lessons = Collections.emptyList();

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    public LocalDateTime deletedAt;
}
