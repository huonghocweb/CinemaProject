package com.poly.cinemaproject.service;

import com.poly.cinemaproject.mapper.ShowTimeMapper;
import com.poly.cinemaproject.model.dto.ShowTimeDTO;
import com.poly.cinemaproject.model.entity.ShowTime;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilm;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilmAndDay;
import com.poly.cinemaproject.model.report.FindShowTimeResult;
import com.poly.cinemaproject.repository.ShowTimeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShowTimeServiceImpl implements ShowTimeService{

    @Autowired
    ShowTimeMapper showTimeMapper;
    @Autowired
    ShowTimeRepo showTimeRepo;

    @Override
    public Optional<ShowTimeDTO> getShowTimeById(Integer malichchieu) {
        return Optional.of(showTimeRepo.findById(malichchieu)
                .map(showTimeMapper :: convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Not Found Show Time"))) ;
    }

    @Override
    public List<FindShowTimeByIdFilm> findShowTimeByIdFilm(Integer maphim) {
        return this.showTimeRepo.findShowTimeByIdFilm(maphim);
    }

    @Override
    public List<FindShowTimeByIdFilmAndDay> findShowTimeByIdFilmAndDay(Integer maphim, LocalDate ngaychieu) {
        return this.showTimeRepo.findShowTimeByIdFilmAndDay(maphim, ngaychieu);
    }

    @Override
    public List<FindShowTimeResult> findSHowTimeResult(Integer maphim, LocalDate ngaychieu, String giochieu) {
        return this.showTimeRepo.findSHowTimeResult(maphim, ngaychieu, giochieu);
    }
}
