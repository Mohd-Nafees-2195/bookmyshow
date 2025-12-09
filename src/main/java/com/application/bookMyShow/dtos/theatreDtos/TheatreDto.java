package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.dtos.screenDtos.ScreenResponseDto;
import com.application.bookMyShow.models.Theatre;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class TheatreDto {
    private Long id;
    private String name;
    private Date created_at;
    private Date updated_at;

    public static TheatreDto convertToTheatreDto(Theatre theatre){
        TheatreDto response=new TheatreDto();
        response.setId(theatre.getId());
        response.setName(theatre.getName());
        response.setCreated_at(theatre.getCreated_at());
        response.setUpdated_at(theatre.getUpdated_at());
        return response;
    }
}
