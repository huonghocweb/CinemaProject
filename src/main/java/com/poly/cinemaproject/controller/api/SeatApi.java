package com.poly.cinemaproject.controller.api;

import com.poly.cinemaproject.model.dto.SeatDTO;
import com.poly.cinemaproject.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/seat")
public class SeatApi {
    @Autowired
    SeatService seatService;

    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeat(){
        return ResponseEntity.ok(seatService.getAllSeat());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SeatDTO>> getSeatById(@PathVariable("id") Integer  maghe){
        Optional<SeatDTO> seat= seatService.getSeatById(maghe);
        if(seat.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(seat);
    }
}
