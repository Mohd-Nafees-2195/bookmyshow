package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.screenDtos.ScreenRequestDto;
import com.application.bookMyShow.dtos.screenDtos.ScreenResponseDto;
import com.application.bookMyShow.services.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("screens")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @PostMapping()
    public ResponseEntity<ScreenResponseDto> addScreen(@RequestBody ScreenRequestDto requestDto){
        return screenService.addScreen(requestDto);
    }
}
