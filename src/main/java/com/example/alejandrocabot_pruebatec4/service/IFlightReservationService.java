package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.FlightReservationDTO;

import java.util.List;

public interface IFlightReservationService {
    Double createFlightReservation(FlightReservationDTO flightReservationRequest);
    Double updateFlightReservation(FlightReservationDTO flightReservationRequest, Long id);
    FlightReservationDTO deleteFlightReservation(Long id);
    FlightReservationDTO findById(Long id);
    List<FlightReservationDTO> getFlightReservations();
}
