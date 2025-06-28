package com.application.bookMyShow.dtos.showDtos;

import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetDto;
import com.application.bookMyShow.dtos.movieLanguageDtos.MovieLanguageDto;
import com.application.bookMyShow.models.enums.Feature;
import lombok.Data;

import java.util.List;

@Data
public class ShowRequestDto {
    private Long screenId;
    private Long movieId;
    private Long startTime;//Later change it to List of timing means for 1 show - many time-slot
    private Long endTime;
    private List<Feature> features;
    private MovieLanguageDto movieLanguageDto;
    private List<ShowSheetDto> showSheetDtos;
}

