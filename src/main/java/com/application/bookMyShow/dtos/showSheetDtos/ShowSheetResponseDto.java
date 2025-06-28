package com.application.bookMyShow.dtos.showSheetDtos;

import com.application.bookMyShow.models.ShowSheet;
import lombok.Data;

@Data
public class ShowSheetResponseDto {
    private ShowSheet showSheet;
    private String message;
}
