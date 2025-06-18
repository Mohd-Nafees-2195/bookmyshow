package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String number;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "screen_id",nullable = false)
    @JsonBackReference
    private Screen screen;
}

/*
Seat --> Screen
1 --> 1
M <-->
 */
