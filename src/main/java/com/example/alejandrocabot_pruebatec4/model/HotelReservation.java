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
@Table(name = "reservation_hotels")
public class HotelReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer nights;
    private String place;
    private String hotelCode;
    private Integer peopleQ;
    private String roomType;

    @OneToMany(mappedBy = "hotelReservation", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Host> hosts;

    @OneToOne
    private Hotel hotel;

}
