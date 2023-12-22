package com.example.showmeyourability.teacher.application;


import com.example.showmeyourability.teacher.infrastructure.dto.CreateTeacherDto.CreateTeacherRequestDto;
import com.example.showmeyourability.teacher.infrastructure.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CreateTeacherApplication {

    private final TeacherRepository teacherRepository;
    @Transactional
    public void execute(
            Long userId,
            @RequestBody CreateTeacherRequestDto createTeacherRequestDto
    ) {
    }
}

