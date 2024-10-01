package com.api.crud.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Título de tu API")
                        .version("1.0")
                        .description("Descripción de tu API")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tuemail@ejemplo.com")
                                .url("https://tuwebsite.com")));
    }
}