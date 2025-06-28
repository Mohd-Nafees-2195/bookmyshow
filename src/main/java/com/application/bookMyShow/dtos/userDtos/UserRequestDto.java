package com.application.bookMyShow.dtos.userDtos;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
}
