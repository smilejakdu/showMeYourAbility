package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.users.application.FindUserByEmailApplication;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.FindUserDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.webjars.NotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class FindUserApplicationTest {
    @MockBean // 스프링 부트 테스트에서 사용하는 목(mock) 객체를 생성하는 어노테이션입니다
    private UserRepository userRepository;

    @MockBean
    private FindUserByEmailApplication findUserByEmailApplication;

    @BeforeEach // 각 테스트 메소드 실행 전에 호출되는 메소드를 지정
    public void setup() {
        String hashedPassword = BCrypt.hashpw("1234", BCrypt.gensalt());

        // User 객체 생성
        User user1 = User.builder()
                .id(1L)
                .email("robertvsd1@gmail.com")
                .genderType(GenderType.MALE)
                .age(20)
                .img("img")
                .password(hashedPassword)
                .build();

        User user2 = User.builder()
                .id(2L)
                .email("robertvsd2@gmail.com")
                .genderType(GenderType.MALE)
                .age(20)
                .img("img_two")
                .password(hashedPassword)
                .build();

        // userRepository.findById에 대한 모의 동작 정의
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
    }



    @Test
    public void notFoundUserByEmailTest() {
        // given
        String nonExistentEmail = "nonexistentemail@example.com";

        // userRepository.findByEmail에 대한 모의 동작 정의
        // 이메일로 사용자를 찾을 수 없을 때 빈 Optional 반환
        when(findUserByEmailApplication.execute(nonExistentEmail))
                .thenThrow(new NotFoundException("해당 유저가 존재하지 않습니다."));

        // then
        Assertions.assertThrows(NotFoundException.class, () -> {
            // when
            findUserByEmailApplication.execute(nonExistentEmail);
        });
    }

    @Test
    public void successTestFindUserByEmail() {
        // given
        Long userId = 1L;
        String email = "aweroh@gmail.com";
        User user = User.builder()
                .id(userId)
                .email(email)
                .build();

        FindUserByEmailResponseDto expectedResponse = new FindUserByEmailResponseDto();
        expectedResponse.setId(1L);
        expectedResponse.setEmail(email);

        // when
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        FindUserByEmailResponseDto actualResponse = findUserByEmailApplication.execute(email);
        System.out.println("found user :" + actualResponse);

        // then
        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }
}
