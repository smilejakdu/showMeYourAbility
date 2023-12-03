package com.example.showmeyourability.users.application;

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
                .orElseThrow(() -> new NotFoundException("해당 유저가 존재하지 않습니다."));

        return FindUserByEmailResponseDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
