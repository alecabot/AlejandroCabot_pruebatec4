package com.example.alejandrocabot_pruebatec4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerDTO {
    @NotBlank(message = "The name cannot be blank")
    @Size(max = 50, message = "The name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "The last name cannot be blank")
    @Size(max = 50, message = "The last name cannot exceed 50 characters")
    private String lastName;

    @Positive(message = "The age must be a positive number")
    private int age;
}

