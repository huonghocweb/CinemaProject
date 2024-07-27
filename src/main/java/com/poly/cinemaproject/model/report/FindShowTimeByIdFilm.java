package com.poly.cinemaproject.model.report;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FindShowTimeByIdFilm implements Serializable {
    @Id
    private Integer maphim;
    private String tenphim;
    private String tenphongchieu;
    private LocalDate ngaychieu;
    private String giochieu;
    private Long soghetoida;
}
