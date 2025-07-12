package com.application.bookMyShow.dtos.userDtos;

import com.application.bookMyShow.models.User;
import lombok.Data;

@Data
public class CreateUserRequestDto {
    private UserRequestDto user;

    public static User convertToUser(UserRequestDto request){
        return UserRequestDto.convertToUser(request);
    }
}
