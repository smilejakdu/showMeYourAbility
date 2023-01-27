package com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto;

import com.example.showmeyourability.users.domain.GenderType;
import lombok.Data;

@Data
public class UpdateUserResponseDto {
    private String email;

    private int age;

    private GenderType gender;
}
