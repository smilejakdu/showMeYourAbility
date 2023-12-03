package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.users.application.FindUserByEmailApplication;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.FindUserDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.webjars.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindUserApplicationTest {
    @Mock // 스프링 부트 테스트에서 사용하는 목(mock) 객체를 생성하는 어노테이션입니다
    private UserRepository userRepository;

    @InjectMocks
    private FindUserByEmailApplication findUserByEmailApplication;

    private User user1;
    private User user2;

    @BeforeEach // 각 테스트 메소드 실행 전에 호출되는 메소드를 지정
    public void setup() {
        String hashedPassword = BCrypt.hashpw("1234", BCrypt.gensalt());

        // User 객체 생성
        user1 = User.builder()
                .id(1L)
                .email("robertvsd1@gmail.com")
                .genderType(GenderType.MALE)
                .age(20)
                .img("img")
                .password(hashedPassword)
                .build();

        user2 = User.builder()
                .id(2L)
                .email("robertvsd2@gmail.com")
                .genderType(GenderType.MALE)
                .age(20)
                .img("img_two")
                .password(hashedPassword)
                .build();
    }


    @Test
    public void notFoundUserByEmailTest() {
        // given
        String notFoundEmail = "nonexistentemail@example.com";

        // userRepository.findByEmail에 대한 모의 동작 정의
        // 이메일로 사용자를 찾을 수 없을 때 빈 Optional 반환
        when(userRepository.findByEmail(notFoundEmail)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundException.class, () -> {
            // when
            findUserByEmailApplication.execute(notFoundEmail);
        });
    }

    @Test
    public void successTestFindUserByEmail() {
// given
        String email = "robertvsd1@gmail.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user1));

        FindUserByEmailResponseDto expectedResponse = new FindUserByEmailResponseDto();
        expectedResponse.setId(1L);
        expectedResponse.setEmail(email);

        // when
        FindUserByEmailResponseDto actualResponse = findUserByEmailApplication.execute(email);

        // then
        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());    }
}
