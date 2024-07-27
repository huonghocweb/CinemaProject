package com.poly.cinemaproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmGenreDTO {
    private String maloaiphim;
    private String tenloaiphim;
    @JsonIgnore
    List<FilmDTO> films;
}
