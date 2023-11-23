package com.example.showmeyourability.service.userApplication;

import com.example.showmeyourability.users.application.UpdateMyInfoApplication;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UpdateMyInfoApplicationTest {
    @MockBean
    private UserRepository userRepository;

    private UpdateMyInfoApplication updateMyInfoApplication;

    @BeforeEach
    public void setup() {
        updateMyInfoApplication = new UpdateMyInfoApplication(
                userRepository
        );
    }

    @Test
    public void testUpdateMyInfo() {
        // given
        Long userId = 1L;
        String email = "aweroh@gmail.com";
        User user = User.builder()
                .id(userId)
                .email(email)
                .build();
        System.out.println("user : " + user);
        // when

        // then
    }
}
