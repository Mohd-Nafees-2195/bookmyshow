package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.models.Theatre;
import lombok.Data;

@Data
public class TheatreResponseDto {
    private Long id;
    private String name;
    private Long created_at;
    private Long updated_at;

    public static TheatreResponseDto convertToTheatreResponseDto(Theatre theatre){
        TheatreResponseDto response=new TheatreResponseDto();
        response.setId(theatre.getId());
        response.setName(theatre.getName());
        response.setCreated_at(theatre.getCreated_at());
        response.setUpdated_at(theatre.getUpdated_at());
        return response;
    }
}
