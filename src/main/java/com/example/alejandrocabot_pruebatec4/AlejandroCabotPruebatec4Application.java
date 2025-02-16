package com.example.alejandrocabot_pruebatec4;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlejandroCabotPruebatec4Application {

    public static void main(String[] args) {
        SpringApplication.run(AlejandroCabotPruebatec4Application.class, args);
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API Booking")
                .version("0.0.1")
                .description("Hotel and Flight Reservations"));
    }


}
