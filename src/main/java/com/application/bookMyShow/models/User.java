package com.application.bookMyShow.models;

import com.application.bookMyShow.dtos.userDtos.UserRequestDto;
import com.application.bookMyShow.models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private Role role;
}
