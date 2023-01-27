package com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto;

import com.example.showmeyourability.users.domain.GenderType;
import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private String name;

    private String email;

    private int age;

    private String password;

    private GenderType gender;
}
