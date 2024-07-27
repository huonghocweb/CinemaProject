package com.poly.cinemaproject.controller.myController.home;

import com.poly.cinemaproject.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    CustomUserDetailService customUserDetailService;
    @RequestMapping("/login")
    public String login(Model model){
        return "home/newlogin";
    }

    @RequestMapping("/oauth2/login/success")
    public String success(OAuth2AuthenticationToken oauth2){
        customUserDetailService.loginFormOauth2(oauth2);
        return "redirect:/";
    }
    @RequestMapping("/access-denied")
    public String accessDenied (){
        return "redirect:/";
    }

    @RequestMapping("/auth/logout")
    public String logout(){
        return "redirect:/";
    }

}
