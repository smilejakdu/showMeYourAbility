package com.example.showmeyourability.shared;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springdoc.core.GroupedOpenApi;
=======
import org.springdoc.core.models.GroupedOpenApi;
>>>>>>> dda462a (swagger user api docs)
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
<<<<<<< HEAD
        info = @Info(title = "SHOW ME YOUR ABILITY App",
                description = "SHOW ME YOUR ABILITY api명세",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
=======
        info = @Info(title = "SHOW ME YOUR ABILITY",
                description = "SHOW ME YOUR ABILITY",
                version = "v1"))
@Configuration
@RequiredArgsConstructor
>>>>>>> dda462a (swagger user api docs)
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("SHOW ME YOUR ABILITY API")
                .pathsToMatch(paths)
                .build();
    }
}
