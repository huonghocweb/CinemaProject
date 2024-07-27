package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.entity.FilmGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmGenerRepo extends JpaRepository<FilmGenre,String> {
}
