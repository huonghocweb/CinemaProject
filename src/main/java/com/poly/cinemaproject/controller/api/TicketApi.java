package com.poly.cinemaproject.controller.api;

import com.poly.cinemaproject.model.dto.TicketDTO;
import com.poly.cinemaproject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketApi {
    @Autowired
    TicketService ticketService;

    @GetMapping
    public ResponseEntity<?> getAllTicket(){
        return ResponseEntity.ok(ticketService.getAllTicket());
    }

    @PostMapping
    public ResponseEntity<?> saveTicket(@RequestBody TicketDTO ticketDTO){
            return ResponseEntity.ok(ticketService.saveTicket(ticketDTO));
    }
}
