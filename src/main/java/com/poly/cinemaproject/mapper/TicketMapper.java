package com.poly.cinemaproject.mapper;

import com.poly.cinemaproject.model.dto.TicketDTO;
import com.poly.cinemaproject.model.entity.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {

    @Autowired
    ModelMapper modelMapper;

    public Ticket convertToEntity(TicketDTO ticketDTO){
        return modelMapper.map(ticketDTO, Ticket.class);
    }
    public TicketDTO convertToDTO(Ticket ticket){
        return modelMapper.map(ticket,TicketDTO.class);
    }
}
