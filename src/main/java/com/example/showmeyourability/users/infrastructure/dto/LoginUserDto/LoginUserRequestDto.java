package com.example.showmeyourability.users.infrastructure.dto.LoginUserDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserRequestDto {
    @Schema(description = "email", example = "이메일")
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;
    @Schema(description = "password", example = "패스워드")
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
}
