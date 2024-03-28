package com.example.showmeyourability.comments.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public class Content {
    private final String value;

    // 에러 메시지 상수
    public static final String CONTENT_CANNOT_BE_EMPTY = "Content cannot be empty.";
    public static final String CONTENT_TOO_LONG = "Content length cannot exceed 500 characters.";

    // Content 객체를 생성하는 정적 팩토리 메소드
    public static Content of(String value) {
        validate(value);
        return new Content(value);
    }

    // Content 값의 유효성을 검증하는 메소드
    private static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new IllegalArgumentException(CONTENT_CANNOT_BE_EMPTY);
        }
        if (value.length() > 500) {
            throw new IllegalArgumentException(CONTENT_TOO_LONG);
        }
    }
}
