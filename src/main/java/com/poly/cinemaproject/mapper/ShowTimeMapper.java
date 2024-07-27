package com.poly.cinemaproject.mapper;

import com.poly.cinemaproject.model.dto.ShowTimeDTO;
import com.poly.cinemaproject.model.entity.ShowTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowTimeMapper {
    @Autowired
    ModelMapper modelMapper;
    public ShowTime convertToEntity(ShowTimeDTO showTimeDTO){
        return modelMapper.map(showTimeDTO, ShowTime.class);
    }
    public ShowTimeDTO convertToDTO(ShowTime showTime){
        return modelMapper.map(showTime,ShowTimeDTO.class);
    }
}
