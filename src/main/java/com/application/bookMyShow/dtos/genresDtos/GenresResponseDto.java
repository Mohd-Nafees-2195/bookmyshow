package com.application.bookMyShow.dtos.genresDtos;

import com.application.bookMyShow.models.Genres;
import lombok.Data;

@Data
public class GenresResponseDto {
    private Long id;
    private String name;

    public static GenresResponseDto convertToGenresDto(Genres genres){
        GenresResponseDto response=new GenresResponseDto();
        response.setId(genres.getId());
        response.setName(genres.getName());
        return response;
    }
}
