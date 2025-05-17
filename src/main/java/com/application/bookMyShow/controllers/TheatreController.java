package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.TheatreRequestDto;
import com.application.bookMyShow.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theatre")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    public void addTheatre(@RequestBody TheatreRequestDto requestDto){
        theatreService.addTheatre(requestDto);
    }
}
