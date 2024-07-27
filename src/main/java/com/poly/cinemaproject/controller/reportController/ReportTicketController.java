package com.poly.cinemaproject.controller.reportController;

import com.poly.cinemaproject.model.dto.TicketDetailDTO;
import com.poly.cinemaproject.model.report.AllTickets;
import com.poly.cinemaproject.model.report.FilmByRevenue;
import com.poly.cinemaproject.model.report.TicketDetailByTicketId;
import com.poly.cinemaproject.service.ReportService;
import com.poly.cinemaproject.service.TicketService;
import com.poly.cinemaproject.serviceApi.FilmServiceApi;
import com.poly.cinemaproject.serviceApi.TicketDetailServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class ReportTicketController {
    @Autowired
    ReportService reportService;
    @Autowired
    TicketDetailServiceApi ticketDetailServiceApi;
    @Autowired
    private FilmServiceApi filmServiceApi;

    @RequestMapping("/admin/report/manageTicket")
    public String manageTicket(Model model){
        List<AllTickets> allTickets= reportService.getAllTicket();
        model.addAttribute("allTickets", allTickets);
        return "admin/report/manageTicket";
    }
    @RequestMapping("/admin/report/ticketDetail/{id}")
    public String ticketDetailByTicketId(Model model,
                                         @PathVariable("id") String mave){
        Flux<TicketDetailByTicketId> ticketDetailFlux= ticketDetailServiceApi.getTicektDetailByTicketId(mave);
        List<TicketDetailByTicketId> ticketDetailsByTicketId = ticketDetailFlux.collectList().block();
        model.addAttribute("ticketDetailsByTicketId", ticketDetailsByTicketId);
        return "admin/report/ticketDetail";
    }

    @RequestMapping("/admin/report/filmByRevenue")
    public String getFilmByRevenue(Model model){
        Flux<FilmByRevenue> filmByRevenueFlux =  filmServiceApi.getFilmByRevenue();
        List<FilmByRevenue> filmByRevenues= filmByRevenueFlux.collectList().block();
        model.addAttribute("filmByRevenues",filmByRevenues);
        return "admin/report/filmByRevenue";
    }
}
