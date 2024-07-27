package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.report.AllTickets;
import com.poly.cinemaproject.model.report.FilmByRevenue;
import com.poly.cinemaproject.model.report.TicketDetailByTicketId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    List<AllTickets> getAllTicket();
    List<TicketDetailByTicketId> getTicketDetailByTicketId(String mave);
    List<FilmByRevenue> filmByRevenues();

}
