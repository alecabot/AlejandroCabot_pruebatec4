package com.example.alejandrocabot_pruebatec4.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDTO {

    @NotBlank(message = "The hotel code cannot be blank")
    @Size(max = 10, message = "The hotel code cannot exceed 10 characters")
    private String hotelCode;

    @NotBlank(message = "The name cannot be blank")
    @Size(max = 50, message = "The name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "The place cannot be blank")
    @Size(max = 50, message = "The place cannot exceed 50 characters")
    private String place;

    @NotBlank(message = "The room type cannot be blank")
    @Size(max = 20, message = "The room type cannot exceed 20 characters")
    @Pattern(regexp = "Single|Double|Triple|Multiple", message = "The room type must be one of the following: Single, Double, Triple, Multiple")
    private String roomType;

    @NotNull(message = "The room price cannot be null")
    @Positive(message = "The room price must be positive")
    private Double roomPrice;

    @NotNull(message = "The availability date from cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @NotNull(message = "The availability date to cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @NotNull(message = "The booking status cannot be null")
    private Boolean isBooked;

    @NotNull(message = "The hotel reservation cannot be null")
    private Long hotelId;

    // Getters and setters
}
