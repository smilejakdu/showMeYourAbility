package com.example.showmeyourability.comments.domain;

import com.example.showmeyourability.shared.Exception.HttpExceptionCustom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class LikesVo {
    private final Double value;

    // 에러 메시지 상수
    public static final String LIKES_CANNOT_BE_EMPTY = "Likes cannot be empty.";
    public static final String MUST_BE_NUMBER = "Likes must be a number.";

    // Likes 객체 생성을 위한 private 생성자
    public static LikesVo of(Double value) {
        validate(value);
        return new LikesVo(value);
    }

    // Content 값의 유효성을 검증하는 메소드
    private static void validate(Double value) {
        if (value == null){
            throw new HttpExceptionCustom(
                    false,
                    LIKES_CANNOT_BE_EMPTY,
                    HttpStatus.BAD_REQUEST
            );
        }

        if (value < 0) {
            throw new HttpExceptionCustom(
                    false,
                    MUST_BE_NUMBER,
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
