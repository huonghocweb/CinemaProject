package com.poly.cinemaproject.controller.myController.home;

import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.entity.Film;
import com.poly.cinemaproject.repository.FilmGenerRepo;
import com.poly.cinemaproject.repository.UserRepo;
import com.poly.cinemaproject.service.FilmService;
import com.poly.cinemaproject.serviceApi.FilmServiceApi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class FilmController {
    @Autowired
    FilmServiceApi filmServiceApi;
    @Autowired
    FilmGenerRepo   filmGenerRepo;
    @Autowired
    HttpSession session;
    @Autowired
    FilmService filmService;
    @Autowired
    UserRepo userRepo;
    @RequestMapping("/")
    public String home(Model model) {
        Flux<FilmDTO> filmFlux= this.filmServiceApi.getAllFilm();
        List<FilmDTO> films= filmFlux.collectList().block();
        model.addAttribute("films", films);
        session.setAttribute("films",films);
        session.removeAttribute("filmChoose");
        session.removeAttribute("showTimeByIdFilm");
        session.removeAttribute("showTimeByIdFilmAndDay");
        session.removeAttribute("showTimeResult");
        session.removeAttribute("seatNormal");
        session.removeAttribute("seatVip");
        session.removeAttribute("seatChooses");
        session.removeAttribute("tickets");
        session.removeAttribute("totalQuantity");
        session.removeAttribute("totalPrice");
        return "home/index";
    }
    @RequestMapping("/home/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        Mono<FilmDTO> filmMono= this.filmServiceApi.getFilmById(id);
        FilmDTO film= filmMono.block();
        model.addAttribute("film", film);
        return "home/detail";
    }

    @RequestMapping("home/filmByKeyWord")
    public String findFilmByKeyWord(Model model,
            @RequestParam("tenphim") String tenphim){
        Mono<FilmDTO> filmMono= filmServiceApi.getFilmByKeyWord(tenphim );
        FilmDTO film= filmMono.block();
        model.addAttribute("film",film);
        System.out.println(film.getTenphim());
        return "home/detail";
    }


}
