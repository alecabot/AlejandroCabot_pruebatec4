package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.FlightReservationDTO;
import com.example.alejandrocabot_pruebatec4.exception.EmptyException;
import com.example.alejandrocabot_pruebatec4.exception.NotFoundException;
import com.example.alejandrocabot_pruebatec4.exception.SaveException;
import com.example.alejandrocabot_pruebatec4.model.Flight;
import com.example.alejandrocabot_pruebatec4.model.FlightReservation;
import com.example.alejandrocabot_pruebatec4.repository.FlightReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightReservationService implements IFlightReservationService{
    @Autowired
    private FlightReservationRepository flightReservationRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private FlightService flightService;


    @Override
    public FlightReservationDTO findById(Long id) {
        // Encuentra una reserva de vuelo por su ID y la convierte a DTO
        return flightReservationRepository.findById(id).map(this::convertToDto).orElseThrow(() ->
                new NotFoundException("FlightReservation with ID " + id + " not found."));
    }

    @Override
    public Double createFlightReservation(FlightReservationDTO flightReservationRequest) {
        // Convierte el DTO de la solicitud de reserva de vuelo a una entidad
        FlightReservation flightReservation = convertToEntity(flightReservationRequest);

        // Guarda la reserva de vuelo y devuelve el monto total
        return saveFlightReservation(flightReservationRequest, flightReservation);
    }

    @Override
    public Double updateFlightReservation(FlightReservationDTO flightReservationRequest, Long id) {
        // Verifica si la reserva de vuelo existe, si no lanza una excepción
        flightReservationRepository.findById(id).orElseThrow(() -> new NotFoundException("FlightReservation with ID " + id + " not found."));

        // Convierte el DTO de la solicitud de reserva de vuelo a una entidad
        FlightReservation flightReservation = convertToEntity(flightReservationRequest);

        // Establece el ID de la reserva de vuelo
        flightReservation.setId(id);

        // Guarda la reserva de vuelo actualizada y devuelve el monto total
        return saveFlightReservation(flightReservationRequest, flightReservation);
    }

    private Double saveFlightReservation(FlightReservationDTO flightReservationRequest, FlightReservation flightReservation) {
        // Convierte el DTO de vuelo a una entidad y establece el vuelo en la reserva de vuelo
        Flight flight = flightService.convertToEntity(flightService.findById(flightReservationRequest.getFlightId()));
        flightReservation.setFlight(flight);
        flightReservation.getFlight().setId(flightReservationRequest.getFlightId());

        // Guarda la reserva de vuelo en el repositorio
        try {
            FlightReservation flightReservationSave = flightReservationRepository.save(flightReservation);
            // Guarda los pasajeros asociados a la reserva de vuelo
            if (flightReservation.getPassengers() != null) {
                flightReservation.getPassengers().forEach(passenger -> {
                    passenger.setFlightReservation(flightReservation);
                    passenger.getFlightReservation().setId(flightReservationSave.getId());
                    passengerService.createPassenge(passenger);
                });
            }
        } catch (Exception e) {
            throw new SaveException("Error saving the flight");
        }



        // Calcula y devuelve el monto total de la reserva de vuelo
        return flight.getFlightPrice() * flightReservation.getPeopleQ();
    }

    @Override
    public FlightReservationDTO deleteFlightReservation(Long id) {
        // Encuentra una reserva de vuelo por su ID y la elimina del repositorio
        FlightReservation flightReservation = flightReservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("FlightReservation with ID " + id + " not found."));
        flightReservationRepository.delete(flightReservation);
        return convertToDto(flightReservation);
    }

    @Override
    public List<FlightReservationDTO> getFlightReservations() {
        // Obtiene todas las reservas de vuelo y las convierte a DTO
        List<FlightReservationDTO> flightreservations = flightReservationRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();

        // Lanza una excepción si no se encuentran reservas de vuelo
        if (flightreservations.isEmpty()) {
            throw new EmptyException("No results found in the flight reservations.");
        }

        // Devuelve la lista de reservas de vuelo
        return flightreservations;
    }




    public FlightReservationDTO convertToDto(FlightReservation flightReservation) {
        return FlightReservationDTO.builder()
                .date(flightReservation.getDate())
                .origin(flightReservation.getOrigin())
                .flightCode(flightReservation.getFlightCode())
                .destination(flightReservation.getDestination())
                .peopleQ(flightReservation.getPeopleQ())
                .seatType(flightReservation.getSeatType())
                .flightId(flightReservation.getFlight().getId())
                .passengers(flightReservation.getPassengers().stream()
                        .map(passengerService::convertToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public FlightReservation convertToEntity(FlightReservationDTO flightReservationDto) {
        return FlightReservation.builder()
                .date(flightReservationDto.getDate())
                .origin(flightReservationDto.getOrigin())
                .flightCode(flightReservationDto.getFlightCode())
                .destination(flightReservationDto.getDestination())
                .peopleQ(flightReservationDto.getPeopleQ())
                .seatType(flightReservationDto.getSeatType())
                .flight(flightService.convertToEntity(flightService.findById(flightReservationDto.getFlightId())))
                .passengers(flightReservationDto.getPassengers().stream()
                        .map(passengerService::convertToEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
