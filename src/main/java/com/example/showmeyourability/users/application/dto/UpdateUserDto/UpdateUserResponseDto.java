package com.example.showmeyourability.users.application.dto.UpdateUserDto;

import com.example.showmeyourability.users.domain.GenderType;
import lombok.Data;

@Data
public class UpdateUserResponseDto {

    private String email;

    private GenderType gender;

    private int age;
}
