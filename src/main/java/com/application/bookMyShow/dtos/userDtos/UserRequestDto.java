package com.application.bookMyShow.dtos.userDtos;

import com.application.bookMyShow.models.User;
import lombok.Data;

@Data
public class UserRequestDto {
    private Long id;
    private String name;
    private String email;
    private String password;

    public static User convertToUser(UserRequestDto request){
        User newUser=new User();
        newUser.setId(request.getId());
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        return newUser;
    }
}
