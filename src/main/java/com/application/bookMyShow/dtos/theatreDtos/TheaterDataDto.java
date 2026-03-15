package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.dtos.screenDtos.ScreenDto;
import com.application.bookMyShow.dtos.screenDtos.ScreenInfoDto;
import com.application.bookMyShow.dtos.showDtos.ShowResponseDtos;
import com.application.bookMyShow.models.Theatre;
import lombok.Data;

import java.util.List;

@Data
public class TheaterDataDto {
    private Long totalBooking;
    private Long totalRevenue;
    private Long activeShows;
    private ShowResponseDtos shows;
//    private GetTheatreResponseDtos theaters;
    private  List<TheatreInfoDto> theatres;
}
