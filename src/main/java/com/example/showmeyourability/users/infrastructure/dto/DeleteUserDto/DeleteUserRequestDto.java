package com.example.showmeyourability.users.infrastructure.dto.DeleteUserDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeleteUserRequestDto {
    @Schema(description = "userId", example = "유저 아이디")
    private Long userId;
}
