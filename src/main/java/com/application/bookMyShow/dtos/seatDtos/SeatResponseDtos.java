package com.application.bookMyShow.dtos.seatDtos;

import lombok.Data;

import java.util.List;

@Data
public class SeatResponseDtos {
    private List<SeatResponseDto> seats;
}
