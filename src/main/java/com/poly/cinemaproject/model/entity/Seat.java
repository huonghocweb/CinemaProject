package com.poly.cinemaproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ghe")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maghe;
    private String soghe;
    private Integer dongia;
    private Integer loaighe;
    private Boolean trangthai;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="maphongchieu")
    private Room room;
}
