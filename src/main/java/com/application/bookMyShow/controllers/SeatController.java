package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.seatDtos.SeatRequestDto;
import com.application.bookMyShow.dtos.seatDtos.SeatResponseDto;
import com.application.bookMyShow.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping()
    public ResponseEntity<SeatResponseDto> addSeat(@RequestBody SeatRequestDto requestDto){
        return seatService.addSeat(requestDto);
    }
}
