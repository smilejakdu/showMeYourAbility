package com.example.showmeyourability.shared;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        servers = {
                @Server(
                        description = "Local server",
                        url = "http://localhost:12425"
                )
        }
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi storeOpenApi() {
        return GroupedOpenApi.builder()
                .group("stores")
                .packagesToScan("com.example.showmeyourability.store.controller")
                .build();
    }

    @Bean
    public GroupedOpenApi userOpenApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .packagesToScan("com.example.showmeyourability.user.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("SpringBoot Rest API Documentation")
                .description("Spring Boot를 이용한 Rest API 문서입니다.")
                .version("0.1")
                .termsOfService("http://localhost:8080"));
    }

}