package com.example.showmeyourability.users.infrastructure.dto.LoginUserDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class LoginUserRequestDto implements CheckValidity {

    private String email;

    private String password;
    @Override
    public void check() {
        if(email == null || email.isEmpty()) {
            throw new RuntimeException("Email is empty");
        }

        if(password == null || password.isEmpty()) {
            throw new RuntimeException("Password is empty");
        }
    }
}
