package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.PassengerDTO;

public interface IPassengerService {
    PassengerDTO createPassenge(PassengerDTO passengerRequest);
    PassengerDTO updatePassenger(PassengerDTO passengerRequest, Long id);
}
