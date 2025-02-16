package com.example.alejandrocabot_pruebatec4.controller;

import com.example.alejandrocabot_pruebatec4.dto.HotelReservationDTO;
import com.example.alejandrocabot_pruebatec4.service.HotelReservationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency/room-booking")
public class HotelReservationController {
    @Autowired
    private HotelReservationService hotelReservationService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The hotel reservation was created successfully"),
            @ApiResponse(responseCode = "400", description = "The hotel reservation could not be saved")
    })
    @PostMapping
    public ResponseEntity<Double> createHotelReservation(@Valid @RequestBody HotelReservationDTO hotelReservation) {
        return new ResponseEntity<>(hotelReservationService.createHotelReservation(hotelReservation), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The hotel reservation was updated successfully"),
            @ApiResponse(responseCode = "204", description = "The hotel reservation you are trying to edit was not found"),
            @ApiResponse(responseCode = "400", description = "The hotel reservation could not be saved")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Double> updateHotelReservation(@Valid @RequestBody HotelReservationDTO hotelReservation, @Valid @PathVariable Long id) {
        return new ResponseEntity<>(hotelReservationService.updateHotelReservation(hotelReservation, id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The hotel reservation was deleted successfully"),
            @ApiResponse(responseCode = "204", description = "The hotel reservation you are trying to delete was not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HotelReservationDTO> deleteHotelReservation(@Valid @PathVariable Long id) {
        HotelReservationDTO hotelReservationDTO = hotelReservationService.deleteHotelReservation(id);

        return new ResponseEntity<>(hotelReservationDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The hotel reservation was found successfully"),
            @ApiResponse(responseCode = "204", description = "The hotel reservation you are trying to find was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HotelReservationDTO> findById(@Valid @PathVariable Long id) {
        HotelReservationDTO hotelReservationDTO = hotelReservationService.findById(id);

        return new ResponseEntity<>(hotelReservationDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The hotel reservations were retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No results found in the hotel reservations")
    })
    @GetMapping
    public ResponseEntity<List<HotelReservationDTO>> getHotelReservations() {
        return new ResponseEntity<>(hotelReservationService.getHotelReservations(), HttpStatus.OK);
    }
}