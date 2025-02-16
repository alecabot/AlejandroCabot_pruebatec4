package com.example.alejandrocabot_pruebatec4.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String seatType;
    private Double flightPrice;
    private LocalDate departureDate;
    private LocalDate returnDate;

    @OneToMany(mappedBy = "flight")
    private List<FlightReservation> flightReservations;

}

