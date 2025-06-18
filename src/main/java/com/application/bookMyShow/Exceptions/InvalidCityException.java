package com.application.bookMyShow.Exceptions;

public class InvalidCityException extends RuntimeException {
    public InvalidCityException(String invalidCity) {
        super(invalidCity);
    }
}
