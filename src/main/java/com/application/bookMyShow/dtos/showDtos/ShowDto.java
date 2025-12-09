package com.application.bookMyShow.dtos.showDtos;

import com.application.bookMyShow.dtos.movieDtos.MovieResponseDto;
import com.application.bookMyShow.dtos.screenDtos.ScreenDto;
import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetDto;
import com.application.bookMyShow.models.Show;
import com.application.bookMyShow.models.enums.Feature;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ShowDto {
    private Long id;
    private MovieResponseDto movie;
//    private ScreenDto screen;
    private Date startTime;
    private Date endTime;
    private List<ShowSheetDto> showSheets;


    public static ShowDto convertToShowDto(Show show) {
        ShowDto response=new ShowDto();
        response.setId(show.getId());
        response.setMovie(MovieResponseDto.convertToMovieResponseDto(show.getMovie()));

        response.setStartTime(show.getStartTime());
        response.setEndTime(show.getEndTime());
        response.setShowSheets(new ArrayList<>());
        show.getShowSheets().forEach(showSheet -> response.getShowSheets().add(ShowSheetDto.convertTo(showSheet)));
        return response;
    }
}
