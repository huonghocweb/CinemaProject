package com.poly.cinemaproject.controller.api;
import com.poly.cinemaproject.mapper.FilmMapper;
import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.entity.Film;
import com.poly.cinemaproject.model.report.FilmByRevenue;
import com.poly.cinemaproject.service.FilmService;
import com.poly.cinemaproject.service.ReportService;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/film")
public class FilmRestApi {

    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private ReportService reportService;

    @GetMapping()
    public ResponseEntity<?>getAllFilm(){
        List<FilmDTO>filmsDTO= this.filmService.getAllFilms();
        if(filmsDTO.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable int id){
        Optional<FilmDTO> filmDTO= this.filmService.getFilmById(id);
        if (filmDTO.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(filmDTO);
    }
    @RequestMapping("/filmByRevenue")
    public ResponseEntity<List<FilmByRevenue>> getFilmByRevenue(){
        List<FilmByRevenue> filmByRevenues= reportService.filmByRevenues();
        if(filmByRevenues.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmByRevenues);
    }

    @GetMapping("/filmByKeyWord")
    public ResponseEntity<FilmDTO> getFilmByKeyWord(@RequestParam String tenphim){
        Film film= filmService.findFilmByKeyWord(tenphim);
        if(film == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmMapper.convertToDTO(film));
    }

    @PostMapping
    public ResponseEntity<?> createFilm(@RequestBody FilmDTO filmDTO){
        return ResponseEntity.ok(this.filmService.saveFilm(filmDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable int id, @RequestBody FilmDTO fimlDTO){
        if (this.filmService.getFilmById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.filmService.updateFilm(id, fimlDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Integer id){
        if (this.filmService.getFilmById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        this.filmService.deleteFilmById(id);
        return ResponseEntity.ok().build();
    }

}
