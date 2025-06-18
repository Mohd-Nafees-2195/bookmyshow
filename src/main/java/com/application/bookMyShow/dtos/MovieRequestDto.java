package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieRequestDto {
    private Movie movie;
    private MovieLanguageDto movieLanguageDto;
}
