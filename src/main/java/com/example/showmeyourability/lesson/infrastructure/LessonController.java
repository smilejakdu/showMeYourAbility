package com.example.showmeyourability.lesson.infrastructure;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "lesson", description = "레슨 API")
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonController {
}
