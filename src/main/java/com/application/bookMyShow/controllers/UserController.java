package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.UserRequestDto;
import com.application.bookMyShow.dtos.UserResponseDto;
import com.application.bookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto){
        return userService.saveUser(userRequestDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,@RequestBody UserRequestDto userRequestDto){
        return userService.updateUser(userRequestDto,id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return userService.getAllUser();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
