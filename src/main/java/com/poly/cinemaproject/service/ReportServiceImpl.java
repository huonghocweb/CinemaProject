package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.report.AllTickets;
import com.poly.cinemaproject.model.report.FilmByRevenue;
import com.poly.cinemaproject.model.report.TicketDetailByTicketId;
import com.poly.cinemaproject.repository.FilmRepo;
import com.poly.cinemaproject.repository.TicketDetailRepo;
import com.poly.cinemaproject.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements  ReportService{

    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    TicketDetailRepo ticketDetailRepo;
    @Autowired
    FilmRepo filmRepo;

    @Override
    public List<AllTickets> getAllTicket() {
        return ticketRepo.getAllTicket();
    }

    @Override
    public List<TicketDetailByTicketId> getTicketDetailByTicketId(String mave) {
        return ticketDetailRepo.getTicketDetailByTicketId(mave);
    }

    @Override
    public List<FilmByRevenue> filmByRevenues() {
        return filmRepo.filmByRevenues() ;
    }
}
