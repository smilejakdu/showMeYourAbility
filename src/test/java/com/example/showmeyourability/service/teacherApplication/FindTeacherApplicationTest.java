package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class FindTeacherApplicationTest {
    private FindTeacherApplication findTeacherApplication;
    @MockBean
    private TeacherRepository teacherRepository;

    @BeforeEach
    void setUp() {
        findTeacherApplication = new FindTeacherApplication(
                teacherRepository
        );
    }

    @Test
    void findOneTeacherDoesNotExist() {
        // givne
        Long teacherId = 3L;
        String teacherName = "teacherName";
        // when
        // then
    }
}
