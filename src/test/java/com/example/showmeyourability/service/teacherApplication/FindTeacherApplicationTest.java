package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import com.example.showmeyourability.users.domain.GenderType;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.webjars.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
public class FindTeacherApplicationTest {

    @MockBean
    TeacherRepository teacherRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    FindTeacherApplication findTeacherApplication;

    @BeforeEach
    void setUp() {
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
                .img("img")
                .password(hashedPassword)
                .build();

        // userRepository.findById에 대한 모의 동작 정의
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        // Teacher 객체 생성 및 저장
        Teacher teacher = Teacher.builder()
                .id(1L)
                .user(user1)
                .career("경력")
                .skill("스킬")
                .comments(null)
                .orders(null)
                .build();

         teacherRepository.save(teacher);
    }

    @Test
    void notFoundTeacherByIdTest() {
        // given
        Long teacherId = 3L;

        when(findTeacherApplication.findOneTeacherById(teacherId))
                .thenThrow(new NotFoundException("해당 선생님을 찾을 수 없습니다."));

        // then
        assertThrows(NotFoundException.class, () -> {
            findTeacherApplication.findOneTeacherById(teacherId);
        });        // NotFoundException: 해당 선생님을 찾을 수 없습니다.
    }

    @Test
    void foundTeacherByIdTest() {
        Long teacherId = 1L;

        // 필요한 User 및 Teacher 객체 생성
        User mockUser = new User();
        mockUser.setId(teacherId);
        mockUser.setEmail("robertvsd1@gmail.com");
        // mockUser의 다른 필드도 필요에 따라 설정

        Teacher mockTeacher = new Teacher();
        mockTeacher.setId(teacherId);
        mockTeacher.setUser(mockUser);
        // mockTeacher의 다른 필드도 필요에 따라 설정

        // FindTeacherByIdResponseDto 객체 생성 및 Teacher 객체 설정
        FindTeacherByIdResponseDto mockResponse = new FindTeacherByIdResponseDto();
        mockResponse.setTeacher(mockTeacher);

        // 모의 동작 설정
        when(findTeacherApplication.findOneTeacherById(teacherId)).thenReturn(mockResponse);

        // 테스트 실행
        FindTeacherByIdResponseDto actualResponse = findTeacherApplication.findOneTeacherById(teacherId);

        // 검증
        assertEquals(teacherId, actualResponse.getTeacher().getId());
        assertEquals("robertvsd1@gmail.com", actualResponse.getTeacher().getUser().getEmail());
    }
}
