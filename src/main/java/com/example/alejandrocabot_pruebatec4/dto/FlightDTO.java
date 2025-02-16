package com.example.alejandrocabot_pruebatec4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDTO {

    @NotBlank(message = "The flight number cannot be blank")
    @Size(max = 10, message = "The flight number cannot exceed 10 characters")
    private String flightNumber;

    @NotBlank(message = "The origin cannot be blank")
    @Size(max = 50, message = "The origin cannot exceed 50 characters")
    private String origin;

    @NotBlank(message = "The destination cannot be blank")
    @Size(max = 50, message = "The destination cannot exceed 50 characters")
    private String destination;

    @NotBlank(message = "The seat type cannot be blank")
    @Size(max = 20, message = "The seat type cannot exceed 20 characters")
    private String seatType;

    @NotNull(message = "The flight price cannot be null")
    @Positive(message = "The flight price must be positive")
    private Double flightPrice;

    @NotNull(message = "The departure date cannot be null")
    private LocalDate departureDate;

    @NotNull(message = "The return date cannot be null")
    private LocalDate returnDate;

    // Getters and setters
}

