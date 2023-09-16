package com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto;

import com.example.showmeyourability.users.domain.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserRequestDto {
    @Schema(description = "name", example = "name")
    @NotEmpty(message = "name 을 입력해주세요.")
    private String name;

    @Schema(description = "email", example = "이메일")
    @NotEmpty(message = "email을 입력해주세요.")
    private String email;

    @Schema(description = "age", example = "나이")
    private int age;

    @Schema(description = "password", example = "패스워드")
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @Schema(description = "gender", example = "성별")
    @NotEmpty(message = "성별을 입력해주세요.")
    private GenderType gender;

    @Schema(description = "img", example = "이미지")
    private String img;
}
