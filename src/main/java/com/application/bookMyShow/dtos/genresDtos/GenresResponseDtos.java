package com.application.bookMyShow.dtos.genresDtos;

import lombok.Data;

import java.util.List;

@Data
public class GenresResponseDtos {
    private List<GenresResponseDto> genres;
}
