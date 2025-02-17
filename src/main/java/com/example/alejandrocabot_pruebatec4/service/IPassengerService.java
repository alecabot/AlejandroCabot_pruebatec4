package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.PassengerDTO;
import com.example.alejandrocabot_pruebatec4.model.Passenger;

public interface IPassengerService {
    PassengerDTO createPassenge(Passenger passengerRequest);
    PassengerDTO updatePassenger(Passenger passengerRequest, Long id);
}
