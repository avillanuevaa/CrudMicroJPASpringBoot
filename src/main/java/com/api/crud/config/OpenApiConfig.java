package com.api.crud.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appversion) {
        return new OpenAPI().components(new Components())
                .info(new Info().title("Título de tu API").version(appversion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().email("amva@gmail.com").url("http://ejemplo.com").name("Amva")));
    }
}