package com.application.bookMyShow.dtos.showSheetDtos;

import com.application.bookMyShow.models.ShowSheet;
import lombok.Data;

@Data
public class CreateShowSheetRequestDto {
    private ShowSheetRequestDto showSheet;

    public static ShowSheet convertToShowSheet(ShowSheetRequestDto showSheet){
        return ShowSheetRequestDto.convertToShowSheet(showSheet);
    }
}
