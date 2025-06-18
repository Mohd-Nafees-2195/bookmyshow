package com.application.bookMyShow.Exceptions;

public class InvalidShowException extends RuntimeException {
    public InvalidShowException(String invalidSHow) {
        super(invalidSHow);
    }
}
