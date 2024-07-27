package com.poly.cinemaproject.serviceApi;

import com.poly.cinemaproject.mapper.ShowTimeMapper;
import com.poly.cinemaproject.mapper.UserMapper;
import com.poly.cinemaproject.model.dto.ShowTimeDTO;
import com.poly.cinemaproject.model.dto.TicketDTO;
import com.poly.cinemaproject.model.dto.UserDTO;
import com.poly.cinemaproject.model.entity.ShowTime;
import com.poly.cinemaproject.model.entity.Ticket;
import com.poly.cinemaproject.service.ShowTimeService;
import com.poly.cinemaproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceApi {
    @Autowired
    WebClient webClient;
    @Autowired
    HttpSession session;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ShowTimeMapper showTimeMapper;
    @Autowired
    ShowTimeService showTimeService;
    @Autowired
    TicketDetailServiceApi ticketDetailServiceApi;


    public Flux<TicketDTO> getAllTicket(){
        return webClient.get()
                .uri("/api/ticket")
                .retrieve()
                .bodyToFlux(TicketDTO.class);
    }

    public Mono<TicketDTO> saveTicket(Integer totalPrice, Integer malichchieu){
        TicketDTO ticketDTO = new TicketDTO();
        List<String> seatChooses= (List<String>) session.getAttribute("seatChooses");
        ticketDTO.setTongtien(totalPrice);
        ticketDTO.setNgaydatve(LocalDate.now());
        ticketDTO.setTrangthai(true);
        ticketDTO.setTongsoghe(seatChooses.size());
        // session.getAttribute Userlogin
        Optional<ShowTimeDTO> showTimeOptional= showTimeService.getShowTimeById(malichchieu);
        showTimeOptional.ifPresent(ticketDTO::setShowTimeDTO);
        Optional<UserDTO> userOptional= userService.getUserById(1);
        userOptional.ifPresent(ticketDTO :: setUserDTO);
        return webClient.post()
                .uri("/api/ticket")
                .bodyValue(ticketDTO)
                .retrieve()
                .bodyToMono(TicketDTO.class);
    }

}
