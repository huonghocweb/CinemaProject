package com.poly.cinemaproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ve")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mave;
    private LocalDate ngaydatve;
    private Integer tongtien;
    private Boolean trangthai;
    private Integer tongsoghe;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="makhachhang")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="malichchieu")
    private ShowTime showTime;

    @JsonIgnore
    @OneToMany(mappedBy="ticket")
    private List<TicketDetail> ls_Detail;
}
