package com.poly.cinemaproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Integer mave;
    private LocalDate ngaydatve;
    private Integer tongtien;
    private Boolean trangthai;
    private Integer tongsoghe;
    private UserDTO userDTO;
    private ShowTimeDTO showTimeDTO;
}
