package com.application.bookMyShow.models;

import com.application.bookMyShow.dtos.UserRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;

    public User(){}
    public User(UserRequestDto userRequestDto,Long time){
        super(time,time);
        this.name=userRequestDto.getName();
        this.email=userRequestDto.getEmail();
        this.password= userRequestDto.getPassword();
    }
}
