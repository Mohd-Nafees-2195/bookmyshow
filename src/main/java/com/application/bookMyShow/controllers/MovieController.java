package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.movieDtos.MovieRequestDto;
import com.application.bookMyShow.dtos.movieDtos.MovieResponseDto;
import com.application.bookMyShow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping()
    public ResponseEntity<MovieResponseDto> addMovie(@RequestBody MovieRequestDto requestDto){
        return movieService.addMovie(requestDto);
    }
}
