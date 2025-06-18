package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.MovieLanguageRequestDto;
import com.application.bookMyShow.dtos.MovieLanguageResponseDto;
import com.application.bookMyShow.services.MovieLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie-language")
public class MovieLanguageController {

    @Autowired
    private MovieLanguageService movieLanguageService;

    @PostMapping("/add")
    public ResponseEntity<MovieLanguageResponseDto> addMovieLanguage(@RequestBody MovieLanguageRequestDto requestDto){
       return movieLanguageService.addMovieLanguage(requestDto);
    }
}
