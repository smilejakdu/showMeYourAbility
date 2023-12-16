package com.example.showmeyourability.users.infrastructure.dto.UpdateUserDto;

import com.example.showmeyourability.users.domain.GenderType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserResponseDto {
    private Long id;
    private String email;
    private String img;
    private GenderType genderType;
    private int age;
}
