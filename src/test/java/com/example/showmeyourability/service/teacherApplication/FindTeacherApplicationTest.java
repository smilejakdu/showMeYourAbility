package com.example.showmeyourability.service.teacherApplication;

import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FindTeacherApplicationTest {

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    void notFoundTeacherByIdTest() {
        // given
        Long teacherId = 1L;

        // when
        teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("해당 선생님을 찾을 수 없습니다."));
    }
}
