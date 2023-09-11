package com.example.showmeyourability.users.infrastructure.dto.LoginUserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserResponseDto {
    private String email;
    private String token;
}
