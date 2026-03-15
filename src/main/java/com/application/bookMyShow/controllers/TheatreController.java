package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.theatreDtos.*;
import com.application.bookMyShow.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @PostMapping
    public ResponseEntity<CreateTheatreResponseDto> addTheatre(@RequestBody CreateTheatreRequestDto request){
       return theatreService.addTheatre(request);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetTheatreResponseDto> getTheatre(@PathVariable Long id){
        return theatreService.getTheatre(id);
    }
    @GetMapping
    public ResponseEntity<GetTheatreResponseDtos> getAllTheatre(){
        return theatreService.getAllTheatre();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheatre(@PathVariable Long id){
        return theatreService.deleteTheatre(id);
    }

    //Fetching Theater Data
    @GetMapping("/data/{id}")
    public ResponseEntity<TheaterDataDto> getTheaterDataByOwnerId(@PathVariable Long id){
        return theatreService.getTheaterDataByOwnerId(id);
    }
}
