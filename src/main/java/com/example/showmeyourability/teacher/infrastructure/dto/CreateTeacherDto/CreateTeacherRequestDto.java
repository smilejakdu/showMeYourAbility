package com.example.showmeyourability.teacher.infrastructure.dto.CreateTeacherDto;

import lombok.Data;

@Data
public class CreateTeacherRequestDto {
    private String name;

    private String email;

    private String skill;
}
