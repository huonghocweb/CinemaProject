package com.poly.cinemaproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="phim")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maphim;
    private String tenphim;
    private Date ngaysanxuat;
    private String hinhanh;
    private String quocgia;
    private Integer thoiluong;
    private String mota;
    private String href;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="phim_loaiphim",
            joinColumns = @JoinColumn(name="maphim"),
            inverseJoinColumns = @JoinColumn(name="maloaiphim")
    )
    private List<FilmGenre> filmGenres= new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="phim_daodien",
            joinColumns = @JoinColumn(name="maphim"),
            inverseJoinColumns = @JoinColumn(name="madaodien")
    )
    private List<Actor> actors = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "film")
    private Set<ShowTime> showTimes= new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "film")
    private List<TicketDetail> ls_ticketDetail;

}
