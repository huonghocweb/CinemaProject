package com.poly.cinemaproject.service;

import com.poly.cinemaproject.model.dto.RoleDTO;
import com.poly.cinemaproject.model.dto.UserCustomDTO;
import com.poly.cinemaproject.model.dto.UserDTO;
import com.poly.cinemaproject.model.entity.CustomUserDetails;
import com.poly.cinemaproject.model.entity.Role;
import com.poly.cinemaproject.model.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCustomDTO> user= userService.findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Not found User");
        }
        Collection<GrantedAuthority> grantedAuthorities= new ArrayList<>();
        List<RoleDTO> roles= user.get().getRoles();
        for(RoleDTO role: roles){
            if(role!= null){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRolename()));
            }
        }
        UserDetails userDetail= new CustomUserDetails(user.get(),grantedAuthorities);
        session.setAttribute("user", user.get());
        System.out.println("userLogin"+ user.get());
        return userDetail;
    }

    public void loginFormOauth2(OAuth2AuthenticationToken oauth2){
        String fullname= oauth2.getPrincipal().getAttribute("name");
        String email= oauth2.getPrincipal().getAttribute("email");
        String password= Long.toHexString(System.currentTimeMillis());
        UserCustomDTO user = new UserCustomDTO();
        user.setUsername(email);
        user.setPassword(password);

        List<RoleDTO> roles= new ArrayList<>();
        roles.add(new RoleDTO("ROLE_USER"));
        user.setRoles(roles);
        session.setAttribute("user", user);
        Collection<GrantedAuthority> grantedAuthorities= new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userOauth= new CustomUserDetails(user,grantedAuthorities);
        Authentication auth=new UsernamePasswordAuthenticationToken(userOauth,null ,userOauth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
