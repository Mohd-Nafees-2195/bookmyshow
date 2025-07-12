package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.models.Theatre;
import lombok.Data;

import java.util.List;

@Data
public class GetTheatreResponseDtos {
    private List<TheatreResponseDto> theatres;

    public static TheatreResponseDto convertToTheatreResponseDto(Theatre theatre){
        return TheatreResponseDto.convertToTheatreResponseDto(theatre);
    }
}
