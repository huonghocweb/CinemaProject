package com.poly.cinemaproject.serviceApi;

import com.poly.cinemaproject.model.dto.SeatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SeatServiceApi {
    @Autowired
    WebClient webClient;

    public Flux<SeatDTO> getAllSeat(){
        return webClient.get()
                .uri("api/seat")
                .retrieve()
                .bodyToFlux(SeatDTO.class);
    }
    public Mono<SeatDTO> getSeatById(Integer maghe){
        return webClient.get()
                .uri("api/seat/", maghe)
                .retrieve()
                .bodyToMono(SeatDTO.class);
    }

}
