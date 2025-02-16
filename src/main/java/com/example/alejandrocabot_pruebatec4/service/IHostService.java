package com.example.alejandrocabot_pruebatec4.service;

import com.example.alejandrocabot_pruebatec4.dto.HostDTO;
import com.example.alejandrocabot_pruebatec4.dto.PassengerDTO;

public interface IHostService {
    HostDTO createHost(HostDTO hostRequest);
    HostDTO updateHost(HostDTO hostRequest, Long id);
}
