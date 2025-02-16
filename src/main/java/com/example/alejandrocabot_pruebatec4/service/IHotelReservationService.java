package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.HotelReservationDTO;

import java.util.List;

public interface IHotelReservationService {
    Double createHotelReservation(HotelReservationDTO hotelReservationRequest);
    Double updateHotelReservation(HotelReservationDTO hotelReservationRequest, Long id);
    HotelReservationDTO deleteHotelReservation(Long id);
    HotelReservationDTO findById(Long id);
    List<HotelReservationDTO> getHotelReservations();
}
