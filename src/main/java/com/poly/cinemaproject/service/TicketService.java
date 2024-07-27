package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.dto.TicketDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    List<TicketDTO> getAllTicket();
    TicketDTO saveTicket(TicketDTO ticketDTO);
}
