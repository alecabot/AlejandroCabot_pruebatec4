package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.HostDTO;
import com.example.alejandrocabot_pruebatec4.dto.PassengerDTO;
import com.example.alejandrocabot_pruebatec4.exception.NotFoundException;
import com.example.alejandrocabot_pruebatec4.exception.SaveException;
import com.example.alejandrocabot_pruebatec4.model.Host;
import com.example.alejandrocabot_pruebatec4.model.Passenger;
import com.example.alejandrocabot_pruebatec4.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService implements IPassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public PassengerDTO createPassenge(Passenger passengerRequest) {
        // Convierte el DTO de la solicitud de pasajero a una entidad y guarda el pasajero en el repositorio
        passengerRepository.save(passengerRequest);
        // Devuelve el DTO del pasajero creado
        return convertToDto(passengerRequest) ;
    }

    @Override
    public PassengerDTO updatePassenger(Passenger passenger, Long id) {
        // Verifica si el pasajero existe, si no lanza una excepción
        passengerRepository.findById(id).orElseThrow(() -> new NotFoundException("Passenger with ID " + id + " not found."));

        // Convierte el DTO del pasajero a una entidad y establece el ID
        passenger.setId(id);

        // Guarda el pasajero actualizado en el repositorio
        try {
            passengerRepository.save(passenger);
        } catch (Exception e) {
            throw new SaveException("Error saving the passenger");
        }

        // Devuelve el DTO del pasajero actualizado
        return convertToDto(passenger);
    }

    public PassengerDTO convertToDto(Passenger passenger) {
        return PassengerDTO.builder()
                .name(passenger.getName())
                .lastName(passenger.getLastName())
                .age(passenger.getAge())
                .build();
    }

    public Passenger convertToEntity(PassengerDTO passengerDTO) {
        return Passenger.builder()
                .name(passengerDTO.getName())
                .lastName(passengerDTO.getLastName())
                .age(passengerDTO.getAge())
                .build();
    }
}
