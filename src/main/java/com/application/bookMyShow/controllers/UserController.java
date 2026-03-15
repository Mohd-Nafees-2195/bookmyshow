package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.userDtos.*;
import com.application.bookMyShow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponseDto> saveUser(@RequestBody CreateUserRequestDto request){
        return userService.saveUser(request);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable Long id,@RequestBody UpdateUserRequestDto request){
        return userService.updateUser(request,id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDto> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
    @GetMapping
    public ResponseEntity<GetUserResponseDtos> getAllUser(){
        return userService.getAllUser();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedUserResponseDto> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<CreateUserResponseDto> login(@RequestBody LoginRequestDto requestDto){
       return userService.login(requestDto.getEmail(),requestDto.getPassword());
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        System.out.println("LogOut Successfully");
        return userService.logout();
    }


    @PostMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestBody TokenDto token){
        boolean isValid= userService.validateToken(token.getToken());
        if(isValid){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
