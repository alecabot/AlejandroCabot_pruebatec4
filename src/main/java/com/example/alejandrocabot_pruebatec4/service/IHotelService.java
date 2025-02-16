package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.HotelDTO;

import java.time.LocalDate;
import java.util.List;

public interface IHotelService {
    HotelDTO createHotel(HotelDTO hotelRequest);
    HotelDTO updateHotel(HotelDTO hotelRequest, Long id);
    HotelDTO deleteHotel(Long id);
    HotelDTO findById(Long id);
    List<HotelDTO> getHotels();
    List<HotelDTO> getRooms(String dateTo, String dateFrom, String place);
}
