package com.poly.cinemaproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vechitiet")
public class TicketDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mavechitiet;
    private Integer dongia;
    private Integer soluong;
    private Integer tongtien;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="mave")
    private Ticket ticket;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="maphim")
    private Film film;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="maghe")
    private Seat seat;
}
