package com.example.alejandrocabot_pruebatec4.repository;

import com.example.alejandrocabot_pruebatec4.model.Flight;
import com.example.alejandrocabot_pruebatec4.model.FlightReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

}
