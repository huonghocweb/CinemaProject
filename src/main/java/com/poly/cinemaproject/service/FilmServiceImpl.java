package com.poly.cinemaproject.service;
import com.poly.cinemaproject.mapper.FilmMapper;
import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.entity.Film;
import com.poly.cinemaproject.repository.FilmRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmRepo filmRepo;
    @Autowired
    FilmMapper filmMapper;

    @Override
    public List<FilmDTO> getAllFilms() {
        List<Film> films= this.filmRepo.findAll();
        return films.stream()
                .map(filmMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FilmDTO> getFilmById(Integer id) {
        return Optional.of(this.filmRepo.findById(id)
                .map(filmMapper::convertToDTO).orElseThrow(() -> new EntityNotFoundException("Film not found")));
    }

    @Override
    public FilmDTO saveFilm(FilmDTO filmDTO) {
        Film film= filmMapper.convertToEntity(filmDTO);
        Film savedFilm =this.filmRepo.save(film);
        return filmMapper.convertToDTO(savedFilm);
    }

    @Override
    public Optional<Void> deleteFilmById(Integer id) {
        if(this.filmRepo.existsById(id)){
            this.filmRepo.deleteById(id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<FilmDTO> updateFilm(Integer id, FilmDTO filmDTO) {
        return Optional.of(this.filmRepo.findById(id).map(
                existsFilm -> {
                    Film film = this.filmMapper.convertToEntity(filmDTO);
                    Film updateFilm = this.filmRepo.save(film);
                    return this.filmMapper.convertToDTO(updateFilm);
                }).orElseThrow(() -> new EntityNotFoundException("Film Not Found")));
    }

    @Override
    public Film findFilmByKeyWord(String tenphim) {
        return filmRepo.findFilmByKeyWord(tenphim);
    }
}
