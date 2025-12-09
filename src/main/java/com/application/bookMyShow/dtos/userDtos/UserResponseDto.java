package com.application.bookMyShow.dtos.userDtos;

import com.application.bookMyShow.models.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Date created_at;
    private Date updated_at;

    public static UserResponseDto convertTOUserResponseDto(User user){
        UserResponseDto response=new UserResponseDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreated_at(user.getCreated_at());
        response.setUpdated_at(user.getUpdated_at());
        return response;
    }
}
