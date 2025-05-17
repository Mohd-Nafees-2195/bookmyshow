package com.application.bookMyShow.services;

import com.application.bookMyShow.dtos.UserRequestDto;
import com.application.bookMyShow.dtos.UserResponseDto;
import com.application.bookMyShow.models.User;
import com.application.bookMyShow.repositories.UserRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<UserResponseDto> saveUser(UserRequestDto userRequestDto){
        Long time = System.currentTimeMillis();
        String password=passwordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(password);
        User user=new User(userRequestDto,time);
        User savedUser= userRepository.save(user);
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setUser(savedUser);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
    public ResponseEntity<UserResponseDto> updateUser(UserRequestDto userRequestDto,Long id){
        Optional<User> savedUser=userRepository.findById(id);
        UserResponseDto userResponseDto=new UserResponseDto();
        if(savedUser.isPresent()){
            Long time = System.currentTimeMillis();
            User user=savedUser.get();
            user.setUpdated_at(time);
            user.setName(userRequestDto.getName());
            user.setEmail(userRequestDto.getEmail());
            User updatedUser= userRepository.save(user);
            userResponseDto.setUser(updatedUser);
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(userResponseDto, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<UserResponseDto> getUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setUser(user.get());
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        List<User> users=userRepository.findAll();
        List<UserResponseDto> response=new ArrayList<>();
        for(User user:users){
            UserResponseDto userResponseDto=new UserResponseDto();
            userResponseDto.setUser(user);
            response.add(userResponseDto);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    public ResponseEntity<UserResponseDto> deleteUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        UserResponseDto userResponseDto=new UserResponseDto();
        if(user.isPresent()){
            userRepository.deleteById(id);
            userResponseDto.setUser(user.get());
            return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
        }
        return new ResponseEntity<>(userResponseDto,HttpStatus.PRECONDITION_FAILED);
    }
}
