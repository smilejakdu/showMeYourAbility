package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.comments.domain.Comments;
import com.example.showmeyourability.comments.domain.QComments;
import com.example.showmeyourability.teacher.application.FindTeacherByNameApplication;
import com.example.showmeyourability.teacher.domain.QTeacher;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByNameResponseDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.QUser;
import com.example.showmeyourability.users.domain.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindTeacherByNameApplicationTest {

    @Mock
    private JPAQueryFactory queryFactory;

    @Mock
    private JPAQuery<Teacher> jpaTeacherQuery;

    @Mock
    private JPAQuery<Comments> jpaCommentsQuery;

    @InjectMocks
    private FindTeacherByNameApplication findTeacherByNameApplication;

    private Teacher teacher;
    private User user;
    @BeforeEach
    public void setUp() {

        String hashedPassword = BCrypt.hashpw("1234", BCrypt.gensalt());

        // User 객체 생성
        user = User.builder()
                .id(1L)
                .email("robertvsd1@gmail.com")
                .name("robert")
                .genderType(GenderType.MALE)
                .age(20)
                .img("img")
                .password(hashedPassword)
                .build();

        teacher = Teacher.builder()
                .id(1L)
                .career("2")
                .skill("english")
                .user(user)
                .build();
    }

    @Test
    public void testFindTeacherByName() {
        String name = user.getName();
        // when
        when(queryFactory.selectFrom(QTeacher.teacher))
                .thenReturn(jpaTeacherQuery);
        when(jpaTeacherQuery.join(QTeacher.teacher.user, QUser.user))
                .thenReturn(jpaTeacherQuery);
        when(jpaTeacherQuery.leftJoin(QTeacher.teacher.comments, QComments.comments))
                .thenReturn(jpaTeacherQuery);
        when(jpaTeacherQuery.where(QUser.user.name.eq(name)))
                .thenReturn(jpaTeacherQuery);
        when(jpaTeacherQuery.fetchOne())
                .thenReturn(teacher);
        when(queryFactory.selectFrom(QComments.comments))
                .thenReturn(jpaCommentsQuery);
        when(jpaCommentsQuery.where(QComments.comments.teacher.eq(teacher)))
                .thenReturn(jpaCommentsQuery);
        when(jpaCommentsQuery.fetch())
                .thenReturn(teacher.getComments());

        FindTeacherByNameResponseDto findTeacherByNameResponseDto = findTeacherByNameApplication.findTeacherByName(name);
        // then
        System.out.println("teacher = " + teacher);
        assertEquals(teacher.getUser().getName(), findTeacherByNameResponseDto.getTeacher().getUser().getName());
    }
}
