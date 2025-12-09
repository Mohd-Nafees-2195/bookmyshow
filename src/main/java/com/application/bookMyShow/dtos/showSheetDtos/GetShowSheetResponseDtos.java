package com.application.bookMyShow.dtos.showSheetDtos;

import com.application.bookMyShow.models.ShowSheet;
import lombok.Data;

import java.util.List;

@Data
public class GetShowSheetResponseDtos {
    private List<ShowSheetResponseDto> showSheets;

    public static ShowSheetResponseDto convertToShowSheetResponseDto(ShowSheet showSheet){
        return ShowSheetResponseDto.convertToShowSheetResponseDto(showSheet);
    }
}
