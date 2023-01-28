package com.example.showmeyourability.users.infrastructure.dto.CreateUserDto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateUserResponseDto {
    private Long id;
    private String email;

    @Builder
    public CreateUserResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
