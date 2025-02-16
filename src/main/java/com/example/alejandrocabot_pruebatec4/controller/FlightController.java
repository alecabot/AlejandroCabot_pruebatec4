package com.example.alejandrocabot_pruebatec4.controller;

import com.example.alejandrocabot_pruebatec4.dto.FlightDTO;
import com.example.alejandrocabot_pruebatec4.dto.HotelDTO;
import com.example.alejandrocabot_pruebatec4.service.FlightService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The flight was created successfully"),
            @ApiResponse(responseCode = "400", description = "The flight could not be saved")
    })
    @PostMapping
    public ResponseEntity<FlightDTO> createFlight(@Valid @RequestBody FlightDTO flight) {
        return new ResponseEntity<>(flightService.createFlight(flight), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The operation was executed successfully"),
            @ApiResponse(responseCode = "204", description = "The flight you are trying to edit was not found"),
            @ApiResponse(responseCode = "400", description = "The flight could not be saved")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FlightDTO> updateFlight(@Valid @RequestBody FlightDTO flight, @Valid @PathVariable Long id) {
        FlightDTO flightDTO = flightService.updateFlight(flight, id);

        return new ResponseEntity<>(flightDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The flight was deleted successfully"),
            @ApiResponse(responseCode = "204", description = "The flight you are trying to delete was not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<FlightDTO> deleteFlight(@Valid @PathVariable Long id) {
        FlightDTO flightDTO = flightService.deleteFlight(id);

        return new ResponseEntity<>(flightDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The flight was found successfully"),
            @ApiResponse(responseCode = "204", description = "The flight you are trying to find was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> findById(@Valid @PathVariable Long id) {
        FlightDTO flightDTO = flightService.findById(id);

        return new ResponseEntity<>(flightDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The flights were retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No results found in the flight")
    })
    @GetMapping
    public ResponseEntity<List<FlightDTO>> getHotelsOrRooms(
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            @RequestParam(required = false) String place,
            @RequestParam(required = false) String destination)  {
        if (dateFrom != null && dateTo != null && place != null && destination != null) {
            return new ResponseEntity<>(flightService.getFlightsAvailability(dateFrom, dateTo, place, destination), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(flightService.getFlights(), HttpStatus.OK);
        }
    }

}