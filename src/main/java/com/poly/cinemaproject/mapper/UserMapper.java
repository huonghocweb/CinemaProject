package com.poly.cinemaproject.mapper;

import com.poly.cinemaproject.model.dto.UserCustomDTO;
import com.poly.cinemaproject.model.dto.RoleDTO;
import com.poly.cinemaproject.model.dto.UserDTO;

import com.poly.cinemaproject.model.entity.Role;
import com.poly.cinemaproject.model.entity.User;
import com.poly.cinemaproject.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    @Autowired
    ModelMapper mapper;
    @Autowired
    RoleRepo roleRepo;
    public User convertToEnity(UserDTO userDTO){
        User user= mapper.map(userDTO, User.class);
        List<Role> roles= userDTO.getRolesId().stream()
                .map(roleId -> roleRepo.findById(roleId)
                        .orElseThrow(()-> new RuntimeException("Role not found")))
                .collect(Collectors.toList());
        user.setRoles(roles);
        return user;
    }
    public UserCustomDTO convertToCustomUser(User user){
        UserCustomDTO userCustom= mapper.map(user, UserCustomDTO.class);
        List<RoleDTO> roles= user.getRoles().stream()
                .map(role -> mapper.map(role, RoleDTO.class))
                        .collect(Collectors.toList());
                userCustom.setRoles(roles);
        return mapper.map(user, UserCustomDTO.class);
    }
    public UserDTO convertToDTO(User user ){
        UserDTO userDTO= mapper.map(user, UserDTO.class);
        List<Integer> rolesId= user.getRoles().stream()
                        .map(Role :: getId)
                         .collect(Collectors.toList());
        userDTO.setRolesId(rolesId);
        return userDTO;
    }

}
