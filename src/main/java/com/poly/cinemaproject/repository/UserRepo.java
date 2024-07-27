package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findUserByUsername(String username);

//    @Query("SELECT new com.poly.cinemaproject.model.dto.CustomUserDetailsDTO(u.id, u.username, u.roles) "
//            + " FROM User u  WHERE u.username = ?1")
//    CustomUserDetailsDTO findUserDTO(String username);
}
