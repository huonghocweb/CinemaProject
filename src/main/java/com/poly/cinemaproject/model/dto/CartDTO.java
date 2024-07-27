package com.poly.cinemaproject.model.dto;

import com.poly.cinemaproject.model.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private SeatDTO seat;
    private Integer soluong;
    private String soghe;
    private Integer gia;
}
