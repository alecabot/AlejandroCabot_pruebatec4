package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.FlightDTO;
import com.example.alejandrocabot_pruebatec4.dto.HotelDTO;
import com.example.alejandrocabot_pruebatec4.exception.*;
import com.example.alejandrocabot_pruebatec4.model.Flight;
import com.example.alejandrocabot_pruebatec4.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class FlightService implements IFlightService{
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public FlightDTO findById(Long id) {
        // Encuentra un vuelo por su ID y lo convierte a DTO
        return flightRepository.findById(id).map(this::convertToDto).orElseThrow(() ->
                new NotFoundException("Flight with ID " + id + " not found."));
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightRequest) {
        // Crea un nuevo vuelo a partir del DTO proporcionado y lo guarda en el repositorio
        try {
            flightRepository.save(convertToEntity(flightRequest));
        } catch (Exception e) {
            throw new SaveException("Error saving the hotel");
        }

        // Devuelve el DTO del vuelo creado
        return flightRequest;
    }

    @Override
    public FlightDTO updateFlight(FlightDTO flightRequest, Long id) {
        // Verifica si el vuelo existe, si no lanza una excepción
        flightRepository.findById(id).orElseThrow(() -> new NotFoundException("Flight with ID " + id + " not found."));

        // Convierte el DTO del vuelo a una entidad y establece el ID
        Flight flight = convertToEntity(flightRequest);
        flight.setId(id);

        // Guarda el vuelo actualizado en el repositorio
        try {
            flightRepository.save(flight);
        } catch (Exception e) {
            throw new SaveException("Error saving the hotel");
        }

        // Devuelve el DTO del vuelo actualizado
        return convertToDto(flight);
    }

    @Override
    public FlightDTO deleteFlight(Long id) {
        // Encuentra un vuelo por su ID y lo elimina del repositorio
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight with ID " + id + " not found."));

        // Verifica si el vuelo tiene reservas asociadas, si es así lanza una excepción
        if (flight.getFlightReservations() != null) {
            throw new NotDeletedException("The flight has associated reservations and cannot be deleted.");
        }
        flightRepository.delete(flight);

        // Devuelve el DTO del vuelo eliminado
        return convertToDto(flight);
    }

    @Override
    public List<FlightDTO> getFlights() {
        // Obtiene todos los vuelos y los convierte a DTO
        List<FlightDTO> flights = flightRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();

        // Lanza una excepción si no se encuentran vuelos
        if (flights.isEmpty()) {
            throw new EmptyException("No results found in the flight.");
        }

        // Devuelve la lista de vuelos
        return flights;
    }

    @Override
    public List<FlightDTO> getFlightsAvailability(String dateFromStr, String dateToStr, String origin, String destination) {
        // Formatea las fechas de entrada y salida
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dateFrom;
        LocalDate dateTo;

        // Intenta parsear las fechas, si falla lanza una excepción
        try {
            dateFrom = LocalDate.parse(dateFromStr, formatter);
            dateTo = LocalDate.parse(dateToStr, formatter);
        } catch (DateTimeParseException e) {
            throw new DateException("Dates must be in the format yyyy-MM-dd.");
        }

        // Verifica que la fecha de inicio no sea posterior a la fecha de fin
        if (dateFrom.isAfter(dateTo)) {
            throw new DateException("The 'dateFrom' cannot be after 'dateTo'.");
        }

        // Busca vuelos disponibles según los criterios proporcionados y los convierte a DTO
        List<FlightDTO> flights = flightRepository
                .findByDepartureDateLessThanEqualAndReturnDateGreaterThanEqualAndOriginContainsIgnoreCaseAndDestinationIgnoreCase(
                        dateTo, dateFrom, origin, destination
                )
                .stream()
                .map(this::convertToDto)
                .toList();

        // Lanza una excepción si no se encuentran vuelos
        if (flights.isEmpty()) {
            throw new EmptyException("No results found in the flight.");
        }

        // Devuelve la lista de vuelos disponibles
        return flights;
    }



    public FlightDTO convertToDto(Flight flight) {
        return FlightDTO.builder()
                .flightNumber(flight.getFlightNumber())
                .origin(flight.getOrigin())
                .destination(flight.getDestination())
                .seatType(flight.getSeatType())
                .flightPrice(flight.getFlightPrice())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                // Asigna otros campos según sea necesario
                .build();
    }

    public Flight convertToEntity(FlightDTO flightDto) {
        return Flight.builder()
                .flightNumber(flightDto.getFlightNumber())
                .origin(flightDto.getOrigin())
                .destination(flightDto.getDestination())
                .seatType(flightDto.getSeatType())
                .flightPrice(flightDto.getFlightPrice())
                .departureDate(flightDto.getDepartureDate())
                .returnDate(flightDto.getReturnDate())
                // Asigna otros campos según sea necesario
                .build();
    }


}
