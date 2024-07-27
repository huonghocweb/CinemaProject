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
public class FindShowTimeResult {
    @Id
    private Integer maphim;
    private String tenphim;
    private String tenphongchieu;
    private LocalDate ngaychieu;
    private String giochieu;
    private Long soghe;
    private Integer maphongchieu;
    private Integer malichchieu;

}
