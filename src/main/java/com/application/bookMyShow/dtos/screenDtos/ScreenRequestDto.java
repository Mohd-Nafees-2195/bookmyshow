package com.application.bookMyShow.dtos.screenDtos;

import com.application.bookMyShow.dtos.seatDtos.SeatRequestDto;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.enums.Feature;
import lombok.Data;

import java.util.List;

@Data
public class ScreenRequestDto {
    private Long theatreId;
    private String name;
    private List<Feature> features;
    private List<SeatRequestDto> seats;

    public static Screen convertToScreen(ScreenRequestDto request){
        Screen newScreen=new Screen();
        newScreen.setName(request.getName());
        return newScreen;
    }
}
