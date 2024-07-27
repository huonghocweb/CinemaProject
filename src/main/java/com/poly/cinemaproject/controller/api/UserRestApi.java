package com.poly.cinemaproject.controller.api;

import com.poly.cinemaproject.model.dto.UserDTO;
import com.poly.cinemaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/authorities/user")
public class UserRestApi {

    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<UserDTO> usersDTO= this.userService.getAllUser();
        if(usersDTO.isEmpty()){
            return ResponseEntity.noContent().build();
        }
            return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        Optional<UserDTO> userDTO= this.userService.getUserById(id);
        if(userDTO.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO
                                        ){
            return ResponseEntity.ok(this.userService.saveUser(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        if(this.userService.getUserById(id).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        this.userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        if(this.userService.getUserById(id).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(this.userService.updateUser(id, userDTO));
    }
}
