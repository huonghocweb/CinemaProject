package com.poly.cinemaproject.serviceApi;


import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.entity.Film;
import com.poly.cinemaproject.model.report.FilmByRevenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FilmServiceApi {
    @Autowired
    WebClient webClient;

    public Flux<FilmDTO> getAllFilm(){
            return webClient.get()
                    .uri("/api/film")
                    .retrieve()
                    .bodyToFlux(FilmDTO.class);
    }
    public Mono<FilmDTO> getFilmById(int id){
        return webClient.get()
                .uri("/api/film/{id}" ,id)
                .retrieve()
                .bodyToMono(FilmDTO.class);
    }
    public Flux<FilmByRevenue> getFilmByRevenue(){
        return webClient.get()
                .uri("/api/film/filmByRevenue")
                .retrieve()
                .bodyToFlux(FilmByRevenue.class);
    }
    public Mono<FilmDTO> getFilmByKeyWord(String tenphim){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/film/filmByKeyWord")
                        .queryParam("tenphim", tenphim)
                        .build())
                .retrieve()
                .bodyToMono(FilmDTO.class);
    }
    public Mono<Film> saveFilm(Film film){
        return webClient.post()
                .uri("/api/film")
                .bodyValue(film)
                .retrieve()
                .bodyToMono(Film.class);
    }
    public Mono<Film> updateFilm(int id,Film film){
        return webClient.put()
                .uri("/api/film/{id}",id)
                .bodyValue(film)
                .retrieve()
                .bodyToMono(Film.class);
    }
    public Mono<Void> deleteFilm(int id){
        return webClient.delete()
                .uri("/api/film/{id}" ,id)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
