package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.models.Theatre;
import lombok.Data;

@Data
public class TheatreRequestDto {
    private Long cityId;
    private String name;

    public static Theatre convertToTheatre(TheatreRequestDto request){
        Theatre newTheatre=new Theatre();
        newTheatre.setName(request.getName());
        return newTheatre;
    }
}
