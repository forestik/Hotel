package com.hotels.exceptions;

/**
 * Exception that we get when in some logic we have bad ID.
 *
 */
public class WrongIdException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message {@link String}
     */
    public WrongIdException(String message) {
        super(message);
    }
}
