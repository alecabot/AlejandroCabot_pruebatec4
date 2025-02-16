package com.example.alejandrocabot_pruebatec4.repository;

import com.example.alejandrocabot_pruebatec4.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
