package com.application.bookMyShow.dtos.showSheetDtos;

import com.application.bookMyShow.models.enums.ShowSeatStatus;
import lombok.Data;

@Data
public class ShowSheetDto {
    private Long seatId;
    private Long price;
    private ShowSeatStatus showSheetStatus;
}
