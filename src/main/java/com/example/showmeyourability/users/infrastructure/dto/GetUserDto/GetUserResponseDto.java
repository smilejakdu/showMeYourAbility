package com.example.showmeyourability.users.infrastructure.dto.GetUserDto;

import com.example.showmeyourability.users.domain.User;
import lombok.Data;

@Data
public class GetUserResponseDto {
    private User user;
}
