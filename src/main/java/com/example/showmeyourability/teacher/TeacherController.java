package com.example.showmeyourability.teacher;

import com.example.showmeyourability.teacher.application.FindRecentTeacherApplication;
import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindRecentTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final FindTeacherApplication findTeacherApplication;

    private final FindRecentTeacherApplication findRecentTeacherApplication;

    @GetMapping()
    public FindTeacherResponseDto findAllTeacher(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return findTeacherApplication.findAllTeacher(page, size);
    }

    @GetMapping("{teacherId}")
    public FindTeacherByIdResponseDto findOneTeacherById(
            @PathVariable Long teacherId
    ) {
        return findTeacherApplication.findOneTeacherById(teacherId);
    }

    @GetMapping("/recentTeacher")
    public FindRecentTeacherResponseDto findRecentTeacher() {
        return findRecentTeacherApplication.execute();
    }
}
