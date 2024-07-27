package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.dto.SeatDTO;
import com.poly.cinemaproject.model.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SeatService {
    List<SeatDTO> getAllSeat();
    Optional<SeatDTO> getSeatById(Integer maghe);
    SeatDTO saveSeat(SeatDTO seatDTO);
    Optional<SeatDTO> updateSeat(Integer maghe, SeatDTO seatDTO);
    Optional<Void> deleteSeat(Integer maghe);
}
