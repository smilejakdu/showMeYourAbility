package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import lombok.Builder;
import lombok.Data;


@Data
public class TeacherDto {
    private Long id;
    private String career;

    private String email;

    private String skill;

    private Long userId;

    @Builder
    public TeacherDto(Long id, String career, String email, String skill, Long userId) {
        this.id = id;
        this.career = career;
        this.email = email;
        this.skill = skill;
        this.userId = userId;
    }
}
