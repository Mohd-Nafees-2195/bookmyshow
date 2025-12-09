package com.application.bookMyShow.dtos.ShowTimingDtos;

import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetDto;
import com.application.bookMyShow.models.Show;
import com.application.bookMyShow.models.ShowTiming;
import com.application.bookMyShow.models.enums.ShowTimingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class ShowTimingDto {
    private Long id;
    private Date startTime;
    private Date endTime;
    private ShowTimingStatus showTimingStatus;
    private Long showId;

    public static ShowTimingDto convertToShowSheetDto(ShowTiming showTiming){
        ShowTimingDto response=new ShowTimingDto();
        response.setId(showTiming.getId());
        response.setStartTime(showTiming.getStartTime());
        response.setEndTime(showTiming.getEndTime());
        response.setShowTimingStatus(showTiming.getShowTimingStatus());
        return response;
    }
}
