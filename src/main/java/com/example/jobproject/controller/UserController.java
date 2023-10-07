package com.example.jobproject.controller;

import com.example.jobproject.Models.User;
import com.example.jobproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping(value = "/user",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public User addUser(@RequestPart User user, @RequestPart MultipartFile cv){
        String relativeFilePath=service.saveCV(cv,user.getFirstName(),user.getLastName());
        user.setCvPath(relativeFilePath);
        return service.saveUser(user);
    }


    @GetMapping("/userById/{id}")
    public User getUser(@PathVariable int id){
        return service.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/user/{name}")
    public User getUserByLastName(@PathVariable String lastName){
        return service.getUserByLastName(lastName);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id){
        return String.valueOf(service.deleteUser(id));
    }

}
