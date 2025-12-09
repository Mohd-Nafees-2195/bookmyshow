package com.application.bookMyShow.dtos.movieDtos;

import com.application.bookMyShow.dtos.languageDtos.LanguageDto;
import com.application.bookMyShow.models.Movie;
import com.application.bookMyShow.models.enums.MovieStatus;
import lombok.Data;

import java.util.List;

@Data
public class MovieRequestDto {
    private String title;
    private String description;
    private Long runtime;
    private MovieStatus movieStatus;
    private String imageUrl;

    private List<Long> languageIds;
    private List<Long> genresIds;

    public static Movie convertToMovie(MovieRequestDto request){
        Movie newMovie=new Movie();
        newMovie.setTitle(request.getTitle());
        newMovie.setDescription(request.getDescription());
        newMovie.setRuntime(request.getRuntime());
        newMovie.setMovieStatus(request.getMovieStatus());
        newMovie.setImageUrl(request.getImageUrl());
        return newMovie;
    }
}
