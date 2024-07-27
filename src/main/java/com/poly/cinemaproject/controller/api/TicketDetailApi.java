package com.poly.cinemaproject.controller.api;

import com.poly.cinemaproject.model.dto.TicketDetailDTO;
import com.poly.cinemaproject.service.ReportService;
import com.poly.cinemaproject.service.TicketDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ticketDetail")
public class TicketDetailApi {

    @Autowired
    TicketDetailService ticketDetailService;
    @Autowired
    ReportService reportService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicketDetailByTicketId(@PathVariable("id") String mave){
        return ResponseEntity.ok(reportService.getTicketDetailByTicketId(mave));
    }

    @PostMapping
    public ResponseEntity<?> saveTicketDetail(@RequestBody TicketDetailDTO ticketDetailDTO){
        return ResponseEntity.ok(ticketDetailService.saveTicketDetail(ticketDetailDTO));
    }

}
