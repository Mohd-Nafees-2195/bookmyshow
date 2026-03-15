package com.application.bookMyShow.dtos.screenDtos;

import lombok.Data;

@Data
public class ScreenInfoDto {
    private Long id;
    private String name;

    public ScreenInfoDto(Long id,String name){
        this.id=id;
        this.name=name;
    }
}
