package com.application.bookMyShow.Exceptions;

public class InvalidShowSheetException extends RuntimeException {
    public InvalidShowSheetException(String invalidShowSheet) {
        super(invalidShowSheet);
    }
}
