package com.example.showmeyourability.users.application.dto.GetUserDto;

import com.example.showmeyourability.users.domain.User;
import lombok.Data;

@Data
public class GetUserResponseDto {
    private User user;
}
