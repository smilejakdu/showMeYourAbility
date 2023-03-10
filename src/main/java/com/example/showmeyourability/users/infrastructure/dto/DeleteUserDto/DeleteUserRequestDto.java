package com.example.showmeyourability.users.infrastructure.dto.DeleteUserDto;

import com.example.showmeyourability.shared.CheckValidity;
import lombok.Data;

@Data
public class DeleteUserRequestDto implements CheckValidity {
    private String userId;
    @Override
    public void check() {
        if(userId == null || userId.isEmpty()) {
            throw new RuntimeException("userId is empty");
        }
    }

}
