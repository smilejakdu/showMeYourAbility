package com.example.showmeyourability.users.infrastructure.dto.CreateUserDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserResponseDto {
    private Long id;
    private String email;
}
