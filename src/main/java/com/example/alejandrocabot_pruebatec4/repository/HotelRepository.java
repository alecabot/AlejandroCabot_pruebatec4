package com.example.alejandrocabot_pruebatec4.repository;

import com.example.alejandrocabot_pruebatec4.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByDateFromLessThanEqualAndDateToGreaterThanEqualAndIsBookedIsFalseAndPlaceContainsIgnoreCase(LocalDate dateFrom, LocalDate dateTo, String place);
    List<Hotel> findByDateFromLessThanEqualAndDateToGreaterThanEqualAndIsBookedIsFalseAndPlace(LocalDate disponibleDesde, LocalDate disponibleHasta, String ubicacion);

}
