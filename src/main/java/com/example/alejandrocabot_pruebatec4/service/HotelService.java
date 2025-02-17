package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.HotelDTO;
import com.example.alejandrocabot_pruebatec4.exception.*;
import com.example.alejandrocabot_pruebatec4.model.Hotel;
import com.example.alejandrocabot_pruebatec4.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class HotelService implements IHotelService{
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelDTO findById(Long id) {
        // Encuentra un hotel por su ID y lo convierte a DTO
        return hotelRepository.findById(id).map(this::convertToDto).orElseThrow(() ->
                new NotFoundException("Hotel with ID " + id + " not found."));
    }

    @Override
    public HotelDTO createHotel(HotelDTO hotelRequest) {
        // Crea un nuevo hotel a partir del DTO proporcionado y lo guarda en el repositorio
        try {
            hotelRepository.save(convertToEntity(hotelRequest));
        } catch (Exception e) {
            throw new SaveException("Error saving the Hotel");
        }

        // Devuelve el DTO del hotel creado
        return hotelRequest;
    }

    @Override
    public HotelDTO updateHotel(HotelDTO hotelRequest, Long id) {
        // Verifica si el hotel existe, si no lanza una excepción
        hotelRepository.findById(id).orElseThrow(() -> new NotFoundException("Hotel with ID " + id + " not found."));

        // Convierte el DTO del hotel a una entidad y establece el ID
        Hotel hotel = convertToEntity(hotelRequest);
        hotel.setId(id);
        // Guarda el hotel actualizado en el repositorio
        try {
            hotelRepository.save(hotel);
        } catch (Exception e) {
            throw new SaveException("Error saving the Hotel");
        }

        // Devuelve el DTO del hotel actualizado
        return convertToDto(hotel);
    }

    @Override
    public HotelDTO deleteHotel(Long id) {
        // Encuentra un hotel por su ID y lo elimina del repositorio
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel with ID " + id + " not found."));

        // Verifica si el hotel tiene reservas asociadas, si es así lanza una excepción
        if (hotel.getHotelReservation() != null) {
            throw new NotDeletedException("The Hotel has associated reservations and cannot be deleted.");
        }
        hotelRepository.delete(hotel);

        // Devuelve el DTO del hotel eliminado
        return convertToDto(hotel);
    }

    @Override
    public List<HotelDTO> getHotels() {
        // Obtiene todos los hoteles y los convierte a DTO
        List<HotelDTO> hotels = hotelRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
        // Lanza una excepción si no se encuentran hoteles
        if (hotels.isEmpty()) {
            throw new EmptyException("No results found in the hotel.");
        }
        // Devuelve la lista de hoteles
        return hotels;
    }

    @Override
    public List<HotelDTO> getRooms(String dateFromStr, String dateToStr, String place) {
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

        // Busca habitaciones disponibles según los criterios proporcionados y las convierte a DTO
        List<HotelDTO> hotels = hotelRepository
                .findByDateFromLessThanEqualAndDateToGreaterThanEqualAndIsBookedIsFalseAndPlace(
                        dateTo, dateFrom, place
                )
                .stream()
                .map(this::convertToDto)
                .toList();

        // Lanza una excepción si no se encuentran habitaciones
        if (hotels.isEmpty()) {
            throw new EmptyException("No results found in the hotel.");
        }

        // Devuelve la lista de habitaciones disponibles
        return hotels;
    }

    public HotelDTO convertToDto(Hotel hotel) {
        return HotelDTO.builder()
                .hotelCode(hotel.getHotelCode())
                .name(hotel.getName())
                .place(hotel.getPlace())
                .roomType(hotel.getRoomType())
                .roomPrice(hotel.getRoomPrice())
                .dateFrom(hotel.getDateFrom())
                .dateTo(hotel.getDateTo())
                .isBooked(hotel.getIsBooked())
                .build();
    }

    public Hotel convertToEntity(HotelDTO hotelDto) {
        return Hotel.builder()
                .hotelCode(hotelDto.getHotelCode())
                .name(hotelDto.getName())
                .place(hotelDto.getPlace())
                .roomType(hotelDto.getRoomType())
                .roomPrice(hotelDto.getRoomPrice())
                .dateFrom(hotelDto.getDateFrom())
                .dateTo(hotelDto.getDateTo())
                .isBooked(hotelDto.getIsBooked())
                .build();
    }
}
