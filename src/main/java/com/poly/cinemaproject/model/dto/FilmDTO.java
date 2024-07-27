package com.poly.cinemaproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private Integer maphim;
    private String tenphim;
    private Date ngaysanxuat;
    private String hinhanh;
    private String quocgia;
    private Integer thoiluong;
    private String mota;
    private String href;
    private List<ActorDTO> actors;
    private List<FilmGenreDTO> filmGenres;
}
