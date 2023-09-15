package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.users.application.FindUserByEmailApplication;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.FindUserDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FindUserApplicationTest {
    @MockBean // 스프링 부트 테스트에서 사용하는 목(mock) 객체를 생성하는 어노테이션입니다
    private UserRepository userRepository;

    private FindUserByEmailApplication findUserByEmailApplication;

    @BeforeEach // 각 테스트 메소드 실행 전에 호출되는 메소드를 지정
    public void setup() {
        findUserByEmailApplication = new FindUserByEmailApplication(
                userRepository
        );
    }

    @Test
    public void testFindUserByEmail() {
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
        FindUserByEmailResponseDto actualResponse = findUserByEmailApplication.execute(user);
        System.out.println("found user :" + actualResponse);

        // then
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
    }

    @Test
    public void testFindUserByEmailNotExists() {
        // given
        String email = "doesnotemail@gmail.com";

        User user = User.builder()
                .email(email)
                .build();

        // when
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // then
        // 이 경우, execute 메서드가 존재하지 않는 이메일을 찾을 때 예외를 던져야 합니다.
        assertThrows(NoSuchElementException.class, () -> {
            findUserByEmailApplication.execute(user);
        });
    }
}