package com.application.bookMyShow.controllers;

import com.application.bookMyShow.Exceptions.InvalidUserException;
import com.application.bookMyShow.dtos.userDtos.UserBookingRequestDto;
import com.application.bookMyShow.dtos.userDtos.UserBookingResponseDto;
import com.application.bookMyShow.services.UserBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tickets")
public class BookingController {
    @Autowired
    private UserBookingService userBookingService;

    @PostMapping()
    public ResponseEntity<UserBookingResponseDto> bookTickets(@RequestBody UserBookingRequestDto requestDto) throws InvalidUserException {
        return userBookingService.bookTickets(requestDto);
    }
}
