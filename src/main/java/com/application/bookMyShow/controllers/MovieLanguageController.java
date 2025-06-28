package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.movieLanguageDtos.MovieLanguageRequestDto;
import com.application.bookMyShow.dtos.movieLanguageDtos.MovieLanguageResponseDto;
import com.application.bookMyShow.services.MovieLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie-languages")
public class MovieLanguageController {

    @Autowired
    private MovieLanguageService movieLanguageService;

    @PostMapping()
    public ResponseEntity<MovieLanguageResponseDto> addMovieLanguage(@RequestBody MovieLanguageRequestDto requestDto){
       return movieLanguageService.addMovieLanguage(requestDto);
    }
}
