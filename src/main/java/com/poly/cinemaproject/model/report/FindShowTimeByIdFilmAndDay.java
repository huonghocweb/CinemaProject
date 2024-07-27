package com.poly.cinemaproject.model.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FindShowTimeByIdFilmAndDay {
    @Id
    private Integer maphim;
    private String tenphim;
    private String tenphongchieu;
    private String giochieu;
    private LocalDate ngaychieu;
}
