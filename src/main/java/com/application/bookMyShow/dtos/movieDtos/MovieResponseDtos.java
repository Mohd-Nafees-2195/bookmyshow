package com.application.bookMyShow.dtos.movieDtos;

import lombok.Data;

import java.util.List;

@Data
public class MovieResponseDtos {
    private List<MovieResponseDto> movies;
}
