package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.genresDtos.*;
import com.application.bookMyShow.services.GenresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenresController {

    @Autowired
    private GenresServices genresServices;

    @PostMapping
    public ResponseEntity<CreateGenresResponseDto> addGenres(@RequestBody CreateGenresRequestDto request){
        return genresServices.addGenres(request.getGenre());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetGenresResponseDto> getGenres(@PathVariable Long id){
        return genresServices.getGenres(id);
    }

    @GetMapping
    public ResponseEntity<GenresResponseDtos> getAllGenres(){
        return genresServices.getAllGenres();
    }
}
