package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Movie;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Show;
import com.application.bookMyShow.models.enums.Feature;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
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

