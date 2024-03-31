package com.example.showmeyourability.comments.domain;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentVo {
    private final String value;

    // 에러 메시지 상수
    public static final String CONTENT_CANNOT_BE_EMPTY = "Content cannot be empty.";
    public static final String CONTENT_TOO_LONG = "Content length cannot exceed 500 characters.";

    // Content 객체를 생성하는 정적 팩토리 메소드
    public static ContentVo of(String value) {
        validate(value);
        return new ContentVo(value);
    }

    // Content 값의 유효성을 검증하는 메소드
    private static void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new HttpExceptionCustom(
                    false,
                    CONTENT_CANNOT_BE_EMPTY,
                    HttpStatus.BAD_REQUEST
            );
        }

        if (value.isEmpty()) {
            throw new HttpExceptionCustom(
                    false,
                    CONTENT_CANNOT_BE_EMPTY,
                    HttpStatus.BAD_REQUEST
            );
        }

        if (value.length() > 500) {
            throw new HttpExceptionCustom(
                    false,
                    CONTENT_TOO_LONG,
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
