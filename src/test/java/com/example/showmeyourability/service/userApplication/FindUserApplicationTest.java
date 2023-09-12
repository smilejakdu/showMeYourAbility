package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.users.application.FindUserByEmailApplication;
import com.example.showmeyourability.users.application.SignupUserApplication;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.dto.FindUserDto.FindUserByEmailResponseDto;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    public void findUserByEmail() {
        // given
        // 1. 특정 유저의 email를 나타내는 변수를 생성한다.
        Long userId = 1L;
        String email = "ash@gmail.com";
        String phone = "112";

        // 2. 테스트용으로 사용할 유저 객체를 생성한다.
        // 해당 유저의 속성(이메일, 전화번호)를 설정하고 빌더 패턴을 사용하여 객체를 생성한다.
        User user = User.builder()
                .email(email)
                .build();

        // 3. 유저 객체에
        // 테스트 목적으로 추가된 작업
        // 실제로는 자동 증가되는 값
        user.setId(userId);// Added for test //AutoEncrementation

        // 4. Mockito를 사용하여 userRepository의 findByEmail 메소드를 호출하면 user 객체를 반환하도록 설정한다.
        Mockito.when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user));

        // when
        // 5. FindUserByEmailApplication 객체를 생성한다.
        FindUserByEmailResponseDto findUser = findUserByEmailApplication.execute(user);
        System.out.println("findUser:"+findUser);

        Assertions.assertNotNull(findUser);
    }
}
