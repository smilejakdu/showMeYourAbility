package com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto;

import com.example.showmeyourability.comments.domain.Comments;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
public class TeacherDto {
    private Long id;
    private String career;

    private String email;

    private String skill;

    private Long userId;

    private List<Comments> comments;

    private Double avgScore;

    @Builder
    public TeacherDto(
            Long id,
            String career,
            String email,
            String skill,
            Long userId,
            List<Comments> comments
            ) {
        this.id = id;
        this.career = career;
        this.email = email;
        this.skill = skill;
        this.userId = userId;
        this.comments = comments;
    }
}
