package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.showDtos.ShowRequestDto;
import com.application.bookMyShow.dtos.showDtos.ShowResponseDto;
import com.application.bookMyShow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping()
    public ResponseEntity<ShowResponseDto> addShow(@RequestBody ShowRequestDto requestDto){
        return showService.addShow(requestDto);
    }

}
