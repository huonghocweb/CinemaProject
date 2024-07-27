package com.poly.cinemaproject.mapper;

import com.poly.cinemaproject.model.dto.RoleDTO;
import com.poly.cinemaproject.model.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    @Autowired
    ModelMapper mapper;
    public Role convertToEntity(RoleDTO roleDTO){
        return this.mapper.map(roleDTO, Role.class);
    }
    public RoleDTO convertToDTO(Role role){
        return this.mapper.map(role, RoleDTO.class);
    }
}
