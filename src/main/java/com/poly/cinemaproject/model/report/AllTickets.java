package com.poly.cinemaproject.model.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AllTickets implements Serializable {
    @Id
    private Serializable mave;
    private Integer makh;
    private String tenkhachhang;
    private String tenphim;
    private String hinhanh;
    private Integer tongsove;
    private Integer tongtien;
    private Boolean trangthai;
    private LocalDate ngaydatve;
}
