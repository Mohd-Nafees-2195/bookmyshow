package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.userDtos.UserRequestDto;
import com.application.bookMyShow.dtos.userDtos.UserResponseDto;
import com.application.bookMyShow.dtos.userDtos.UserResponseDtos;
import com.application.bookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto){
        return userService.saveUser(userRequestDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,@RequestBody UserRequestDto userRequestDto){
        return userService.updateUser(userRequestDto,id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    @GetMapping()
    public ResponseEntity<UserResponseDtos> getAllUser(){
        return userService.getAllUser();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
