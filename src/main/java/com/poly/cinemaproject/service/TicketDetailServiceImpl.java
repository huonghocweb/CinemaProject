package com.poly.cinemaproject.service;

import com.poly.cinemaproject.mapper.TicketDetailMapper;
import com.poly.cinemaproject.mapper.TicketMapper;
import com.poly.cinemaproject.model.dto.TicketDetailDTO;
import com.poly.cinemaproject.model.entity.TicketDetail;
import com.poly.cinemaproject.repository.TicketDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketDetailServiceImpl implements TicketDetailService {

    @Autowired
    TicketDetailRepo ticketDetailRepo;
    @Autowired
    TicketDetailMapper ticketDetailMapper;

    @Override
    public List<TicketDetailDTO> getAllTicketDetail() {
        return List.of();
    }

    @Override
    public TicketDetailDTO saveTicketDetail(TicketDetailDTO ticketDetailDTO) {
        TicketDetail ticketDetail= ticketDetailMapper.convertToEntity(ticketDetailDTO);
        TicketDetail ticketDetailSave= ticketDetailRepo.save(ticketDetail);
        return ticketDetailMapper.convertToDTO(ticketDetailSave
        );
    }

}
