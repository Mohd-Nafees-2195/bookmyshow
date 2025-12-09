package com.application.bookMyShow.dtos.showSheetDtos;

import com.application.bookMyShow.models.ShowSheet;
import com.application.bookMyShow.models.enums.ShowSeatStatus;
import lombok.Data;

@Data
public class ShowSheetRequestDto {
    private Long showId;
    private Long seatId;
    private Long price;
    private ShowSeatStatus showSheetStatus;

    public static ShowSheet convertToShowSheet(ShowSheetRequestDto showSheet) {
        ShowSheet newShowSheet=new ShowSheet();
        newShowSheet.setPrice(showSheet.getPrice());
        newShowSheet.setShowSheetStatus(showSheet.getShowSheetStatus());
        return newShowSheet;
    }
}
