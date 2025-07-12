package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.models.Theatre;
import lombok.Data;

@Data
public class CreateTheatreRequestDto {
    private TheatreRequestDto theatre;

    public static Theatre convertToTheatre(TheatreRequestDto request){
        return TheatreRequestDto.convertToTheatre(request);
    }
}
