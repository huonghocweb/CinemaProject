package com.poly.cinemaproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poly.cinemaproject.model.entity.Seat;
import com.poly.cinemaproject.model.entity.ShowTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomDTO {
    private Integer maphongchieu;
    private String tenphongchieu;
    private Integer soghetoida;

    @JsonIgnore
    private Set<ShowTime> showTimes= new HashSet<>();

    @JsonIgnore
    private Set<SeatDTO> seats = new HashSet<>();

}
