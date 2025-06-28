package com.application.bookMyShow.dtos.movieDtos;

import com.application.bookMyShow.dtos.movieLanguageDtos.MovieLanguageDto;
import com.application.bookMyShow.models.Movie;
import lombok.Data;

@Data
public class MovieRequestDto {
    private Movie movie;
    private MovieLanguageDto movieLanguageDto;
}
