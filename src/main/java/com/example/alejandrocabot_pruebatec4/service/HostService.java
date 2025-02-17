package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.HostDTO;
import com.example.alejandrocabot_pruebatec4.dto.HotelDTO;
import com.example.alejandrocabot_pruebatec4.dto.PassengerDTO;
import com.example.alejandrocabot_pruebatec4.exception.NotFoundException;
import com.example.alejandrocabot_pruebatec4.exception.SaveException;
import com.example.alejandrocabot_pruebatec4.model.Host;
import com.example.alejandrocabot_pruebatec4.model.Hotel;
import com.example.alejandrocabot_pruebatec4.model.Passenger;
import com.example.alejandrocabot_pruebatec4.repository.HostRepository;
import com.example.alejandrocabot_pruebatec4.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostService implements IHostService{
    @Autowired
    HostRepository hostRepository;

    @Override
    public HostDTO createHost(Host hostRequest) {
        // Convierte el DTO de la solicitud de host a una entidad y guarda el host en el repositorio
        hostRepository.save(hostRequest);
        // Devuelve el DTO del host creado
        return convertToDto(hostRequest);
    }

    @Override
    public HostDTO updateHost(Host hostRequest, Long id) {
        // Verifica si el host existe, si no lanza una excepción
        hostRepository.findById(id).orElseThrow(() -> new NotFoundException("Host with ID " + id + " not found."));

        // Convierte el DTO del host a una entidad y establece el ID
        hostRequest.setId(id);

        // Guarda el host actualizado en el repositorio
        try {
            hostRepository.save(hostRequest);
        } catch (Exception e) {
            throw new SaveException("Error saving the passenger");
        }

        // Devuelve el DTO del host actualizado
        return convertToDto(hostRequest);
    }

    public HostDTO convertToDto(Host host) {
        return HostDTO.builder()
                .name(host.getName())
                .lastName(host.getName())
                .lastName(host.getName())
                .age(host.getAge())
                .build();
    }

    public Host convertToEntity(HostDTO hostDTO) {
        return Host.builder()
                .name(hostDTO.getName())
                .lastName(hostDTO.getName())
                .age(hostDTO.getAge())
                .build();
    }
}
