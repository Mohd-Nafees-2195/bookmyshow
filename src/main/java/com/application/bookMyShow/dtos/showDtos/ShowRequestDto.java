package com.application.bookMyShow.dtos.showDtos;

import com.application.bookMyShow.dtos.ShowTimingDtos.ShowTimingRequestDto;
import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetDto;
import com.application.bookMyShow.models.Show;
import com.application.bookMyShow.models.enums.Feature;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ShowRequestDto {
    private Long screenId;
    private Long theatreId;
    private Long movieId;
    private List<ShowTimingRequestDto> showTimings;
    private List<Feature> features;
    private List<ShowSheetDto> showSheets;

    public static Show convertToShow(ShowRequestDto request) {
        Show newShow=new Show();
        newShow.setFeatures(request.getFeatures());
        return newShow;
    }
}


