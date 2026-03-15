package com.application.bookMyShow.dtos.userDtos;

import com.application.bookMyShow.models.User;
import com.application.bookMyShow.models.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public static User convertToUser(UserRequestDto request){
        User newUser=new User();
        newUser.setId(request.getId());
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setRole(request.getRole());
        return newUser;
    }
}
