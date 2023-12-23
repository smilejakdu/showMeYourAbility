package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.teacher.application.FindRecentTeacherApplication;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindRecentTeacherResponseDto;
import com.example.showmeyourability.users.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindRecentTeacherApplicationTest {

    @Mock
    private JPAQueryFactory queryFactory;

    @InjectMocks
    private FindRecentTeacherApplication findRecentTeacherApplication;

    private List<Teacher> teachers;

    @BeforeEach
    public void setup() {
        String password = "password1234";
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = User.builder()
                .id(1L)
                .name("robert")
                .email("robert@gmail.com")
                .password(hashPassword)
                .build();

        teachers = Arrays.asList(
                Teacher.builder()
                        .id(1L)
                        .career("1")
                        .skill("english")
                        .user(user)
                        .build(),
                Teacher.builder()
                        .id(2L)
                        .career("13")
                        .skill("math")
                        .user(user)
                        .build(),
                Teacher.builder()
                        .id(3L)
                        .career("13")
                        .skill("math")
                        .user(user)
                        .build(),
                Teacher.builder()
                        .id(4L)
                        .career("13")
                        .skill("math")
                        .user(user)
                        .build(),
                Teacher.builder()
                        .id(5L)
                        .career("13")
                        .skill("math")
                        .user(user)
                        .build());
    }
}
