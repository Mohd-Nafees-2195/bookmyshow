package com.application.bookMyShow.dtos.showDtos;

import com.application.bookMyShow.models.Show;
import lombok.Data;

@Data
public class CreateShowRequestDto {
    private ShowRequestDto show;

    public static Show convertToShow(ShowRequestDto request){
        return ShowRequestDto.convertToShow(request);
    }
}
