package com.example.showmeyourability.users.infrastructure.dto.CreateUserDto;

import com.example.showmeyourability.shared.CheckValidity;
import com.example.showmeyourability.users.domain.GenderType;
import lombok.Data;

@Data
public class CreateUserRequestDto implements CheckValidity {

    private String email;
    private String password;
    private GenderType genderType;
    private int age;

    @Override
    public void check() {
        if(email == null || email.isEmpty()) {
            throw new RuntimeException("email is empty");
        }

        if(password == null || password.isEmpty()) {
            throw new RuntimeException("password is empty");
        }

        if (age < 0) {
            throw new RuntimeException("age is negative");
        }
    }
}
