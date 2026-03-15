package com.application.bookMyShow.dtos.bookingDto;

import lombok.Data;

import java.util.List;

@Data
public class BookingResponseDtos {
    private List<BookingResponseDto> bookings;
}
