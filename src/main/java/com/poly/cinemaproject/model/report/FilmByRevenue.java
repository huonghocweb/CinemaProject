package com.poly.cinemaproject.model.report;

import com.poly.cinemaproject.model.entity.Film;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmByRevenue {
    private Film film;
    private Long tongdoanhthu;
    private Long tongsove;
}
