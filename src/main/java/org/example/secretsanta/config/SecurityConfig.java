package org.example.secretsanta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disable csrf temporarily
        http.csrf(c -> c.disable());

        // Disable authorization temporarily
        http.authorizeHttpRequests(c ->
                c.anyRequest().permitAll()
        );

        return http.build();
    }
}
