package com.application.bookMyShow.dtos.genresDtos;

import com.application.bookMyShow.models.Genres;
import lombok.Data;

@Data
public class GenresDto {
    private Long id;
    private String name;

    public static GenresDto convertToGenresDto(Genres genres){
        GenresDto response=new GenresDto();
        response.setId(genres.getId());
        response.setName(genres.getName());
        return response;
    }
}
