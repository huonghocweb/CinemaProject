package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.dto.CartDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface CartService {
    HashMap<Integer, CartDTO> addCart(Integer maghe, HashMap<Integer, CartDTO> tickets);
    HashMap<Integer, CartDTO> removeCart(Integer maghe, HashMap<Integer, CartDTO> tickets);
    Integer totalQuantity( HashMap<Integer, CartDTO> tickets);
    Integer totalPrice( HashMap<Integer, CartDTO> tickets);
}
