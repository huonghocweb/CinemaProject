package com.poly.cinemaproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.cinemaproject.model.entity.Film;
import com.poly.cinemaproject.model.entity.Room;
import com.poly.cinemaproject.model.entity.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeDTO {
    private Integer malichchieu;
    private LocalDate ngaychieu;
    private String giochieu;
    private RoomDTO roomDTO;
    private FilmDTO filmDTO;
    private List<TicketDTO> ls_ticketDTO;
}
