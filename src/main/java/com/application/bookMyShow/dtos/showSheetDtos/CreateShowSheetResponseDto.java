package com.application.bookMyShow.dtos.showSheetDtos;

import com.application.bookMyShow.models.ShowSheet;
import lombok.Data;

@Data
public class CreateShowSheetResponseDto {
    private ShowSheetResponseDto response;

    public static ShowSheetResponseDto convertToShowSheetResponseDto(ShowSheet showSheet){
        return ShowSheetResponseDto.convertToShowSheetResponseDto(showSheet);
    }
}
