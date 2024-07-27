package com.poly.cinemaproject.service;

import com.poly.cinemaproject.mapper.SeatMapper;
import com.poly.cinemaproject.model.dto.CartDTO;
import com.poly.cinemaproject.model.entity.Seat;
import com.poly.cinemaproject.repository.SeatRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    HttpSession session;
    @Autowired
    SeatRepo seatRepo;
    @Autowired
    private SeatMapper seatMapper;

    @Override
    public HashMap<Integer , CartDTO> addCart(Integer maghe, HashMap<Integer , CartDTO> tickets){
        CartDTO item= new CartDTO();
        Optional<Seat> seat= seatRepo.findById(maghe);
        if(seat !=null  && tickets.containsKey(maghe)){
                item.setSeat(seatMapper.convertToDTO(seat.get()));
                item.setSoluong(1);
                item.setSoghe(seat.get().getSoghe());
                item.setGia(seat.get().getDongia() * item.getSoluong());
        }else{
                item.setSoghe(seat.get().getSoghe());
                item.setSeat(seatMapper.convertToDTO(seat.get()));
                item.setSoluong(1);
                item.setGia(seat.get().getDongia() * item.getSoluong());
        }
        tickets.put(maghe, item);
        return tickets;
    }

    @Override
    public HashMap<Integer, CartDTO> removeCart(Integer maghe, HashMap<Integer, CartDTO> tickets) {
        return null;
    }

    @Override
    public Integer totalQuantity(HashMap<Integer, CartDTO> tickets){
        Integer totalQuantity= 0;
        for(Map.Entry<Integer,CartDTO> ticket : tickets.entrySet()){
            totalQuantity += ticket.getValue().getSoluong();
        }
        return totalQuantity;
    }

    @Override
    public Integer totalPrice(HashMap<Integer, CartDTO> tickets){
        Integer totalPrice=0;
        for(Map.Entry<Integer, CartDTO> ticket : tickets.entrySet()){
            totalPrice += ticket.getValue().getGia();
        }
        return totalPrice;
    }
}
