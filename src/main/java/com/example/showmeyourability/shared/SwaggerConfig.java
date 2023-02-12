package com.example.showmeyourability.shared;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Show Your Ability API 명세서",
                description = "API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi pubildApi() {
        String[] paths = {"/v1/**"};

        return GroupedOpenApi.builder()
                .group("Show Your Ability API v1")
                .pathsToMatch(paths)
                .build();
    }
}