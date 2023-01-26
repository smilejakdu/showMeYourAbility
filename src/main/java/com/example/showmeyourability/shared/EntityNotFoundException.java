package com.example.showmeyourability.shared;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends HttpException {
    public EntityNotFoundException() {
        super("요청한 자원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
    }

    public EntityNotFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public static EntityNotFoundException of(String messageKey) {
        String message = MessageUtils.getMessageByKey(messageKey);

        return new EntityNotFoundException(message);
    }
}

