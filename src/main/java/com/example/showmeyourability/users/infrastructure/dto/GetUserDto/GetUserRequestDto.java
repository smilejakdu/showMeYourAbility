package com.example.showmeyourability.users.infrastructure.dto.GetUserDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class GetUserRequestDto implements CheckValidity {

    private String email;

    @Override
    public void check() {
        if(email == null || email.isEmpty()) {
            throw new RuntimeException("Email is empty");
        }
    }
}
