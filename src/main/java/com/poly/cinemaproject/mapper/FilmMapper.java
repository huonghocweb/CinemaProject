package com.poly.cinemaproject.mapper;

import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.dto.FilmGenreDTO;
import com.poly.cinemaproject.model.entity.Film;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmMapper {
    @Autowired
    private ModelMapper mapper;
    public Film convertToEntity(FilmDTO filmDTO){
        return mapper.map(filmDTO, Film.class);
    }
    public FilmDTO convertToDTO(Film film){
        return mapper.map(film, FilmDTO.class);
    }
}
