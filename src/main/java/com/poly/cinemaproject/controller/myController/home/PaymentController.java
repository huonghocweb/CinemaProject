package com.poly.cinemaproject.controller.myController.home;

import com.poly.cinemaproject.model.dto.TicketDTO;
import com.poly.cinemaproject.service.VNPayService;
import com.poly.cinemaproject.serviceApi.TicketDetailServiceApi;
import com.poly.cinemaproject.serviceApi.TicketServiceApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

@Controller
public class PaymentController {
    @Autowired
    private VNPayService vnPayService;
    @Autowired
    HttpSession session;
    @Autowired
    TicketServiceApi ticketServiceApi;
    @Autowired
    TicketDetailServiceApi ticketDetailServiceApi;

    @PostMapping("/payment")
    public String checkout(HttpServletRequest request){
        int totalPrice= (int) session.getAttribute("totalPrice");
        Integer malichchieu= (Integer) session.getAttribute("malichchieu");
        String orderInfo= String.valueOf(malichchieu);
        String baseUrl= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(totalPrice, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
    }
    @RequestMapping("/returnPayment")
    public String thanks(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        int paymentStatus = vnPayService.orderReturn(request);
        request.setCharacterEncoding("UTF-8");
        String orderInfo= request.getParameter("vnp_OrderInfo");
        String paymentTime= request.getParameter("vnp_TransactionNo");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice= request.getParameter("vnp_Amount");
        Integer maLichChieu= Integer.valueOf(orderInfo);
        if(paymentStatus ==1 ){
           Mono<TicketDTO> ticketDTOMono= ticketServiceApi.saveTicket(Integer.valueOf(totalPrice), maLichChieu);
           TicketDTO ticketDTO= ticketDTOMono.block();
            ticketDetailServiceApi.saveTicketDetail(ticketDTO).block();
            model.addAttribute("paymentStatus", "Thanh Toán Thành Công");
        }else{
            model.addAttribute("paymentStatus", "Thanh Toán Thất Bại ");
        }
        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);
        return "home/thanks";
    }
}
