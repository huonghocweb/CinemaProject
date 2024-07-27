package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.entity.Film;
import com.poly.cinemaproject.model.report.FilmByRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepo extends JpaRepository<Film,Integer> {
    @Query("Select f FROM Film f where f.tenphim = ?1 ")
    Film findFilmByKeyWord(String tenphim);

    @Query("Select new com.poly.cinemaproject.model.report.FilmByRevenue(f,sum(tk.tongtien),sum(tk.tongsoghe))"
            + " FROM Film  f JOIN f.ls_ticketDetail tkdt JOIN tkdt.ticket tk"
            + " GROUP BY f"
            + " ORDER BY sum(tk.tongtien) DESC")
    List<FilmByRevenue> filmByRevenues();
}
