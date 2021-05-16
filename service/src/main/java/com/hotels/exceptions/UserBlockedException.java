package com.hotels.exceptions;

/**
 * Exception that we get when user trying to add place or left comment that is
 * blocked.
 *
 */
public class UserBlockedException extends RuntimeException {
    /**
     * Constructor for UserBlockedException.
     */
    public UserBlockedException(String message) {
        super(message);
    }
}
