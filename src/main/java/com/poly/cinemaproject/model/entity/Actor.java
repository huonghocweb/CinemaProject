package com.poly.cinemaproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="daodien")
public class Actor {
    @Id
    private String madaodien;
    private String tendaodien;

    @JsonIgnore
    @ManyToMany(mappedBy = "actors")
    private List<Film> films= new ArrayList<Film>();
}
