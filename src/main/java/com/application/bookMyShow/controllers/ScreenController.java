package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.screenDtos.*;
import com.application.bookMyShow.services.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screens")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @PostMapping
    public ResponseEntity<CreateScreenResponseDto> addScreen(@RequestBody CreateScreenRequestDto request){
        return screenService.addScreen(request.getScreen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetScreenResponseDto> getScreen(@PathVariable Long id){
        return screenService.getScreen(id);
    }

    @GetMapping
    public ResponseEntity<ScreenResponseDtos> getAllScreen(){
        return screenService.getAllScreen();
    }

}
