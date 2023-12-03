package com.example.showmeyourability.users.infrastructure.dto.FindUserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindUserByEmailResponseDto {
    private Long id;
    private String email;
}
