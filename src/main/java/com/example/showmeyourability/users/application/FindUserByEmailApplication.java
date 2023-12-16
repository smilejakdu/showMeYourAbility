package com.example.showmeyourability.users.application;

import com.example.showmeyourability.shared.Exception.ErrorCode;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.FindUserDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class FindUserByEmailApplication {
    private final UserRepository userRepository;

    @Transactional
    public FindUserByEmailResponseDto execute(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        ErrorCode.NOT_FOUND_DATA.getMessage()
                ));

        return FindUserByEmailResponseDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
