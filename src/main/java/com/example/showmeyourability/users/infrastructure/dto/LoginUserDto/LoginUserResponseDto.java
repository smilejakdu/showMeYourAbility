package com.example.showmeyourability.users.infrastructure.dto.LoginUserDto;

import lombok.Data;

@Data
public class LoginUserResponseDto {
    private String email;

    private String token;
}
