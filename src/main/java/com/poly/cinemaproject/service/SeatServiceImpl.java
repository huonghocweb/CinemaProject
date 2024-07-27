package com.poly.cinemaproject.service;

import com.poly.cinemaproject.mapper.SeatMapper;
import com.poly.cinemaproject.model.dto.SeatDTO;
import com.poly.cinemaproject.model.entity.Seat;
import com.poly.cinemaproject.repository.SeatRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService{

    @Autowired
    SeatRepo seatRepo;
    @Autowired
    SeatMapper seatMapper;

    @Override
    public List<SeatDTO> getAllSeat() {
        List<Seat> seats= seatRepo.findAll();
        return seats.stream()
                .map(seatMapper :: convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SeatDTO> getSeatById(Integer maghe) {
        Optional<Seat> seat= seatRepo.findById(maghe);
        return Optional.of(seat
                .map(seatMapper :: convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Seat not found")));
    }

    @Override
    public SeatDTO saveSeat(SeatDTO seatDTO) {
        Seat seat= seatMapper.convertToEntity(seatDTO);
        Seat seatSaved= seatRepo.save(seat);
        return  seatMapper.convertToDTO(seatSaved);
    }

    @Override
    public Optional<SeatDTO> updateSeat(Integer maghe, SeatDTO seatDTO) {
        return Optional.of(seatRepo.findById(maghe)
                .map(existsSeat -> {
                    Seat seat= seatMapper.convertToEntity(seatDTO);
                    seat.setMaghe(existsSeat.getMaghe());
                    Seat seatUpdate= seatRepo.save(seat);
                    return seatMapper.convertToDTO(seatUpdate);
                }).orElseThrow(() -> new EntityNotFoundException("Seat not found")));
    }

    @Override
    public Optional<Void> deleteSeat(Integer maghe) {
        if( seatRepo.existsById(maghe)){
            seatRepo.deleteById(maghe);
        }
        return Optional.empty();
    }
}
