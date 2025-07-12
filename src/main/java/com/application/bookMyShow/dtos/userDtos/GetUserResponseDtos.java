package com.application.bookMyShow.dtos.userDtos;

import com.application.bookMyShow.models.User;
import lombok.Data;

import java.util.List;

@Data
public class GetUserResponseDtos {
    private List<UserResponseDto> users;

    public static UserResponseDto convertToUserResponseDto(User user){
        return UserResponseDto.convertTOUserResponseDto(user);
    }
}
