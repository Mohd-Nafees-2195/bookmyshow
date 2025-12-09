package com.application.bookMyShow.dtos.showSheetDtos;

import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.ShowSheet;
import com.application.bookMyShow.models.enums.ShowSeatStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ShowSheetResponseDto {
//    private ShowSheet showSheet;
    private Long id;
    private Long showId;
    private Long seatId;
    private Long price;
    private ShowSeatStatus showSheetStatus;
    private String message;

    public static ShowSheetResponseDto convertToShowSheetResponseDto(ShowSheet showSheet) {
        ShowSheetResponseDto response=new ShowSheetResponseDto();
        response.setId(showSheet.getId());
        response.setPrice(showSheet.getPrice());
        response.setShowSheetStatus(showSheet.getShowSheetStatus());
        return response;
    }
}
