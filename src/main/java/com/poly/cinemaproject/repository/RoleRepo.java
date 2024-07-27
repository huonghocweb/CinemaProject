package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepo extends JpaRepository<Role,Integer> {
}
