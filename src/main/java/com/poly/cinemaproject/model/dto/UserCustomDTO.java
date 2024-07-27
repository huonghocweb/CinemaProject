package com.poly.cinemaproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCustomDTO {
    private Integer id;
    private String username;
    private String password;
    private String image;
    private List<RoleDTO> roles ;
}
