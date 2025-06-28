package com.application.bookMyShow.dtos.userDtos;

import com.application.bookMyShow.models.Booking;
import lombok.Data;

@Data
public class UserBookingResponseDto {
    private Booking booking;
}
