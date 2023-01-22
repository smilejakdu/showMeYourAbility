package com.example.showmeyourability.users.application.dto.FindUserByEmailDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class FindUserByEmailRequestDto implements CheckValidity {

    private String email;

    @Override
    public void check() {
        if(email == null || email.isEmpty()) {
            throw new RuntimeException("email is empty");
        }
    }
}
