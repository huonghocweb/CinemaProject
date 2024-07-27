package com.poly.cinemaproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="loaiphim")
public class FilmGenre {
    @Id
    private String maloaiphim;
    private String tenloaiphim;

    @JsonIgnore
    @ManyToMany(mappedBy = "filmGenres")
    private List<Film> films = new ArrayList<>();

}
