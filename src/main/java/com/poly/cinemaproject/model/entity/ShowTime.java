package com.poly.cinemaproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="lichchieu")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer malichchieu;
    private LocalDate ngaychieu;
    private String giochieu;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="maphongchieu")
    private Room room;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="maphim")
    private Film film;

    @JsonIgnore
    @OneToMany(mappedBy = "showTime")
    private List<Ticket> ls_ticket;
}
