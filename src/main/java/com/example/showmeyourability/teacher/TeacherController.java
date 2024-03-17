package com.example.showmeyourability.teacher;

import com.example.showmeyourability.shared.CoreSuccessResponse;
import com.example.showmeyourability.teacher.application.FindOneTeacherByIdApplication;
import com.example.showmeyourability.teacher.application.FindRecentTeacherApplication;
import com.example.showmeyourability.teacher.application.FindTeacherApplication;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindRecentTeacherResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherByIdResponseDto;
import com.example.showmeyourability.teacher.infrastructure.dto.FindTeacherDto.FindTeacherResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.showmeyourability.shared.CoreSuccessResponse.coreSuccessResponse;

@RestController
@Tag(name = "teacher", description = "선생님 API")
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final FindTeacherApplication findTeacherApplication;
    private final FindRecentTeacherApplication findRecentTeacherApplication;
    private final FindOneTeacherByIdApplication findOneTeacherByIdApplication;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "선생님 데이터 불러오기",
            description = "선생님 데이터 불러오기"
    )
    public CoreSuccessResponse findAllTeacher(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        // 페이지 번호가 1보다 작은 경우 1로 재설정합니다.
        int validatedPage = page < 1 ? 1 : page;

        FindTeacherResponseDto findTeacherResponseDto = findTeacherApplication.execute(validatedPage - 1, size);
        return coreSuccessResponse(true, findTeacherResponseDto, "교사 정보 조회 성공", HttpStatus.OK.value());
    }

    @GetMapping("{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "선생님 데이터 하나 불러오기",
            description = "선생님 데이터 하나 불러오기"
    )
    public CoreSuccessResponse findOneTeacherById(
            @PathVariable Long teacherId
    ) {
        FindTeacherByIdResponseDto findTeacherByIdResponseDto = findOneTeacherByIdApplication.execute(teacherId);
        return coreSuccessResponse(true, findTeacherByIdResponseDto, "교사 정보 조회 성공", HttpStatus.OK.value());
    }

    @GetMapping("/recentTeacher")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "최신 선생님 데이터 4개 불러오기",
            description = "최신 선생님 데이터 4개 불러오기"
    )
    public CoreSuccessResponse findRecentTeacher() {
        FindRecentTeacherResponseDto findRecentTeacherResponseDto = findRecentTeacherApplication.execute();
        return coreSuccessResponse(true, findRecentTeacherResponseDto, "최신 선생님 데이터 조회 성공", HttpStatus.OK.value());
    }
}
