package com.application.bookMyShow.Exceptions;

public class InvalidSlotException extends RuntimeException {
    public InvalidSlotException(String timingSlotIsNotAvailable) {
        super(timingSlotIsNotAvailable);
    }
}
