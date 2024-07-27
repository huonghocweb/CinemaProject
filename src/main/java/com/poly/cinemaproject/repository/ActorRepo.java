package com.poly.cinemaproject.repository;

import com.poly.cinemaproject.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepo extends JpaRepository<Actor,String> {
}
