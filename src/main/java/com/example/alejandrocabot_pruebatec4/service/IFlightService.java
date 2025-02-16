package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.FlightDTO;
import com.example.alejandrocabot_pruebatec4.dto.HotelDTO;

import java.util.List;

public interface IFlightService {
    FlightDTO createFlight(FlightDTO flightRequest);
    FlightDTO updateFlight(FlightDTO flightRequest, Long id);
    FlightDTO deleteFlight(Long id);
    FlightDTO findById(Long id);
    List<FlightDTO> getFlights();
    List<FlightDTO> getFlightsAvailability(String dateTo, String dateFrom, String place, String destination);
}
