package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.dto.TicketDTO;
import com.poly.cinemaproject.model.dto.TicketDetailDTO;
import com.poly.cinemaproject.model.entity.TicketDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketDetailService {
    List<TicketDetailDTO> getAllTicketDetail();
    TicketDetailDTO saveTicketDetail(TicketDetailDTO ticketDetailDTO);
}
