package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.TokenStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class JwtSession extends BaseModel{
    private String token;

    @Enumerated(value = EnumType.STRING)
    private TokenStatus status;

    @ManyToOne
    private User user;
}
