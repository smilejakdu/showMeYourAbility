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

    private String img;

    @Override
    public void check() {
        if(email == null || email.isEmpty()) {
            throw new RuntimeException("email is empty");
        }

      if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
          throw new RuntimeException("email is not valid");
      }

        if(password == null || password.isEmpty()) {
            throw new RuntimeException("password is empty");
        }

        if (age < 0) {
            throw new RuntimeException("age is negative");
        }
    }
}
