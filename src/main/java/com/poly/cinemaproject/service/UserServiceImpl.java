package com.poly.cinemaproject.service;

import com.poly.cinemaproject.mapper.UserMapper;
import com.poly.cinemaproject.model.dto.UserCustomDTO;
import com.poly.cinemaproject.model.dto.UserDTO;
import com.poly.cinemaproject.model.entity.User;
import com.poly.cinemaproject.repository.UserRepo;
import com.poly.cinemaproject.utils.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users= this.userRepo.findAll();
        return users.stream()
                .map(userMapper :: convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserCustomDTO> findUserByUsername(String username) {
        return Optional.of(userRepo.findUserByUsername(username)
                .map(userMapper :: convertToCustomUser)
                .orElseThrow( () -> new EntityNotFoundException("User Not Found")));
    }

//    @Override
//    public CustomUserDetailsDTO findUserDTO(String username) {
//        CustomUserDetailsDTO customUserDetailsDTO= userRepo.findUserDTO(username);
//        System.out.println(customUserDetailsDTO);
//        return customUserDetailsDTO;
//    }

    @Override
    public Optional<UserDTO> getUserById(Integer id) {
        Optional<User> user= userRepo.findById(id);
        return Optional.of(this.userRepo.findById(id)
                .map(userMapper :: convertToDTO)
                .orElseThrow( () -> new EntityNotFoundException("User Not Found")));
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO ) {
        User user= this.userMapper.convertToEnity(userDTO);
        User userSave= this.userRepo.save(user);
        return this.userMapper.convertToDTO(userSave);
    }

    @Override
    public Optional<Void> deleteUser(Integer id) {
        if(this.userRepo.existsById(id)){
            this.userRepo.deleteById(id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> updateUser(Integer id, UserDTO userDTO) {
        return Optional.of(this.userRepo.findById(id).map( existsUser ->{
            User user= this.userMapper.convertToEnity(userDTO);
            user.setId(existsUser.getId());
            User userUpdate= this.userRepo.save(user);
            return this.userMapper.convertToDTO(userUpdate);
        }).orElseThrow(() -> new EntityNotFoundException("User not found"))
        );
    }
}
