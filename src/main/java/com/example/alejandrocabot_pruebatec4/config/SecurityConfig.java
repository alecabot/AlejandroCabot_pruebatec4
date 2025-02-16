package com.example.alejandrocabot_pruebatec4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.PUT, "/agency/flights/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/agency/flights/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/agency/flights/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/agency/hotels/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/agency/hotels/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/agency/hotels/**").authenticated()
                        .anyRequest().permitAll()

                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();

    }
}
