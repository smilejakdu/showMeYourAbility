package com.example.showmeyourability.users.infrastructure.dto.CreateUserDto;

import com.example.showmeyourability.users.domain.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateUserRequestDto {
    @Schema(description = "email", example = "이메일")
    private String email;

    @Schema(description = "password", example = "password")
    private String password;

    @Schema(description = "genderType", example = "genderType")
    private GenderType genderType;

    @Schema(description = "age", example = "나이")
    private int age;

    @Schema(description = "phone", example = "phone")
    private String phone;

    @Schema(description = "img", example = "img")
    private String img;
}
