package com.example.showmeyourability.teacher.infrastructure.dto.CreateTeacherDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateTeacherRequestDto {
    private String career;
    private String email;
    private String skill;
}
