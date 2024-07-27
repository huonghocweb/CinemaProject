package com.poly.cinemaproject.controller.myController.admin;

import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.dto.SeatDTO;
import com.poly.cinemaproject.model.entity.Film;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilm;
import com.poly.cinemaproject.model.report.FindShowTimeByIdFilmAndDay;
import com.poly.cinemaproject.model.report.FindShowTimeResult;
import com.poly.cinemaproject.service.SeatService;
import com.poly.cinemaproject.service.ShowTimeService;
import com.poly.cinemaproject.serviceApi.FilmServiceApi;
import com.poly.cinemaproject.serviceApi.SeatServiceApi;
import com.poly.cinemaproject.serviceApi.ShowTimeServiceApi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ShowTimeController {

    @Autowired
    FilmServiceApi filmServiceApi;
    @Autowired
    ShowTimeServiceApi showTimeServiceApi;
    @Autowired
    SeatServiceApi seatServiceApi;
    @Autowired
    HttpSession httpSession;

    @RequestMapping("/home/showTimeByIdFilm/{id}")
    public String showTimeByIdFilm(Model model,
                           @PathVariable("id") Integer maphim){
        Flux<FindShowTimeByIdFilm> fluxShowTime= showTimeServiceApi.getShowTimeByIdFilm(maphim);
        List<FindShowTimeByIdFilm> showTimeByIdFilm= fluxShowTime.collectList().block();
        httpSession.setAttribute("showTimeByIdFilm",showTimeByIdFilm);
        httpSession.setAttribute("maphim",maphim);
        httpSession.setAttribute("filmChoose",showTimeByIdFilm.get(0).getTenphim());
        return "home/bookSeat";
    }

    @RequestMapping("/home/showTimeByIdFilmAndDay/{ngaychieu}")
    public String showTimeByIdFilmAndDay(Model model,
                                         @PathVariable("ngaychieu")LocalDate ngaychieu){
        Flux<FindShowTimeByIdFilmAndDay> fluxShowTime= showTimeServiceApi.getShowTimeByIdFilmAndDay((Integer) httpSession.getAttribute("maphim"), ngaychieu);
        List<FindShowTimeByIdFilmAndDay> showTimeByIdFilmAndDay= fluxShowTime.collectList().block();
        httpSession.setAttribute("showTimeByIdFilmAndDay",showTimeByIdFilmAndDay);
        return "home/bookSeat";
    }

    @RequestMapping("/home/showTimeResult/{giochieu}")
    public String showTimeResult(Model model,
                                 @PathVariable("giochieu") String giochieu){
        List<FindShowTimeByIdFilmAndDay> showTimeByIdFilmAndDay  = (List<FindShowTimeByIdFilmAndDay>) httpSession.getAttribute("showTimeByIdFilmAndDay");
        Flux<FindShowTimeResult> fluxShowTime= showTimeServiceApi.getShowTimeResult(showTimeByIdFilmAndDay.get(0).getMaphim(),showTimeByIdFilmAndDay.get(0).getNgaychieu(),giochieu);
        List<FindShowTimeResult> showTimeResult = fluxShowTime.collectList().block();
        Mono<FilmDTO> filmMono= this.filmServiceApi.getFilmById(showTimeResult.get(0).getMaphim());
        httpSession.setAttribute("filmChoose", filmMono.block());
        httpSession.setAttribute("showTimeResult",showTimeResult);
        Flux<SeatDTO> fluxSeat= seatServiceApi.getAllSeat();
        List<SeatDTO> ls_seat= fluxSeat.collectList().block();
        List<SeatDTO> seatRooms =ls_seat.stream()
                                .filter(seatDTO -> seatDTO.getRoom().getMaphongchieu()== showTimeResult.get(0).getMaphongchieu())
                                .collect(Collectors.toList());
        Map<Integer, List<SeatDTO>> mapSeat= seatRooms.stream()
                        .collect(Collectors.groupingBy(SeatDTO :: getLoaighe));
        List<SeatDTO> seatNormal= mapSeat.get(1);
        List<SeatDTO> seatVip= mapSeat.get(2);
        httpSession.setAttribute("malichchieu", showTimeResult.get(0).getMalichchieu());
        httpSession.setAttribute("seatNormal", seatNormal);
        httpSession.setAttribute("seatVip", seatVip);
        return "home/bookSeat";
    }

}
