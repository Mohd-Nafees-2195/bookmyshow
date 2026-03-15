package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.dtos.screenDtos.ScreenInfoDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TheatreInfoDto {
    private Long id;
    private String name;
    private List<ScreenInfoDto> screens;

    public TheatreInfoDto(Long id,String name){
        this.id=id;
        this.name=name;
        this.screens=new ArrayList<>();
    }
}
