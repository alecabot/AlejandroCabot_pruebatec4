package com.example.alejandrocabot_pruebatec4.controller;

import com.example.alejandrocabot_pruebatec4.dto.FlightReservationDTO;
import com.example.alejandrocabot_pruebatec4.service.FlightReservationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency/flight-booking")
public class FlightReservationController {
    @Autowired
    private FlightReservationService flightReservationService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The flight reservation was created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "400", description = "The flight reservation could not be saved")
    })
    @PostMapping
    public ResponseEntity<Double> createFlightReservation(@Valid @RequestBody FlightReservationDTO flightReservation) {
        return new ResponseEntity<>(flightReservationService.createFlightReservation(flightReservation), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The flight reservation was updated successfully"),
            @ApiResponse(responseCode = "204", description = "The flight reservation you are trying to edit was not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Double> updateFlightReservation(@Valid @RequestBody FlightReservationDTO flightReservation, @Valid @PathVariable Long id) {
        return new ResponseEntity<>(flightReservationService.updateFlightReservation(flightReservation, id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The flight reservation was deleted successfully"),
            @ApiResponse(responseCode = "204", description = "The flight reservation you are trying to delete was not found"),
            @ApiResponse(responseCode = "400", description = "The flight reservation could not be saved")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<FlightReservationDTO> deleteFlightReservation(@Valid @PathVariable Long id) {
        FlightReservationDTO flightReservationDTO = flightReservationService.deleteFlightReservation(id);

        return new ResponseEntity<>(flightReservationDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The flight reservation was found successfully"),
            @ApiResponse(responseCode = "204", description = "The flight reservation you are trying to find was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FlightReservationDTO> findById(@Valid @PathVariable Long id) {
        FlightReservationDTO flightReservationDTO = flightReservationService.findById(id);

        return new ResponseEntity<>(flightReservationDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The flight reservations were retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No results found in the flight reservations")
    })
    @GetMapping
    public ResponseEntity<List<FlightReservationDTO>> getFlightReservations() {
        return new ResponseEntity<>(flightReservationService.getFlightReservations(), HttpStatus.OK);
    }


}