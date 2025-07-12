package com.application.bookMyShow.dtos.userDtos;

import com.application.bookMyShow.models.User;
import lombok.Data;

@Data
public class UpdateUserResponseDto {
    private UserResponseDto user;

    public static UserResponseDto convertTpUserResponseDto(User user){
        return UserResponseDto.convertTOUserResponseDto(user);
    }
}
