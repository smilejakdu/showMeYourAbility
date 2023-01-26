package com.example.showmeyourability.users.application.dto.UpdateUserDto;

import com.example.showmeyourability.shared.CheckValidity;
import com.example.showmeyourability.users.domain.GenderType;
import lombok.Data;

@Data
public class UpdateUserRequestDto implements CheckValidity {
    private String email;

    private GenderType gender;

    private String password;

    private int age;

    @Override
    public void check() {
        if(email == null || email.isEmpty()) {
            throw new RuntimeException("email is empty");
        }

        if (password == null || password.isEmpty()) {
            throw new RuntimeException("password is empty");
        }

        if (age < 1 ) {
            throw new RuntimeException("age bigger than zero");
        }
    }
}
