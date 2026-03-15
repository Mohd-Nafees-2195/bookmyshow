package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.movieDtos.*;
import com.application.bookMyShow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<CreateMovieResponseDto> addMovie(@RequestBody CreateMovieRequestDto request){
        return movieService.addMovie(request.getMovie());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMovieResponseDto> getMovie(@PathVariable Long id){
        return movieService.getMovie(id);
    }

    @GetMapping
    public ResponseEntity<MovieResponseDtos> getAllMovie(){
        return movieService.getAllMovie();
    }

    @GetMapping("/active")
    public ResponseEntity<MovieResponseDtos> getAllActiveMovies(){
        return movieService.getAllActiveMovies();
    }

}
