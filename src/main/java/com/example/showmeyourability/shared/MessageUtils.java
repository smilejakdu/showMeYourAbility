package com.example.showmeyourability.shared;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MessageUtils {
    private static MessageSource messageSource;
    private static String language;

    public MessageUtils(MessageSource messageSource,
                        @Value("${spring.web.locale}") String language) {
        MessageUtils.messageSource = messageSource;
        MessageUtils.language = language;
    }

    public static String getMessageByKey(String key) {
        return messageSource.getMessage(key, null, new Locale(language));
    }

    public static Map<String, String> pipeMessage(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return errors;
    }
}

