package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.showDtos.*;
import com.application.bookMyShow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<CreateShowResponseDto> addShow(@RequestBody CreateShowRequestDto requestDto){
        return showService.addShow(requestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetShowResponseDto> getShow(@PathVariable Long id){
        return showService.getShow(id);
    }

    @GetMapping
    public ResponseEntity<ShowResponseDtos> getAllShow(){
        return showService.getAllShow();
    }

    @GetMapping("/allShows/{id}/{date}")
    public ResponseEntity<ShowResponseDtos> getAllShowByMovieIdAndDate(@PathVariable Long id,@PathVariable String date){
        return showService.getAllShowByMovieIdAndDate(id,date);
    }
    @GetMapping("/allShows/{id}")
    public ResponseEntity<ShowResponseDtos> getAllShowByMovieId(@PathVariable Long id){
        return showService.getAllShowByMovieId(id);
    }

    //Start from here
    @GetMapping("/allShows")
    public ResponseEntity<ShowResponseDtos> getAllShowByTheatreIds(@RequestParam List<Long> theatreIds){
        return showService.getAllShowByTheatreIds(theatreIds);
    }
}
