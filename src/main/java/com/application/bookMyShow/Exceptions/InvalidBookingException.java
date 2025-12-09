package com.application.bookMyShow.Exceptions;

public class InvalidBookingException extends RuntimeException {
    public InvalidBookingException(String invalidBooking) {
        super(invalidBooking);
    }
}
