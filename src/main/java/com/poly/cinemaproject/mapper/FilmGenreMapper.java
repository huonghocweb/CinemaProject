package com.poly.cinemaproject.mapper;

import com.poly.cinemaproject.model.dto.FilmGenreDTO;
import com.poly.cinemaproject.model.entity.FilmGenre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmGenreMapper {
    @Autowired
    ModelMapper mapper;
    public FilmGenre convertToEntity(FilmGenreDTO filmGenreDTO){
        return mapper.map(filmGenreDTO, FilmGenre.class);
    }
    public FilmGenreDTO convertToDTO(FilmGenre filmGenre){
        return mapper.map(filmGenre, FilmGenreDTO.class);
    }
}
