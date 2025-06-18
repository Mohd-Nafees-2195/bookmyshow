package com.application.bookMyShow.Exceptions;

public class InvalidSeatException extends RuntimeException {
    public InvalidSeatException(String invalidSeat) {
        super(invalidSeat);
    }
}
