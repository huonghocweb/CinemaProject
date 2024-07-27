package com.poly.cinemaproject.config;

import com.poly.cinemaproject.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailService customUserDetailService;

    private final String [] PUBLIC_ENDPOINT= {};
    private final String [] PRIVATE_ENDPOINT= {};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(request ->
                        request.requestMatchers("/").permitAll()
                                .requestMatchers("/home/**").authenticated()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/authorities/**","/api/authorities/").hasAnyRole("ADMIN","USER")
                                .anyRequest().permitAll())
                        .formLogin(login ->
                                login.loginPage("/login").loginProcessingUrl("/logon")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/",true)
                                ).exceptionHandling(excep -> excep.accessDeniedPage("/access-denied"));
        // tat tu gia lap mac dinh cua security
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // Dang Xuat
        httpSecurity.logout(http ->
                http.logoutUrl("/auth/logout")
                .logoutSuccessUrl("/"));

        // Oauth2
        httpSecurity.oauth2Login(oauth2 ->
                oauth2.loginPage("/login")
                        .defaultSuccessUrl("/oauth2/login/success",true)
                        .failureUrl("/auth/login/error")
                        .authorizationEndpoint(auth -> auth.baseUri("/oauth2/authorization")) );
        // Secured API
        return httpSecurity.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("12345"));
    }
}

