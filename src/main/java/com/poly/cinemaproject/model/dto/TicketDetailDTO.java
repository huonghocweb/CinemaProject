package com.poly.cinemaproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.cinemaproject.model.entity.Film;
import com.poly.cinemaproject.model.entity.Seat;
import com.poly.cinemaproject.model.entity.Ticket;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetailDTO {
    private Integer mavechitiet;
    private Integer dongia;
    private Integer soluong;
    private Integer tongtien;
    private TicketDTO ticketDTO;
    private FilmDTO filmDTO;
    private SeatDTO seatDTO;

}
