package com.api.crud.config;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permitir acceso a Swagger
                        .anyRequest().permitAll() // Permitir todas las demás rutas sin autenticación
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Deshabilitar la autenticación básica
                .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF de forma correcta en versiones más recientes

        return http.build();
    }
}