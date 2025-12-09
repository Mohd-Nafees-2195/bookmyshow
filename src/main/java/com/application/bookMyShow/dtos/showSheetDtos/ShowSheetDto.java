package com.application.bookMyShow.dtos.showSheetDtos;

import com.application.bookMyShow.models.ShowSheet;
import com.application.bookMyShow.models.ShowTiming;
import com.application.bookMyShow.models.enums.SeatType;
import com.application.bookMyShow.models.enums.ShowSeatStatus;
import lombok.Data;

@Data
public class ShowSheetDto {
    private Long id;
    private Long seatId;
    private Long price;
    private ShowSeatStatus showSheetStatus;
    private SeatType seatType;

    public static ShowSheetDto convertTo(ShowSheet showSheet){
        ShowSheetDto response=new ShowSheetDto();
        response.setId(showSheet.getId());;
        response.setSeatId(showSheet.getSeat().getId());  //comment latest1
        response.setPrice(showSheet.getPrice());
        response.setShowSheetStatus(showSheet.getShowSheetStatus());
        response.setSeatType(showSheet.getSeatType());
        return response;
    }
}
