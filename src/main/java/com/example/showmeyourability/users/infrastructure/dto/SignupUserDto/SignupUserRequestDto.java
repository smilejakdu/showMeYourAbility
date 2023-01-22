package com.example.showmeyourability.users.infrastructure.dto.SignupUserDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class SignupUserRequestDto implements CheckValidity {

    private String email;

    private String password;

    @Override
    public void check() {
        if(email == null || email.isEmpty()) {
            throw new RuntimeException("email is empty");
        }

        if (password == null || password.isEmpty()) {
            throw new RuntimeException("password is empty");
        }
    }
}
