package com.example.showmeyourability.users.infrastructure.dto.FindUserDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class FindUserRequestDto implements CheckValidity {

    private String email;

    @Override
    public void check() {
        if(email == null || email.isEmpty()) {
            throw new RuntimeException("Email is empty");
        }
    }
}
