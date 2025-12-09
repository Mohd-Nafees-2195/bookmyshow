package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.dtos.screenDtos.ScreenDto;
import com.application.bookMyShow.dtos.screenDtos.ScreenResponseDto;
import com.application.bookMyShow.models.Theatre;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TheatreResponseDto {
    private Long id;
    private String name;
    private Date created_at;
    private Date updated_at;
//    private List<ScreenResponseDto> screens;

    public static TheatreResponseDto convertToTheatreResponseDto(Theatre theatre){
        TheatreResponseDto response=new TheatreResponseDto();
        response.setId(theatre.getId());
        response.setName(theatre.getName());
        response.setCreated_at(theatre.getCreated_at());
        response.setUpdated_at(theatre.getUpdated_at());
//        response.setScreens(new ArrayList<>());
//        if(theatre.getScreens()!=null){
//            theatre.getScreens().forEach(screen -> response.getScreens().add(ScreenResponseDto.convertToScreenResponseDto(screen)));
//        }
        return response;
    }
}
