package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.entity.Film;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FilmService {
     List<FilmDTO> getAllFilms();
     Optional<FilmDTO> getFilmById(Integer id);
     FilmDTO saveFilm(FilmDTO filmDTO);
     Optional<Void> deleteFilmById(Integer id);
     Optional<FilmDTO> updateFilm(Integer id, FilmDTO filmDTO);
     Film findFilmByKeyWord(String tenphim);}
