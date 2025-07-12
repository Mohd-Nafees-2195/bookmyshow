package com.application.bookMyShow.services;

import com.application.bookMyShow.dtos.userDtos.*;
import com.application.bookMyShow.models.User;
import com.application.bookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<CreateUserResponseDto> saveUser(CreateUserRequestDto request){
        User newUser=CreateUserRequestDto.convertToUser(request.getUser());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setCreated_at(System.currentTimeMillis());
        newUser.setUpdated_at(System.currentTimeMillis());
        newUser.setIsDeleted(false);

        User savedUser= userRepository.save(newUser);
        CreateUserResponseDto response=new CreateUserResponseDto();
        response.setUser(CreateUserResponseDto.convertToUserResponseDto(savedUser));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<UpdateUserResponseDto> updateUser(UpdateUserRequestDto request,Long id){
        Optional<User> savedUser=userRepository.findById(id);
        UpdateUserResponseDto response=new UpdateUserResponseDto();
        if(savedUser.isPresent()){
            User user=savedUser.get();
            if(StringUtils.hasLength(request.getUser().getName())){
                user.setName(request.getUser().getName());
            }
            if(StringUtils.hasLength(request.getUser().getEmail())){
                user.setEmail(request.getUser().getEmail());
            }
            user.setUpdated_at(System.currentTimeMillis());
            user= userRepository.save(user);
            response.setUser(UpdateUserResponseDto.convertTpUserResponseDto(user));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<GetUserResponseDto> getUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        GetUserResponseDto response=new GetUserResponseDto();
        if(user.isPresent()){
            response.setUser(GetUserResponseDto.convertToUserResponseDto(user.get()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<GetUserResponseDtos> getAllUser() {
        List<User> users=userRepository.findAll();
        GetUserResponseDtos response=new GetUserResponseDtos();
        response.setUsers(new ArrayList<>());
        users.forEach(user -> {
            response.getUsers().add(GetUserResponseDtos.convertToUserResponseDto(user));
        });
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    public ResponseEntity<DeletedUserResponseDto> deleteUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        DeletedUserResponseDto response=new DeletedUserResponseDto();
        if(user.isPresent()){
            userRepository.deleteById(id);
            response.setUser(DeletedUserResponseDto.convertToUserResponseDto(user.get()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.PRECONDITION_FAILED);
    }
}
