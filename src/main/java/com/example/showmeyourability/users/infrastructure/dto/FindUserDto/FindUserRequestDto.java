package com.example.showmeyourability.users.infrastructure.dto.FindUserDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindUserRequestDto{
    @Schema(description = "email", example = "email")
    private String email;
}
