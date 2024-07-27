package com.poly.cinemaproject.model.report;

import com.poly.cinemaproject.model.dto.SeatDTO;
import com.poly.cinemaproject.model.entity.Seat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TicketDetailByTicketId implements Serializable {
    @Id
    private Serializable mavechitiet;
    private Integer mave;
    private Integer soluong;
    private Integer dongia;
    private Integer tongtien;
    private LocalDate ngaychieu;
    private String giochieu;
    private Integer loaighe;
    private String tenphongchieu;
}
