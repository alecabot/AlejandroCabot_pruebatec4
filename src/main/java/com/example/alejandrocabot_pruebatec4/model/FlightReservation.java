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
@Table(name = "reservation_flights")
public class FlightReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String origin;
    private String flightCode;
    private String destination;
    private Integer peopleQ;
    private String seatType;

    @OneToMany(mappedBy = "flightReservation", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Passenger> passengers;

    @ManyToOne
    private Flight flight;

}




