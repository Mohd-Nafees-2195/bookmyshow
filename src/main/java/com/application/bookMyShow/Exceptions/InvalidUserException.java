package com.application.bookMyShow.Exceptions;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException(String message) {
        super(message);
    }
}
