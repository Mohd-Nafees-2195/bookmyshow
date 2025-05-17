package com.application.bookMyShow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register","/update","/all","/book").permitAll()
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable()) // ✅ modern style
                .httpBasic(Customizer.withDefaults()) // enable HTTP Basic Auth
                .build();
    }
}
