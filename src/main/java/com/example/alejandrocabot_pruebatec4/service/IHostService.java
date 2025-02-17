package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.HostDTO;
import com.example.alejandrocabot_pruebatec4.dto.PassengerDTO;
import com.example.alejandrocabot_pruebatec4.model.Host;

public interface IHostService {
    HostDTO createHost(Host hostRequest);
    HostDTO updateHost(Host hostRequest, Long id);
}
