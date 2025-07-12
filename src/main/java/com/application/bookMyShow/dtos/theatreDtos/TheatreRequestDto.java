package com.application.bookMyShow.dtos.theatreDtos;

import com.application.bookMyShow.models.Theatre;
import lombok.Data;

@Data
public class TheatreRequestDto {
    private Long id;
    private Long cityId;
    private String theatreName;

    public static Theatre convertToTheatre(TheatreRequestDto request){
        Theatre newTheatre=new Theatre();
        newTheatre.setId(request.getId());
        newTheatre.setName(request.getTheatreName());
        return newTheatre;
    }
}
