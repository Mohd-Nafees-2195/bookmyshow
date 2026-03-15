package com.application.bookMyShow.controllers;

import com.application.bookMyShow.Exceptions.InvalidUserException;
import com.application.bookMyShow.dtos.bookingDto.BookingRequestDto;
import com.application.bookMyShow.dtos.bookingDto.BookingResponseDto;
import com.application.bookMyShow.dtos.bookingDto.BookingResponseDtos;
import com.application.bookMyShow.services.UserBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class BookingController {
    @Autowired
    private UserBookingService userBookingService;

    @PostMapping("/book")
    public ResponseEntity<BookingResponseDto> bookTickets(@RequestBody BookingRequestDto requestDto) throws InvalidUserException {
        return userBookingService.bookTickets(requestDto);
    }

    //Get all booking by user
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDtos> bookTickets(@PathVariable Long id) throws InvalidUserException {
        return userBookingService.findAllBookingById(id); //Start from here return list of booking by user id
    }

    @PostMapping("/stripeWebhook")   //  /tickets/stripeWebhook
    public void listenToStripe(@RequestBody String event) {
        System.out.println(event);
    }
}
