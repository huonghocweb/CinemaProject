package com.poly.cinemaproject.mapper;

import com.poly.cinemaproject.model.dto.TicketDTO;
import com.poly.cinemaproject.model.dto.TicketDetailDTO;
import com.poly.cinemaproject.model.entity.Ticket;
import com.poly.cinemaproject.model.entity.TicketDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketDetailMapper {

    @Autowired
    ModelMapper modelMapper;

    public TicketDetail convertToEntity(TicketDetailDTO ticketDetailDTO){
        return modelMapper.map(ticketDetailDTO, TicketDetail.class);
    }
    public TicketDetailDTO convertToDTO(TicketDetail ticketDetail){
        return modelMapper.map(ticketDetail,TicketDetailDTO.class);
    }
}
