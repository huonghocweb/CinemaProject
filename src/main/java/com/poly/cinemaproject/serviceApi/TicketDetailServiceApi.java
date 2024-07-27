package com.poly.cinemaproject.serviceApi;

import com.poly.cinemaproject.model.dto.*;
import com.poly.cinemaproject.model.entity.Seat;
import com.poly.cinemaproject.model.report.TicketDetailByTicketId;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketDetailServiceApi {
    @Autowired
    WebClient webClient;
    @Autowired
    HttpSession session;

    public Flux<TicketDetailByTicketId> getTicektDetailByTicketId(String mave){
        return webClient.get()
                .uri("/api/ticketDetail/{id}",mave)
                .retrieve()
                .bodyToFlux(TicketDetailByTicketId.class);
    }

    public Mono<Void> saveTicketDetail(TicketDTO ticketDTO) {
        TicketDetailDTO ticketNormal= new TicketDetailDTO();
        TicketDetailDTO ticketVip= new TicketDetailDTO();
        Map<Integer, CartDTO> carts = (Map<Integer, CartDTO>) session.getAttribute("tickets");
        FilmDTO filmChoose= (FilmDTO) session.getAttribute("filmChoose");
        if (carts == null) { }

        List<SeatDTO> seatChooseNormals = carts.values().stream()
                    .map(CartDTO::getSeat)
                    .filter(seat -> seat.getLoaighe() == 1)
                    .toList();
            List<SeatDTO> seatChooseVips = carts.values().stream()
                    .map(CartDTO::getSeat)
                    .filter(seat -> seat.getLoaighe() == 2)
                    .toList();
            if(!seatChooseNormals.isEmpty()){
                    ticketNormal.setSoluong(seatChooseNormals.size());
                    ticketNormal.setDongia(seatChooseNormals.get(0).getDongia());
                    ticketNormal.setTongtien(ticketNormal.getSoluong() * ticketNormal.getDongia());
                    ticketNormal.setTicketDTO(ticketDTO);
                    ticketNormal.setFilmDTO(filmChoose);
                    ticketNormal.setSeatDTO(seatChooseNormals.get(0));
            }
            if(!seatChooseVips.isEmpty()){
                ticketVip.setSoluong(seatChooseVips.size());
                ticketVip.setDongia(seatChooseVips.get(0).getDongia());
                ticketVip.setTongtien(ticketVip.getSoluong() * ticketVip.getDongia());
                ticketVip.setFilmDTO(filmChoose);
                ticketVip.setTicketDTO(ticketDTO);
                ticketVip.setSeatDTO(seatChooseVips.get(0));
            }
        List<Mono<TicketDetailDTO>> detailMonos = new ArrayList<>();
        if(ticketNormal.getSoluong() != null){
            detailMonos.add(webClient.post()
                    .uri("/api/ticketDetail")
                    .bodyValue(ticketNormal)
                    .retrieve()
                    .bodyToMono(TicketDetailDTO.class));
        }
            if(ticketVip.getSoluong() != null){
                detailMonos.add(webClient.post()
                        .uri("/api/ticketDetail")
                        .bodyValue(ticketVip)
                        .retrieve()
                        .bodyToMono(TicketDetailDTO.class));
            }
        // Kết hợp hai Mono và trả về Mono<Void>
        return Mono.when(detailMonos).then();
    }
}
