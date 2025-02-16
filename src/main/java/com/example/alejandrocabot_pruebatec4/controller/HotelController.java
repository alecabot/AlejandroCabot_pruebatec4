package com.example.alejandrocabot_pruebatec4.controller;

import com.example.alejandrocabot_pruebatec4.dto.HotelDTO;
import com.example.alejandrocabot_pruebatec4.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agency/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The hotel was created successfully"),
            @ApiResponse(responseCode = "400", description = "The hotel could not be saved")
    })
    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@Valid @RequestBody HotelDTO hotel) {
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The operation was executed successfully"),
            @ApiResponse(responseCode = "204", description = "The hotel you are trying to edit was not found"),
            @ApiResponse(responseCode = "400", description = "The hotel could not be saved")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@Valid @RequestBody HotelDTO hotel, @Valid @PathVariable Long id) {
        HotelDTO hotelDTO = hotelService.updateHotel(hotel, id);

        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The hotel was deleted successfully"),
            @ApiResponse(responseCode = "204", description = "The hotel you are trying to delete was not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HotelDTO> deleteHotel(@Valid @PathVariable Long id) {
        HotelDTO hotelDTO = hotelService.deleteHotel(id);

        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The hotel was found successfully"),
            @ApiResponse(responseCode = "204", description = "The hotel you are trying to find was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> findById(@Valid @PathVariable Long id) {
        HotelDTO hotelDTO = hotelService.findById(id);

        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The hotels were retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No results found in the hotel")
    })
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getHotelsOrRooms(
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            @RequestParam(required = false) String place) {
        if (dateFrom != null && dateTo != null && place != null) {
            return new ResponseEntity<>(hotelService.getRooms(dateFrom, dateTo, place), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(hotelService.getHotels(), HttpStatus.OK);
        }
    }

}