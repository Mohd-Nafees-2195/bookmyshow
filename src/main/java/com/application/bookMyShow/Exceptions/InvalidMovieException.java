package com.application.bookMyShow.Exceptions;

public class InvalidMovieException extends RuntimeException {
    public InvalidMovieException(String s) {
        super(s);
    }
}
