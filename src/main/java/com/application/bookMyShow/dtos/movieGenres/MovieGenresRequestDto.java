package com.application.bookMyShow.dtos.movieGenres;

import lombok.Data;

@Data
public class MovieGenresRequestDto {
    private Long genresId;
    private Long movieId;
    private String name;
}
