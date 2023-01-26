package com.example.showmeyourability.users.application;

import com.example.showmeyourability.users.application.dto.FindUserByEmailDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindUserByEmailApplication {
    private final UserRepository userRepository;

    @Transactional
    public FindUserByEmailResponseDto findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(db->{
                    FindUserByEmailResponseDto responseDto = new FindUserByEmailResponseDto();
                    responseDto.setEmail(db.getEmail());
                    responseDto.setComments(db.getComments());
                    return responseDto;
                }).orElseThrow();

    }
}
