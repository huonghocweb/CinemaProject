package com.poly.cinemaproject.serviceApi;

import com.poly.cinemaproject.model.dto.FilmDTO;
import com.poly.cinemaproject.model.dto.UserDTO;
import com.poly.cinemaproject.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserServiceApi {
    @Autowired
    WebClient webClient;
    @Autowired
    FileUtils fileUtils;

    public Flux<UserDTO> getAllUser(){
        return webClient.get()
                .uri("api/authorities/user")
                .retrieve()
                .bodyToFlux(UserDTO.class);
    }

    public Mono<UserDTO> getUserById(Integer id){
        return webClient.get()
                .uri("api/authorities/user/{id}" ,id)
                .retrieve()
                .bodyToMono(UserDTO.class);
    }

    public Mono<UserDTO> saveUser(UserDTO userDTO, MultipartFile[] images){
        if( images.length> 0){
            String folder= "user_image";
            List<String> photoNames= fileUtils.save(folder, images);
            userDTO.setImage(photoNames.get(0));
        }
        return webClient.post()
                .uri("api/authorities/user")
                .bodyValue(userDTO)
                .retrieve()
                .bodyToMono(UserDTO.class);
    }

    public Mono<UserDTO> updateUser(Integer id, UserDTO userDTO, MultipartFile[] images){
        if( images == null){
            userDTO.setImage(userDTO.getImage());
        }else if(images.length>0){
            String folder= "user_image";
            List<String> photoNames= fileUtils.save(folder, images);
            userDTO.setImage(photoNames.get(0));
        }
        return webClient.put()
                .uri("api/authorities/user/{id}",id)
                .bodyValue(userDTO)
                .retrieve()
                .bodyToMono(UserDTO.class);
    }

    public Mono<Void> deleteUser(Integer id){
        return webClient.delete()
                .uri("api/authorities/user/{id}",id)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
