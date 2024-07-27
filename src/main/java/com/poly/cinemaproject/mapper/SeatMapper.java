package com.poly.cinemaproject.mapper;

import com.poly.cinemaproject.model.dto.SeatDTO;
import com.poly.cinemaproject.model.entity.Seat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatMapper {
    @Autowired
    ModelMapper modelMapper;
    public Seat convertToEntity(SeatDTO seatDTO){
        return modelMapper.map(seatDTO, Seat.class);
    }
    public SeatDTO convertToDTO(Seat seat){
        return modelMapper.map(seat,SeatDTO.class);
    }
}
