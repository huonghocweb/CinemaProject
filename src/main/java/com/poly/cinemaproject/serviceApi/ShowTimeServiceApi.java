package com.poly.cinemaproject.serviceApi;

import com.poly.cinemaproject.model.report.FindShowTimeByIdFilm;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilmAndDay;
import com.poly.cinemaproject.model.report.FindShowTimeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
public class ShowTimeServiceApi {
    @Autowired
    WebClient webClient;

    public Flux<FindShowTimeByIdFilm> getShowTimeByIdFilm(Integer maphim){
        return webClient.get()
                .uri("/api/showTime/{id}", maphim)
                .retrieve()
                .bodyToFlux(FindShowTimeByIdFilm.class);
    }
    public Flux<FindShowTimeByIdFilmAndDay> getShowTimeByIdFilmAndDay(Integer maphim, LocalDate ngaychieu){
        return webClient.get()
                .uri("/api/showTime/{id}/{ngaychieu}", maphim, ngaychieu)
                .retrieve()
                .bodyToFlux(FindShowTimeByIdFilmAndDay.class);
    }
    public Flux<FindShowTimeResult> getShowTimeResult(Integer maphim, LocalDate ngaychieu, String giochieu){
        return webClient.get()
                .uri("api/showTime/{id}/{ngaychieu}/{giochieu}" , maphim,ngaychieu,giochieu)
                .retrieve()
                .bodyToFlux(FindShowTimeResult.class);
    }

}
