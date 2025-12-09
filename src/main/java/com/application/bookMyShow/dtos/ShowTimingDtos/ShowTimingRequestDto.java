package com.application.bookMyShow.dtos.ShowTimingDtos;

import com.application.bookMyShow.models.ShowTiming;
import com.application.bookMyShow.models.enums.ShowTimingStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ShowTimingRequestDto {
    private Date startTime;
    private Date endTime;
    private ShowTimingStatus showTimingStatus;
//    private Long showId;

    public static ShowTiming convertToShowTiming(ShowTimingRequestDto request){
        ShowTiming newShowTiming=new ShowTiming();
        newShowTiming.setStartTime(request.getStartTime());
        newShowTiming.setEndTime(request.getEndTime());
        newShowTiming.setShowTimingStatus(request.getShowTimingStatus());
        return newShowTiming;
    }
}
