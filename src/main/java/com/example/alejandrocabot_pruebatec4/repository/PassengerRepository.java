package com.example.alejandrocabot_pruebatec4.repository;

import com.example.alejandrocabot_pruebatec4.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
