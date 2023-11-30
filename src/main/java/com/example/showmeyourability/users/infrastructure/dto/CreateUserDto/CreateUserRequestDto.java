package com.example.showmeyourability.users.infrastructure.dto.CreateUserDto;

import com.example.showmeyourability.shared.CheckValidity;
import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import com.example.showmeyourability.users.domain.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CreateUserRequestDto implements CheckValidity {
    @Schema(description = "email", example = "이메일")
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @Schema(description = "password", example = "password")
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @Schema(description = "genderType", example = "FEMALE")
    private GenderType genderType;

    @Schema(description = "age", example = "나이")
    private int age;

    @Schema(description = "phone", example = "phone")
    private String phone;

    @Schema(description = "img", example = "img")
    private String img;

    @Override
    public void check() {
        if (this.getEmail() != null) {
            throw new HttpExceptionCustom(
                    false,
                    "이메일을 입력해주세요",
                    HttpStatus.BAD_REQUEST
                    );
        }

        if (!this.getEmail().matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+$")) {
            throw new HttpExceptionCustom(
                    false,
                    "이메일 형식을 지켜주세요",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
