package com.example.showmeyourability.users.application;

import com.example.showmeyourability.users.infrastructure.dto.FindUserDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindUserByEmailApplication {
    private final UserRepository userRepository;

    @Transactional
    public FindUserByEmailResponseDto execute(String email) {
        return userRepository.findByEmail(email)
                .map(db->{
                    FindUserByEmailResponseDto responseDto = new FindUserByEmailResponseDto();
                    responseDto.setId(db.getId());
                    responseDto.setEmail(db.getEmail());
                    return responseDto;
                }).orElseThrow();
    }
}
