package com.poly.cinemaproject.model.dto;

import com.poly.cinemaproject.model.entity.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
    private Integer maghe;
    private String soghe;
    private Integer dongia;
    private Integer loaighe;
    private Boolean trangthai;
    private RoomDTO room;
}
