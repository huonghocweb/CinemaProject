package com.poly.cinemaproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="phongchieu")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maphongchieu;
    private String tenphongchieu;
    private Integer soghetoida;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<ShowTime> showTimes= new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private Set<Seat> seats;

}
