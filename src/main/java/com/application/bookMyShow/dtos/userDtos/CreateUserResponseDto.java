package com.application.bookMyShow.dtos.userDtos;

import com.application.bookMyShow.models.User;
import lombok.Data;

@Data
public class CreateUserResponseDto {
    private UserResponseDto user;

    public static UserResponseDto convertToUserResponseDto(User user){
        return UserResponseDto.convertTOUserResponseDto(user);
    }
}
