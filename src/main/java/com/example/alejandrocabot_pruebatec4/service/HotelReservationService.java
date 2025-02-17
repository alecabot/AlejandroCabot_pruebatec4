package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.HotelReservationDTO;
import com.example.alejandrocabot_pruebatec4.exception.*;
import com.example.alejandrocabot_pruebatec4.model.FlightReservation;
import com.example.alejandrocabot_pruebatec4.model.Hotel;
import com.example.alejandrocabot_pruebatec4.model.HotelReservation;
import com.example.alejandrocabot_pruebatec4.repository.HotelReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelReservationService implements IHotelReservationService{
    @Autowired
    private HotelReservationRepository hotelReservationRepository;
    @Autowired
    private HostService hostService;
    @Autowired
    private HotelService hotelService;

    @Override
    public HotelReservationDTO findById(Long id) {
        // Encuentra una reserva de hotel por su ID y la convierte a DTO
        return hotelReservationRepository.findById(id).map(this::convertToDto).orElseThrow(() ->
                new NotFoundException("HotelReservation with ID " + id + " not found."));
    }

    @Override
    public Double createHotelReservation(HotelReservationDTO hotelReservationRequest) {
        // Convierte el DTO de la solicitud de reserva de hotel a una entidad
        HotelReservation hotelReservation = convertToEntity(hotelReservationRequest);

        // Guarda la reserva de hotel y devuelve el monto total
        return saveHotelReservation(hotelReservationRequest, hotelReservation);
    }

    @Override
    public Double updateHotelReservation(HotelReservationDTO hotelReservationRequest, Long id) {
        // Verifica si la reserva de hotel existe, si no lanza una excepción
        hotelReservationRepository.findById(id).orElseThrow(() -> new NotFoundException("HotelReservation with ID " + id + " not found."));

        // Convierte el DTO de la solicitud de reserva de hotel a una entidad
        HotelReservation hotelReservation = convertToEntity(hotelReservationRequest);
        hotelReservation.setId(id);

        // Guarda la reserva de hotel actualizada y devuelve el monto total
        return saveHotelReservation(hotelReservationRequest, hotelReservation);
    }

    private double saveHotelReservation(HotelReservationDTO hotelReservationRequest, HotelReservation hotelReservation) {
        // Convierte el DTO de hotel a una entidad y establece el hotel en la reserva de hotel
        Hotel hotel = hotelService.convertToEntity(hotelService.findById(hotelReservationRequest.getHotelId()));
        if (hotel.getIsBooked()) {
            throw new ReservationException("The room is already booked.");
        }
        hotel.setId(hotelReservationRequest.getHotelId());
        hotel.setIsBooked(true);
        hotelService.updateHotel(hotelService.convertToDto(hotel), hotel.getId());

        hotelReservation.setHotel(hotel);

        // Guarda la reserva de hotel en el repositorio

        try {
            HotelReservation hotelReservationSave = hotelReservationRepository.save(hotelReservation);

            // Guarda los anfitriones asociados a la reserva de hotel
            if (hotelReservation.getHosts() != null) {
                hotelReservation.getHosts().forEach(host -> {
                    host.setHotelReservation(hotelReservation);
                    host.getHotelReservation().setId(hotelReservationSave.getId());
                    hostService.createHost(host);
                });
            }
        } catch (Exception e) {
            throw new SaveException("Error saving the Hotel Reservation");
        }



        // Calcula y devuelve el monto total de la reserva de hotel
        return hotel.getRoomPrice() * hotelReservation.getNights() * hotelReservation.getPeopleQ();
    }

    @Override
    public HotelReservationDTO deleteHotelReservation(Long id) {
        // Encuentra una reserva de hotel por su ID y la elimina del repositorio
        HotelReservation hotelReservation = hotelReservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("HotelReservation with ID " + id + " not found."));

        hotelReservation.getHotel().setIsBooked(false);
        hotelService.updateHotel(hotelService.convertToDto(hotelReservation.getHotel()), id);

        hotelReservationRepository.delete(hotelReservation);
        return convertToDto(hotelReservation);
    }

    @Override
    public List<HotelReservationDTO> getHotelReservations() {
        // Obtiene todas las reservas de hotel y las convierte a DTO
        List<HotelReservationDTO> hotelReservations = hotelReservationRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();

        // Lanza una excepción si no se encuentran reservas de hotel
        if (hotelReservations.isEmpty()) {
            throw new EmptyException("No results found in the hotel reservations");
        }

        // Devuelve la lista de reservas de hotel
        return hotelReservations;
    }

    private HotelReservationDTO convertToDto(HotelReservation hotelReservation) {
        return HotelReservationDTO.builder()
                .dateFrom(hotelReservation.getDateFrom())
                .dateTo(hotelReservation.getDateTo())
                .nights(hotelReservation.getNights())
                .place(hotelReservation.getPlace())
                .hotelCode(hotelReservation.getHotelCode())
                .peopleQ(hotelReservation.getPeopleQ())
                .roomType(hotelReservation.getRoomType())
                .hotelId(hotelReservation.getHotel().getId())
                .hosts(hotelReservation.getHosts().stream()
                        .map(hostService::convertToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private HotelReservation convertToEntity(HotelReservationDTO hotelReservationDto) {
        return HotelReservation.builder()
                .dateFrom(hotelReservationDto.getDateFrom())
                .dateTo(hotelReservationDto.getDateTo())
                .nights(hotelReservationDto.getNights())
                .place(hotelReservationDto.getPlace())
                .hotelCode(hotelReservationDto.getHotelCode())
                .peopleQ(hotelReservationDto.getPeopleQ())
                .roomType(hotelReservationDto.getRoomType())
                .hotel(hotelService.convertToEntity(hotelService.findById(hotelReservationDto.getHotelId())))
                .hosts(hotelReservationDto.getHosts().stream()
                        .map(hostService::convertToEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
