package com.application.bookMyShow.dtos.screenDtos;

import com.application.bookMyShow.models.Seat;
import lombok.Data;

import java.util.List;

@Data
public class ScreenRequestDto {
    private Long theatreId;
    private String screenName;
    private List<Seat> seats;
}
