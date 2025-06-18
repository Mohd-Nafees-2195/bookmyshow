package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Seat;
import lombok.Data;

@Data
public class SeatRequestDto {
    private Long screenId;
    private Seat seat;
}
