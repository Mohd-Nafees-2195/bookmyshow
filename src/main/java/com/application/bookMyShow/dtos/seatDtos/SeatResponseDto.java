package com.application.bookMyShow.dtos.seatDtos;

import com.application.bookMyShow.models.Seat;
import lombok.Data;

@Data
public class SeatResponseDto {
    private Seat seat;
    private String message;
}
