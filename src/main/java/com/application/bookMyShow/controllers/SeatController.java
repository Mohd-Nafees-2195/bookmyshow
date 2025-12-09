package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.seatDtos.*;
import com.application.bookMyShow.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping()
    public ResponseEntity<CreateSeatResponseDto> addSeat(@RequestBody CreateSeatRequestDto requestDto){
        return seatService.addSeat(requestDto.getSeat());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSeatResponseDto> getSeat(@PathVariable Long id){
        return seatService.getSeat(id);
    }

    @GetMapping
    public ResponseEntity<SeatResponseDtos> getAllSeat(){
        return seatService.getAllSeat();
    }

    @DeleteMapping("/{id}")
    public Boolean deleteSeat(@PathVariable Long id){
        return seatService.deleteSeat(id);
    }
}
