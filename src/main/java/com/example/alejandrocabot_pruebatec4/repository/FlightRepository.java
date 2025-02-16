package com.example.alejandrocabot_pruebatec4.repository;

import com.example.alejandrocabot_pruebatec4.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureDateLessThanEqualAndReturnDateGreaterThanEqualAndOriginContainsIgnoreCaseAndDestinationIgnoreCase(LocalDate dateFrom, LocalDate dateTo, String origin, String destination);
}
