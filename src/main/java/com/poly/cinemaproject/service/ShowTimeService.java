package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.dto.ShowTimeDTO;
import com.poly.cinemaproject.model.entity.ShowTime;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilm;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilmAndDay;
import com.poly.cinemaproject.model.report.FindShowTimeResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface ShowTimeService {
    Optional<ShowTimeDTO> getShowTimeById(Integer malichchieu);
    List<FindShowTimeByIdFilm> findShowTimeByIdFilm(Integer maphim);
    List<FindShowTimeByIdFilmAndDay> findShowTimeByIdFilmAndDay(Integer maphim, LocalDate ngaychieu);
    List<FindShowTimeResult> findSHowTimeResult(Integer maphim, LocalDate ngaychieu, String giochieu);
}
