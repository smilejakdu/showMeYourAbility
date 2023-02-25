package com.example.showmeyourability.teacher.application;

import com.example.showmeyourability.teacher.domain.Teacher;
import com.example.showmeyourability.teacher.infrastructure.dto.DeleteTeacherDto.DeleteTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTeacherApplication {
    private final TeacherRepository teacherRepository;

    @Transactional
    public DeleteTeacherResponseDto execute(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("해당 선생님을 찾을 수 없습니다."));
        teacherRepository.delete(teacher);
        return DeleteTeacherResponseDto.builder()
                .teacherId(teacherId)
                .build();
    }
}
