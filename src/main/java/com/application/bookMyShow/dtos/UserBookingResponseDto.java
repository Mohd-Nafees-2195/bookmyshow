package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Booking;
import com.application.bookMyShow.models.enums.BookingStatus;
import lombok.Data;

@Data
public class UserBookingResponseDto {
    private Booking booking;
}
