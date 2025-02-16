package com.example.alejandrocabot_pruebatec4.dto;

import com.example.alejandrocabot_pruebatec4.model.Flight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightReservationDTO {

    @NotNull(message = "The date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "The origin cannot be blank")
    @Size(max = 50, message = "The origin cannot exceed 50 characters")
    private String origin;

    @NotBlank(message = "The flight code cannot be blank")
    @Size(max = 10, message = "The flight code cannot exceed 10 characters")
    private String flightCode;

    @NotBlank(message = "The destination cannot be blank")
    @Size(max = 50, message = "The destination cannot exceed 50 characters")
    private String destination;

    @NotNull(message = "The number of people cannot be null")
    @Positive(message = "The number of people must be positive")
    private Integer peopleQ;

    @NotBlank(message = "The seat type cannot be blank")
    @Size(max = 20, message = "The seat type cannot exceed 20 characters")
    private String seatType;

    @NotNull(message = "The passengers list cannot be null")
    private List<PassengerDTO> passengers;

    @NotNull(message = "The flight id cannot be null")
    private Long flightId;

//    private Flight flight;

    // Getters and setters
}




