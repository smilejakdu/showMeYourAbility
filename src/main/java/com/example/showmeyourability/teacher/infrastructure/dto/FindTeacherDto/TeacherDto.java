package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private Long id;
    private String career;
    private String email;
    private String skill;
    private Long userId;
    private Double avgScore;
    private LocalDateTime createdAt;
}
