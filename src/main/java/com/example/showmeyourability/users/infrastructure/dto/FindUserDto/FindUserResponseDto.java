package com.example.showmeyourability.users.infrastructure.dto.FindUserDto;

import com.example.showmeyourability.users.domain.User;
import lombok.Data;

@Data
public class FindUserResponseDto {
    private User user;
}
