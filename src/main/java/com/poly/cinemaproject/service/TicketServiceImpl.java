package com.poly.cinemaproject.service;

import com.poly.cinemaproject.mapper.ShowTimeMapper;
import com.poly.cinemaproject.mapper.TicketMapper;
import com.poly.cinemaproject.model.dto.TicketDTO;
import com.poly.cinemaproject.model.dto.UserDTO;
import com.poly.cinemaproject.model.entity.Ticket;
import com.poly.cinemaproject.repository.TicketRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    TicketRepo ticketRepo;

    @Override
    public List<TicketDTO> getAllTicket() {
        List<Ticket> tickets= ticketRepo.findAll();
        List<TicketDTO> ticketDTOS= tickets.stream()
                .map(ticketMapper :: convertToDTO)
                .toList();
        return ticketDTOS;
    }

    @Override
    public TicketDTO saveTicket(TicketDTO ticketDTO) {
        Ticket ticket= ticketMapper.convertToEntity(ticketDTO);
        Ticket ticketSave= ticketRepo.save(ticket);
        return ticketMapper.convertToDTO(ticketSave);
    }
}
