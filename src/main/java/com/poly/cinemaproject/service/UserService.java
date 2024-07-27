package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.dto.UserCustomDTO;
import com.poly.cinemaproject.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserDTO> getAllUser();
    Optional<UserCustomDTO> findUserByUsername(String username);
//    CustomUserDetailsDTO findUserDTO(String username);
    Optional<UserDTO> getUserById(Integer id);
    UserDTO saveUser(UserDTO userDTO);
    Optional<Void> deleteUser(Integer id);
    Optional<UserDTO> updateUser(Integer id, UserDTO userDTO);
}
