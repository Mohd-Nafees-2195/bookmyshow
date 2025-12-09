package com.application.bookMyShow.dtos.showDtos;

import com.application.bookMyShow.models.Show;
import lombok.Data;

import java.util.List;

@Data
public class CreateShowResponseDto {
    private List<ShowResponseDto> shows;

    public static ShowResponseDto convertToShowResponseDto(Show show){
        return ShowResponseDto.convertToShowResponseDto(show);
    }
}
