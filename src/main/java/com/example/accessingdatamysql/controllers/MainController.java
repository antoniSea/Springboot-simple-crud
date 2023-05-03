package com.example.accessingdatamysql.controllers;

import com.example.accessingdatamysql.DTO.users.UserDTO;
import com.example.accessingdatamysql.database.entities.User;
import com.example.accessingdatamysql.database.repositories.UserRepository;
import com.example.accessingdatamysql.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/api")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/add")
    public @ResponseBody Optional<UserDTO> addNewUser (@RequestParam String name, @RequestParam String email) {
        return userService.saveUser(name, email);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody void deleteUser (@RequestParam String email) {
        userService.deleteUser(email);
    }

    @PutMapping(path="/update")
    public @ResponseBody UserDTO updateUser (@RequestBody UserDTO userDto, @RequestParam String name, @RequestParam String email) {
        return userService.updateUser(userDto, name, email);
    }

    @GetMapping(path="/find")
    public @ResponseBody UserDTO findUser (@RequestParam String email) {
        return userService.findUserByEmail(email);
    }
}