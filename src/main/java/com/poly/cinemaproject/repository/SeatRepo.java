package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat,Integer> {
}
