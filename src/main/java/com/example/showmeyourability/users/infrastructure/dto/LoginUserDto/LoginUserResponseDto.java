package com.example.showmeyourability.users.infrastructure.dto.LoginUserDto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginUserResponseDto {
    private String email;

    private String token;

    @Builder
    public LoginUserResponseDto(
            String email,
            String token
    ) {
        this.email = email;
        this.token = token;
    }
}
