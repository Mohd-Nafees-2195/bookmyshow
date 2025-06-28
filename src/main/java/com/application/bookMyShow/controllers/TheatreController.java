package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.theatreDtos.TheatreRequestDto;
import com.application.bookMyShow.dtos.theatreDtos.TheatreResponseDto;
import com.application.bookMyShow.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theatres")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @PostMapping()
    public ResponseEntity<TheatreResponseDto> addTheatre(@RequestBody TheatreRequestDto requestDto){
       return theatreService.addTheatre(requestDto);
    }
}
