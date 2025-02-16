package com.example.alejandrocabot_pruebatec4.dto;

import com.example.alejandrocabot_pruebatec4.model.Hotel;
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
public class HotelReservationDTO {

    @NotNull(message = "The start date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @NotNull(message = "The end date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @NotNull(message = "The number of nights cannot be null")
    @Positive(message = "The number of nights must be positive")
    private Integer nights;

    @NotBlank(message = "The place cannot be blank")
    @Size(max = 50, message = "The place cannot exceed 50 characters")
    private String place;

    @NotBlank(message = "The hotel code cannot be blank")
    @Size(max = 10, message = "The hotel code cannot exceed 10 characters")
    private String hotelCode;

    @NotNull(message = "The number of people cannot be null")
    @Positive(message = "The number of people must be positive")
    private Integer peopleQ;

    @NotBlank(message = "The room type cannot be blank")
    @Size(max = 20, message = "The room type cannot exceed 20 characters")
    private String roomType;

    @NotNull(message = "The hosts list cannot be null")
    private List<HostDTO> hosts;

    @NotNull(message = "The hotel id cannot be null")
    private Long hotelId;
    // Getters and setters
}
