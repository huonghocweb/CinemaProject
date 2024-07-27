package com.poly.cinemaproject.controller.api;

import com.poly.cinemaproject.model.report.FindShowTimeByIdFilm;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilmAndDay;
import com.poly.cinemaproject.model.report.FindShowTimeResult;
import com.poly.cinemaproject.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/showTime")
public class ShowTimeApi {

    @Autowired
    ShowTimeService showTimeService;

    @GetMapping("/{id}")
    public ResponseEntity<List<FindShowTimeByIdFilm>> getShowTimeByIdFilm(@PathVariable("id") Integer maphim){
            return ResponseEntity.ok(showTimeService.findShowTimeByIdFilm(maphim));
    }

    @GetMapping("/{id}/{ngaychieu}")
    public ResponseEntity<List<FindShowTimeByIdFilmAndDay>> getShowTimeByIdFilmAndDay(@PathVariable("id") Integer maphim,
                                                                                      @PathVariable("ngaychieu")LocalDate ngaychieu){
        return ResponseEntity.ok(showTimeService.findShowTimeByIdFilmAndDay(maphim, ngaychieu));
    }
    @GetMapping("/{id}/{ngaychieu}/{giochieu}")
    public ResponseEntity<List<FindShowTimeResult>> getShowTimeResult(
            @PathVariable("id") Integer maphim,
            @PathVariable("ngaychieu") LocalDate ngaychieu,
            @PathVariable("giochieu") String giochieu
    ){
        return ResponseEntity.ok(showTimeService.findSHowTimeResult(maphim, ngaychieu, giochieu));
    }
}
