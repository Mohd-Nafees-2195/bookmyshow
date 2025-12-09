package com.application.bookMyShow.dtos.screenDtos;

import com.application.bookMyShow.dtos.seatDtos.SeatDto;
import com.application.bookMyShow.dtos.seatDtos.SeatResponseDto;
import com.application.bookMyShow.dtos.showDtos.ShowDto;
import com.application.bookMyShow.dtos.theatreDtos.TheatreResponseDto;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.Theatre;
import com.application.bookMyShow.models.enums.Feature;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScreenResponseDto {
    private Long id;
    private String name;
    private List<SeatResponseDto> seats;
    private List<Feature> features;
    private List<ShowDto> shows;

    public static ScreenResponseDto convertToScreenResponseDto(Screen screen){
        ScreenResponseDto response=new ScreenResponseDto();
        response.setId(screen.getId());
        response.setName(screen.getName());
        response.setSeats(new ArrayList<>());
        for(Seat seat:screen.getSeats()){
            SeatResponseDto seatResponse=SeatResponseDto.convertToSeatResponseDto(seat);
            response.getSeats().add(seatResponse);
        }
        response.setShows(new ArrayList<>());
        screen.getShows().forEach(show -> response.getShows().add(ShowDto.convertToShowDto(show)));
        response.setFeatures(screen.getFeatures());
//        response.setTheatre(TheatreResponseDto.convertToTheatreResponseDto(screen.getTheatre()));
        return response;
    }
//    private Screen screen;
//    private String message;
}
