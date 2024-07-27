package com.poly.cinemaproject.controller.myController.admin;

import com.poly.cinemaproject.model.dto.UserCustomDTO;
import com.poly.cinemaproject.model.dto.UserDTO;
import com.poly.cinemaproject.model.entity.Role;
import com.poly.cinemaproject.repository.RoleRepo;
import com.poly.cinemaproject.service.UserService;
import com.poly.cinemaproject.serviceApi.UserServiceApi;
import com.poly.cinemaproject.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserServiceApi userServiceApi;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    FileUtils fileUtils;
    @Autowired
    UserService userService;

    @RequestMapping("/admin/users")
    public String index(Model model){
        Flux<UserDTO> userFlux= this.userServiceApi.getAllUser();
        List<UserDTO> usersDTO= userFlux.collectList().block();
        System.out.println(usersDTO.get(0).getRolesId());
        Optional<UserCustomDTO> userDTO= userService.findUserByUsername("huongpham");
        System.out.println("Find By Name" + userDTO.get());
        model.addAttribute("users", usersDTO);
        return "admin/user/index";
    }
    @RequestMapping("/admin/users/create")
    public String create(Model model
                        ,UserDTO userDTO
    ){
        model.addAttribute("user",userDTO);
        return "admin/user/create";
    }
    @PostMapping("/admin/users/save")
    public Mono<String> save(Model model,
                             UserDTO userDTO,
                             @RequestParam(value="images")MultipartFile[] images){
        if(userDTO.getId()== null){
            return userServiceApi.saveUser(userDTO,images)
                    .thenReturn("redirect:/admin/users");
        }else{
            return userServiceApi.updateUser(userDTO.getId(), userDTO,images)
                    .thenReturn("redirect:/admin/users");
        }

    }

    @RequestMapping("/admin/users/delete/{id}")
    public Mono<String> deleteUser(Model model,
                                   @PathVariable("id") Integer id){
        return userServiceApi.deleteUser(id)
                .thenReturn("redirect:/admin/users");
    }

    @RequestMapping("/admin/users/edit/{id}")
    public String editUser(Model model,
                           @PathVariable("id") Integer id){
         Mono<UserDTO> userMono= userServiceApi.getUserById(id);
         UserDTO userDTO = userMono.block();
         model.addAttribute("user",userDTO);
        return "admin/user/create";
    }

    @ModelAttribute("allRoles")
    public List<Role> getAllRole(){
        List<Role> allRoles= roleRepo.findAll();
        return allRoles;
    }

}
