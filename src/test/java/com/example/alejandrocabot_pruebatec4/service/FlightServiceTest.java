package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.FlightDTO;
import com.example.alejandrocabot_pruebatec4.model.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FlightServiceTest {
    private final FlightService flightService = new FlightService();

    @Test
    void testConvertToDtoExito() {
        // Crea una entidad Flight
        Flight flight = Flight.builder()
                .flightNumber("FL123")
                .origin("City A")
                .destination("City B")
                .seatType("Economy")
                .flightPrice(200.00)
                .departureDate(LocalDate.of(2023, 12, 1))
                .returnDate(LocalDate.of(2023, 12, 10))
                .build();

        // Convierte la entidad a DTO
        FlightDTO flightDTO = flightService.convertToDto(flight);

        // Verifica que los campos sean iguales
        assertEquals(flight.getFlightNumber(), flightDTO.getFlightNumber());
        assertEquals(flight.getOrigin(), flightDTO.getOrigin());
        assertEquals(flight.getDestination(), flightDTO.getDestination());
        assertEquals(flight.getSeatType(), flightDTO.getSeatType());
        assertEquals(flight.getFlightPrice(), flightDTO.getFlightPrice());
        assertEquals(flight.getDepartureDate(), flightDTO.getDepartureDate());
        assertEquals(flight.getReturnDate(), flightDTO.getReturnDate());
    }

    @Test
    void testConvertToEntityExisto() {
        // Crea un DTO FlightDTO
        FlightDTO flightDTO = FlightDTO.builder()
                .flightNumber("FL123")
                .origin("City A")
                .destination("City B")
                .seatType("Economy")
                .flightPrice(200.00)
                .departureDate(LocalDate.of(2023, 12, 1))
                .returnDate(LocalDate.of(2023, 12, 10))
                .build();

        // Convierte el DTO a entidad
        Flight flight = flightService.convertToEntity(flightDTO);

        // Verifica que los campos sean iguales
        assertEquals(flightDTO.getFlightNumber(), flight.getFlightNumber());
        assertEquals(flightDTO.getOrigin(), flight.getOrigin());
        assertEquals(flightDTO.getDestination(), flight.getDestination());
        assertEquals(flightDTO.getSeatType(), flight.getSeatType());
        assertEquals(flightDTO.getFlightPrice(), flight.getFlightPrice());
        assertEquals(flightDTO.getDepartureDate(), flight.getDepartureDate());
        assertEquals(flightDTO.getReturnDate(), flight.getReturnDate());
    }
}
