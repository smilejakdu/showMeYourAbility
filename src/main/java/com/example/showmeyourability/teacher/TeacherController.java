package com.example.showmeyourability.teacher;

import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final FindTeacherApplication findTeacherApplication;

    @GetMapping()
    public FindTeacherResponseDto findAllTeacher(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return findTeacherApplication.findAllTeacher(page, size);
    }

    @GetMapping("{teacherId}")
    public CoreSuccessResponse findOneTeacherById(
            @PathVariable Long teacherId
    ) {
        return findTeacherApplication.findOneTeacherById(teacherId);
    }
}
