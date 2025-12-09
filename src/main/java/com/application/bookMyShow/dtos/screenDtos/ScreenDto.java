package com.application.bookMyShow.dtos.screenDtos;

import com.application.bookMyShow.dtos.seatDtos.SeatDto;
import com.application.bookMyShow.dtos.theatreDtos.TheatreDto;
import com.application.bookMyShow.dtos.theatreDtos.TheatreResponseDto;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.enums.Feature;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScreenDto {
    private Long id;
    private String name;
//    private List<SeatDto> seats;
//    private List<Feature> features;
    private TheatreDto theatre;

    public static ScreenDto convertToScreenResponseDto(Screen screen){
        ScreenDto response=new ScreenDto();
        response.setId(screen.getId());
        response.setName(screen.getName());
        response.setTheatre(TheatreDto.convertToTheatreDto(screen.getTheatre()));
        return response;
    }
}
