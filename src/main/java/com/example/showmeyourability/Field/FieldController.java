package com.example.showmeyourability.Field;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "field", description = "filed API")
@RequestMapping("/api/field")
@RequiredArgsConstructor
public class FieldController {
}
