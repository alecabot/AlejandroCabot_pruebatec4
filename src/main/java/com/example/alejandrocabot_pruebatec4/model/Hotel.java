package com.example.alejandrocabot_pruebatec4.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hotelCode;
    private String name;
    private String place;
    private String roomType;
    private Double roomPrice;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Boolean isBooked;

    @OneToOne(mappedBy = "hotel")
    private HotelReservation hotelReservation;
}
