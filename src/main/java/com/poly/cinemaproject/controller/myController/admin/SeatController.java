package com.poly.cinemaproject.controller.myController.admin;

import com.poly.cinemaproject.model.dto.CartDTO;
import com.poly.cinemaproject.model.dto.TicketDTO;
import com.poly.cinemaproject.service.CartService;
import com.poly.cinemaproject.serviceApi.TicketServiceApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SeatController {

    @Autowired
    CartService cartService;
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest request;
    @Autowired
    TicketServiceApi ticketServiceApi;

    @RequestMapping("home/chooseSeat/{maghe}")
    public String chooseSeat(Model model,
            @PathVariable("maghe") Integer maghe){
        HashMap<Integer , CartDTO> tickets= (HashMap<Integer, CartDTO>) session.getAttribute("tickets");
//        List<SeatDTO> seatNormal= (List<SeatDTO>) session.getAttribute("seatNormal");
//        List<SeatDTO> seatVip= (List<SeatDTO>) session.getAttribute("seatVip");
        if(tickets ==null){
            tickets= new HashMap<Integer , CartDTO>();
        }
        if(tickets.containsKey(maghe)){
            cartService.removeCart(maghe,tickets);
        }else{
            cartService.addCart(maghe, tickets);
        }
        List<String> seatChooses= new ArrayList<>();
        for(Map.Entry<Integer, CartDTO> tk : tickets.entrySet()){
                seatChooses.add(tk.getValue().getSoghe());
        }
        session.setAttribute("seatChooses", seatChooses);
        session.setAttribute("tickets", tickets);
        session.setAttribute("totalQuantity", cartService.totalQuantity(tickets));
        session.setAttribute("totalPrice", cartService.totalPrice(tickets));
        return "redirect:" +request.getHeader("Referer");
    }
}
