package com.application.bookMyShow.dtos.showDtos;

import com.application.bookMyShow.dtos.ShowTimingDtos.ShowTimingDto;
import com.application.bookMyShow.dtos.movieDtos.MovieResponseDto;
import com.application.bookMyShow.dtos.screenDtos.ScreenDto;
import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetDto;
import com.application.bookMyShow.models.Movie;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Show;
import com.application.bookMyShow.models.ShowSheet;
import com.application.bookMyShow.models.enums.Feature;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ShowResponseDto {
//    private Show show;
    private Long id;
//    private Long movieId;
    private MovieResponseDto movie;
    private ScreenDto screen;
//    private Long screenId;
    private Date startTime;
    private Date endTime;
//    private List<ShowTimingDto> showTimings;

    private List<Feature> features;
    private List<ShowSheetDto> showSheets;


    public static ShowResponseDto convertToShowResponseDto(Show show) {
        ShowResponseDto response=new ShowResponseDto();
        response.setId(show.getId());
        response.setMovie(MovieResponseDto.convertToMovieResponseDto(show.getMovie()));
//        response.setMovieId(show.getMovie().getId());


        response.setShowSheets(new ArrayList<>());
        show.getShowSheets().forEach(showSheet -> {
            response.getShowSheets().add(ShowSheetDto.convertTo(showSheet));
        });
        response.setStartTime(show.getStartTime());
        response.setEndTime(show.getEndTime());
        response.setFeatures(show.getFeatures());

        response.setScreen(ScreenDto.convertToScreenResponseDto(show.getScreen()));
        return response;
    }
}
