package com.application.bookMyShow.Exceptions;

public class InvalidTheatreException extends RuntimeException {
    public InvalidTheatreException(String invalidTheatre) {
        super(invalidTheatre);
    }
}
