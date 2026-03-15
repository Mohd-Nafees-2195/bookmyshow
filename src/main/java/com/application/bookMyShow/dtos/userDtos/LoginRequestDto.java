package com.application.bookMyShow.dtos.userDtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
