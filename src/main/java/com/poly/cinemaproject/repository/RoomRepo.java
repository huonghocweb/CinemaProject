package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room,Integer> {
}
