package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Movie;
import lombok.Data;

@Data
public class MovieResponseDto {
    private Movie movie;
    private String message;
}
