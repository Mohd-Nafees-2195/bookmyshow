package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.models.Theatre;
import lombok.Data;

@Data
public class CreateTheatreResponseDto {
    private TheatreResponseDto theatre;

    public static TheatreResponseDto convertToTheatreResponseDto(Theatre theatre){
        return TheatreResponseDto.convertToTheatreResponseDto(theatre);
    }

}
