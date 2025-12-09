package com.application.bookMyShow.dtos.genresDtos;

import com.application.bookMyShow.models.Genres;
import lombok.Data;

@Data
public class GenresRequestDto {
    private String name;

    public static Genres convertToGenresDto(GenresRequestDto request){
        Genres newGenres=new Genres();
        newGenres.setName(request.getName());
        return newGenres;
    }
}
